package yutailuo.androiddeeplinksearch.core;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import yutailuo.androiddeeplinksearch.display.ISearchResultDisplayer;
import yutailuo.androiddeeplinksearch.display.SearchResultAdapter;
import yutailuo.androiddeeplinksearch.task.AppSearchTask;
import yutailuo.androiddeeplinksearch.task.DeeplinkSearchTask;
import yutailuo.androiddeeplinksearch.task.ISearchTask;

public class SearchManager implements SearchProgressListener {

    public static final String TAG = SearchManager.class.getSimpleName();

    private static final String RE_INIT_CONFIG = "Search configuration has already been initialized.";
    private static final String INIT_CONFIG_NULL = "Search configuration can not be initialized with null.";
    private static final String NOT_INIT_CONFIG = "Search configuration is not initialized.";
    private static final String NOT_INIT_DISPLAYER = "Search result displayer is not initialized.";

    private volatile static SearchManager sInstance;

    private Context mContext;
    private SearchConfiguration mConfiguration;
    private Executor mTaskExecutor;

    private ISearchResultDisplayer mSearchResultDisplayer;

    // Return singleton class instance.
    public static SearchManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SearchManager.class) {
                if (sInstance == null) {
                    sInstance = new SearchManager(context);
                }
            }
        }
        return sInstance;
    }

    private SearchManager(Context context) {
        mContext = context.getApplicationContext();
    }

    public synchronized void init(SearchConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException(INIT_CONFIG_NULL);
        }
        if (mConfiguration == null) {
            mConfiguration = configuration;
            mTaskExecutor = configuration.getTaskExecutor();
            mSearchResultDisplayer = new SearchResultAdapter();
        } else {
            Log.w(TAG, RE_INIT_CONFIG);
        }
    }

    public boolean isInited() {
        return mConfiguration != null;
    }

    public void setSearchResultDisplayer(ISearchResultDisplayer searchResultDisplayer) {
        mSearchResultDisplayer = searchResultDisplayer;
    }

    public void loadQuery(String query) {
        checkConfiguration();
        // Execute tasks in search task list.
        for (ISearchTask searchTask : createSearchTasks(query)) {
            mTaskExecutor.execute(searchTask);
        }
    }

    private void checkConfiguration() {
        if (mConfiguration == null) {
            throw new IllegalStateException(NOT_INIT_CONFIG);
        }
    }

    private List<ISearchTask> createSearchTasks(String query) {
        List<ISearchTask> searchTasks = new ArrayList<>();
        AppSearchTask appSearchTask = new AppSearchTask(mContext, query, this);
        searchTasks.add(appSearchTask);
        DeeplinkSearchTask deeplinkSearchTask = new DeeplinkSearchTask(mContext, query,
                mConfiguration.getDeeplinkMap(), this);
        searchTasks.add(deeplinkSearchTask);
        return searchTasks;
    }

    @Override
    public void onSearchStarted(ISearchTask.SearchType searchType, String query) {
        Log.d(TAG, searchType.toString() + " search started for query: " + query);
    }

    @Override
    public void onSearchFailed(ISearchTask.SearchType searchType, String query) {
        Log.d(TAG, searchType.toString() + " search failed for query: " + query);
    }

    @Override
    public void onSearchComplete(ISearchTask.SearchType searchType, String query,
                                 VerticalSearchResult verticalSearchResult) {
        Log.d(TAG, searchType.toString() + " search completed for query: " + query);

        if (mSearchResultDisplayer == null) {
            throw new IllegalStateException(NOT_INIT_DISPLAYER);
        }
        mSearchResultDisplayer.display(verticalSearchResult, query);
    }

    @Override
    public void onSearchCancelled(ISearchTask.SearchType searchType, String query) {
        Log.d(TAG, searchType.toString() + " search cancelled for query: " + query);
    }
}
