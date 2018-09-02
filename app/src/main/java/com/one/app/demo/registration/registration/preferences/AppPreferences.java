package com.one.app.demo.registration.registration.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pankaj Kohli on 3/28/2017.
 */
public class AppPreferences {
    private static final String KEY_PREFERENCES_NAME = "inside_track_preferences";

    /*Is user Logged In*/
    private static final String IS_DB_INITIALIZED = "is_db_initialized";


    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(KEY_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public static boolean isDbInitialized(Context context) {
        return getPreferences(context).getBoolean(IS_DB_INITIALIZED, false);
    }

    public static void setDbInitialized(Context context, boolean isInitialized) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_DB_INITIALIZED, isInitialized);
        editor.apply();
    }
}
