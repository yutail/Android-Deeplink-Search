package yutailuo.androiddeeplinksearch.task;

/**
 * Created by yutailuo on 10/10/16.
 */

public interface ISearchTask extends Runnable {
    enum SearchType {
        APP, DEEPLINK
    }
    void submitQuery();
    SearchType getSearchType();
}
