package yutailuo.androiddeeplinksearch.display;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import yutailuo.androiddeeplinksearch.core.SearchResultData;
import yutailuo.library.R;

/**
 * Created by yutailuo on 10/17/16.
 */

public class SearchItemView extends LinearLayout {

    private Context mContext;

    private SearchResultData mSearchResult;

    public SearchItemView(Context context) {
        super(context);
        mContext = context;
    }

    public SearchItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void createView() {
        LayoutInflater.from(mContext).inflate(R.layout.search_item_view, this);
        ImageView searchItemIcon = (ImageView) findViewById(R.id.search_item_icon);
        searchItemIcon.setImageDrawable(mSearchResult.getIcon());
        TextView searchItemLabel = (TextView) findViewById(R.id.search_item_label);
        searchItemLabel.setText(mSearchResult.getLabel());
    }

    public void setSearchResult(SearchResultData searchResult) {
        if (searchResult != null) {
            mSearchResult = searchResult;
            createView();
        }
    }
}
