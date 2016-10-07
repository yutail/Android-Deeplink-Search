package yutailuo.androiddeeplinksearch.core;

public class SearchManager {

    public static final String TAG = SearchManager.class.getSimpleName();

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

        }
        if (mConfiguration == null) {
            mConfiguration = configuration;
        } else {

        }
    }

    public boolean isInited() {
        return mConfiguration != null;
    }
}
