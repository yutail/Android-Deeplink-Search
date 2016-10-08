package yutailuo.androiddeeplinksearch.core;

import android.util.Log;

import yutailuo.androiddeeplinksearch.listener.SearchProgressListener;

public class SearchManager {

    public static final String TAG = SearchManager.class.getSimpleName();

    private static final String RE_INIT_CONFIG = "Search configuration has already been initialized.";
    private static final String INIT_CONFIG_NULL = "Search configuration can not be initialized with null.";
    private static final String NOT_INIT = "Search configuration is not initialzed.";

    private SearchConfiguration mConfiguration;

    private volatile static SearchManager sInstance;

    /** Return singleton class instance. */
    public static SearchManager getInstance() {
        if (sInstance == null) {
            synchronized (SearchManager.class) {
                if (sInstance == null) {
                    sInstance = new SearchManager();
                }
            }
        }
        return sInstance;
    }

    private SearchManager() {
    }

    public synchronized void init(SearchConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException(INIT_CONFIG_NULL);
        }
        if (mConfiguration == null) {
            mConfiguration = configuration;
        } else {
            Log.w(TAG, RE_INIT_CONFIG);
        }
    }

    public boolean isInited() {
        return mConfiguration != null;
    }

    public void loadQuery(String query, SearchProgressListener listener) {
        checkConfiguration();

    }

    private void checkConfiguration() {
        if (mConfiguration == null) {
            throw new IllegalStateException(NOT_INIT);
        }
    }
}
