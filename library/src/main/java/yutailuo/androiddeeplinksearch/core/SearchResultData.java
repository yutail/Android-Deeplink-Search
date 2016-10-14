package yutailuo.androiddeeplinksearch.core;

import android.graphics.drawable.Drawable;

/**
 * Created by yutailuo on 10/13/16.
 */

public class SearchResultData {

    private Drawable mIcon;
    private String mLabel;
    private String mInfo;

    public SearchResultData(String label, String info, Drawable icon) {
        mIcon = icon;
        mInfo = info;
        mLabel = label;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getLabel() {
        return mLabel;
    }

    public String getInfo() {
        return mInfo;
    }
}
