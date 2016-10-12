package yutailuo.androiddeeplinksearch.util;

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

/**
 * Created by yutailuo on 10/12/16.
 */

public class AppSearchUtils {

    private static String TAG = "AppSearchUtils";

    private static volatile Map<ComponentName, String> sAppCache = null;

    public static synchronized Map<ComponentName, String> getAppCache(Context context) {
        if (sAppCache != null) {
            return sAppCache;
        }

        Map<ComponentName, String> appCache = new HashMap<>();
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> launcherActivities = findLauncherActivities(pm);
        for (ResolveInfo info : launcherActivities) {
            String name = info.activityInfo.name;
            String packageName = info.activityInfo.packageName;
            if (packageName.equals(context.getPackageName())) {
                continue;
            }
            ComponentName cn = new ComponentName(packageName, name);
            String label = info.activityInfo.loadLabel(pm).toString();
            appCache.put(cn, label);
        }
        sAppCache = appCache;
        return appCache;
    }

    private static List<ResolveInfo> findLauncherActivities(PackageManager pm) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        try {
            return pm.queryIntentActivities(mainIntent, 0);
        } catch (RuntimeException e) {
            Log.e(TAG, e.toString());
            return Collections.emptyList();
        }
    }
}
