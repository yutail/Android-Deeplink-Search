package yutailuo.androiddeeplinksearch.display;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.List;

import yutailuo.androiddeeplinksearch.core.SearchResultData;
import yutailuo.library.R;

/**
 * Created by yutailuo on 10/17/16.
 */

public class SearchContainerView extends LinearLayout {

    private Context mContext;

    private List<SearchResultData> mSearchResults;

    public SearchContainerView(Context context) {
        super(context);
        mContext = context;
    }

    public SearchContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void createView() {
        LayoutInflater.from(mContext).inflate(R.layout.search_container_view, this);
        for (SearchResultData searchResult : mSearchResults) {
            SearchItemView searchItemView = new SearchItemView(mContext);
            searchItemView.setSearchResult(searchResult);
            addView(searchItemView);
        }
    }

    public void setSearchResults(List<SearchResultData> searchResults) {
        if (searchResults != null) {
            mSearchResults = searchResults;
            createView();
        }
    }
}
