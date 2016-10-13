package yutailuo.androiddeeplinksearch.core;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.Executor;

import yutailuo.androiddeeplinksearch.listener.SearchProgressListener;
import yutailuo.androiddeeplinksearch.listener.SimpleSearchProgressListener;

public class SearchManager {

    public static final String TAG = SearchManager.class.getSimpleName();

    private static final String RE_INIT_CONFIG = "Search configuration has already been initialized.";
    private static final String INIT_CONFIG_NULL = "Search configuration can not be initialized with null.";
    private static final String NOT_INIT_CONFIG = "Search configuration is not initialized.";

    private volatile static SearchManager sInstance;

    private Context mContext;
    private SearchConfiguration mConfiguration;
    private Executor mTaskExecutor;

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
        } else {
            Log.w(TAG, RE_INIT_CONFIG);
        }
    }

    public boolean isInited() {
        return mConfiguration != null;
    }

    public void loadQuery(String query) {
        loadQuery(query, null);
    }

    public void loadQuery(String query, SearchProgressListener listener) {
        checkConfiguration();
        if (listener == null) {
            listener = new SimpleSearchProgressListener();
        }
        listener.onSearchStarted(query);

    }

    private void checkConfiguration() {
        if (mConfiguration == null) {
            throw new IllegalStateException(NOT_INIT_CONFIG);
        }
    }
}
