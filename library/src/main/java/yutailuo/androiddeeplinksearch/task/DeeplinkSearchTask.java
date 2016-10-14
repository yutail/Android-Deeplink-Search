package yutailuo.androiddeeplinksearch.task;

import android.content.Context;
import android.content.Intent;

import java.util.Map;

import yutailuo.androiddeeplinksearch.core.SearchProgressListener;

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

    }

    @Override
    public SearchType getSearchType() {
        return SearchType.DEEPLINK;
    }

    @Override
    public void run() {
        submitQuery();
    }
}