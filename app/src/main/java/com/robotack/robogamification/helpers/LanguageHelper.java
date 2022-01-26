package com.robotack.robogamification.helpers;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class LanguageHelper {

    public static String getCurrentLanguage (Context context)
    {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String lang = preferences.getString(SharedPrefConstants.Language, "en");
        if (lang.contains("en"))
        {
            return "en";
        }else
        {
            return "ar";
        }
    }
}
