package yutailuo.androiddeeplinksearch.core;

import java.util.List;

import yutailuo.androiddeeplinksearch.task.ISearchTask;

/**
 * Created by yutailuo on 10/7/16.
 */

public interface SearchProgressListener {

    void onSearchStarted(ISearchTask.SearchType searchType, String query);

    void onSearchFailed(ISearchTask.SearchType searchType, String query);

    void onSearchComplete(ISearchTask.SearchType searchType, String query,
                          List<SearchResultData> searchResults);

    void onSearchCancelled(ISearchTask.SearchType searchType, String query);
}
