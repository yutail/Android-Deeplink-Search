package yutailuo.androiddeeplinksearch.core;

import java.util.List;

import yutailuo.androiddeeplinksearch.task.ISearchTask;

/**
 * Created by yutailuo on 10/24/16.
 */

public class VerticalSearchResult {

    private List<SearchResultData> mSearchResults;

    private ISearchTask.SearchType mSearchType;

    public VerticalSearchResult(ISearchTask.SearchType searchType,
                                List<SearchResultData> searchResults) {
        mSearchType = searchType;
        mSearchResults = searchResults;
    }

    public List<SearchResultData> getSearchResults() {
        return mSearchResults;
    }

    public ISearchTask.SearchType getSearchType() {
        return mSearchType;
    }
}
