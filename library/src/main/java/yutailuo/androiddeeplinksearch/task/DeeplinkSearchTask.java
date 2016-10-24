package yutailuo.androiddeeplinksearch.task;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Map;

import yutailuo.androiddeeplinksearch.core.SearchProgressListener;
import yutailuo.androiddeeplinksearch.core.SearchResultData;
import yutailuo.androiddeeplinksearch.core.VerticalSearchResult;

public class DeeplinkSearchTask implements ISearchTask {

    private static final String TAG = "DeeplinkSearchTask";

    private Context mContext;
    private String mQuery;
    private Map<String, Intent> mDeeplinkMap;

    private SearchProgressListener mListener;

    public DeeplinkSearchTask(Context context, String query, Map<String, Intent> deeplinkMap) {
        new DeeplinkSearchTask(context, query, deeplinkMap, null);
    }

    public DeeplinkSearchTask(Context context, String query, Map<String, Intent> deeplinkMap,
                              SearchProgressListener listener) {
        mContext = context;
        mQuery = query;
        mDeeplinkMap = deeplinkMap;
        mListener = listener;
    }

    @Override
    public void submitQuery() {
        if (mListener != null) {
            VerticalSearchResult deeplinkSearchResult = new VerticalSearchResult(getSearchType(),
                    new ArrayList<SearchResultData>());
            mListener.onSearchComplete(getSearchType(), mQuery, deeplinkSearchResult);
            return;
        }
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.DEEPLINK;
    }

    @Override
    public void run() {
        if (mListener != null) {
            mListener.onSearchStarted(getSearchType(), mQuery);
        }
        submitQuery();
    }
}