package yutailuo.androiddeeplinksearch.listener;

/**
 * Created by yutailuo on 10/7/16.
 */

public interface SearchProgressListener {

    void onSearchStarted(String query);

    void onSearchFailed(String query);

    void onSearchComplete(String query);

    void onSearchCancelled(String query);
}
