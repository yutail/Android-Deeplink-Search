package yutailuo.androidphonesearch.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "History.db";
    private static final int DB_VERSION = 1;

    private final Context mContext;

    private static HistoryDbHelper sHistoryDbHelper = null;

    private HistoryDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    public static HistoryDbHelper getInstance(Context context) {
        if(sHistoryDbHelper == null) {
            synchronized (HistoryDbHelper.class) {
                if (sHistoryDbHelper == null) {
                    sHistoryDbHelper = new HistoryDbHelper(context.getApplicationContext());
                }
            }
        }
        return sHistoryDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
