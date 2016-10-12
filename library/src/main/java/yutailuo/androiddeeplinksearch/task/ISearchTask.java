package yutailuo.androiddeeplinksearch.task;

/**
 * Created by yutailuo on 10/10/16.
 */

public interface ISearchTask extends Runnable {
    void submitQuery(String query);
}
