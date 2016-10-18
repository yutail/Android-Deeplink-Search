package yutailuo.androiddeeplinksearch.task;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import yutailuo.androiddeeplinksearch.core.SearchProgressListener;
import yutailuo.androiddeeplinksearch.core.SearchResultData;
import yutailuo.androiddeeplinksearch.util.AppSearchUtils;

public class AppSearchTask implements ISearchTask {

    private static final String TAG = "AppSearchTask";

    private Context mContext;
    private String mQuery;

    private SearchProgressListener mListener;

    public AppSearchTask(Context context, String query) {
        new AppSearchTask(context, query, null);
    }

    public AppSearchTask(Context context, String query, SearchProgressListener listener) {
        mContext = context;
        mQuery = query;
        mListener = listener;
    }

    @Override
    public void submitQuery() {
        String trimmedQuery = mQuery.trim();
        if (TextUtils.isEmpty(trimmedQuery)) {
            if (mListener != null) {
                mListener.onSearchComplete(getSearchType(), mQuery, new ArrayList<SearchResultData>());
                return;
            }
        }

        PackageManager pm = mContext.getPackageManager();
        List<SearchResultData> searchResults = new ArrayList<>();
        Pattern wordPrefixMatch = Pattern.compile("\\b" + Pattern.quote(trimmedQuery), Pattern.CASE_INSENSITIVE);

        Map<ComponentName, String> appMap = AppSearchUtils.getAppCache(mContext);
        if (appMap != null) {
            Iterator<Map.Entry<ComponentName, String>> it = appMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, String> appEntry = it.next();
                ComponentName appCN = appEntry.getKey();
                String appName = appEntry.getValue();
                Drawable appIcon = null;

                if (wordPrefixMatch.matcher(appName).find()) {
                    try {
                        pm.getActivityInfo(appCN, 0);
                        pm.getActivityIcon(appCN);
                    } catch (PackageManager.NameNotFoundException e) {
                        continue;
                    }
                    searchResults.add(new SearchResultData(appName, appCN.flattenToShortString(),
                            appIcon));
                }
            }
        }
        if (mListener != null) {
            mListener.onSearchComplete(getSearchType(), mQuery, searchResults);
        }
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.APP;
    }

    @Override
    public void run() {
        if (mListener != null) {
            mListener.onSearchStarted(getSearchType(), mQuery);
        }
        submitQuery();
    }
}
