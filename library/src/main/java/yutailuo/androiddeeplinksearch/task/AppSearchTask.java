package yutailuo.androiddeeplinksearch.task;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSearchTask implements ISearchTask {

    private static final String TAG = "AppSearchTask";

    private String mQuery;

    public AppSearchTask(Context context, String query) {
        mQuery = query;
    }

    @Override
    public void submitQuery(String query) {

    }


    @Override
    public void run() {

    }
}
