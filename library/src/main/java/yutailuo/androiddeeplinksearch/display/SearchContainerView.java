package yutailuo.androiddeeplinksearch.display;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import yutailuo.androiddeeplinksearch.core.SearchResultData;
import yutailuo.androiddeeplinksearch.core.VerticalSearchResult;
import yutailuo.library.R;

/**
 * Created by yutailuo on 10/17/16.
 */

public class SearchContainerView extends LinearLayout {

    private Context mContext;

    private VerticalSearchResult mVerticalSearchResult;

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
        for (SearchResultData searchResult : mVerticalSearchResult.getSearchResults()) {
            SearchItemView searchItemView = new SearchItemView(mContext);
            searchItemView.setSearchResult(searchResult);
            addView(searchItemView);
        }
    }

    public void setVerticalSearchResult(VerticalSearchResult verticalSearchResult) {
        if (verticalSearchResult != null) {
            mVerticalSearchResult = verticalSearchResult;
            createView();
        }
    }
}
