package com.winsh.winsh.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class ViewUtils {

    public static void setThemeFromSettings(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String themeValueStr = prefs.getString("theme_name", "0");
        int themeValue = Integer.parseInt(themeValueStr);

        switch (themeValue) {
            case 1:
                context.setTheme(android.R.style.Theme);
                break;
            case 2:
                context.setTheme(android.R.style.Theme_NoTitleBar);
                break;
            case 3:
                context.setTheme(android.R.style.Theme_Holo);
                break;
            case 0:
            default:
                break;

        }


    }
}
