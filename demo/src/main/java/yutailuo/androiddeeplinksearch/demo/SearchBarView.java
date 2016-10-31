package yutailuo.androiddeeplinksearch.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by yutailuo on 10/31/16.
 */

public class SearchBarView extends LinearLayout {

    private static final String TAG = SearchBarView.class.getSimpleName();

    private EditText mSearchText;
    private View mClearButton;

    public SearchBarView(Context context) {
        super(context);
    }

    public SearchBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.search_bar_view, this);
    }
}
