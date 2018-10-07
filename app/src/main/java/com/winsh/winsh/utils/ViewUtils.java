package com.winsh.winsh.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.winsh.winsh.R;
import com.winsh.winsh.view.activity.About.AboutActivity;
import com.winsh.winsh.view.activity.CallUs.CallUsActivity;
import com.winsh.winsh.view.activity.Main.MainActivity;
import com.winsh.winsh.view.activity.Setting.SettingsActivity;

public class ViewUtils {

    public static void setThemeFromSettings(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String themeValueStr = prefs.getString("theme_name", "0");
        int themeValue = Integer.parseInt(themeValueStr);

        switch (themeValue) {
            case 1:
                context.setTheme(R.style.Theme_RedTheme);
                break;
            case 2:
                context.setTheme(R.style.Theme_YelloTheme);
                break;
            case 3:
                context.setTheme(R.style.Theme_BlackTheme);
                break;
            case 4:
                context.setTheme(R.style.Theme_GreenTheme);
                break;
            case 0:
            default:
                break;

        }





    }

    public static void restartApp(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));

    }

    public static void setToolBar(AppCompatActivity context) {
        //set action bar
        Toolbar toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        context.setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (context.getSupportActionBar() != null) {
            context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            context.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //add title
        if (context instanceof AboutActivity)
            context.getSupportActionBar().setTitle("About");
        else if (context instanceof CallUsActivity)
            context.getSupportActionBar().setTitle("Call us");


        //add icon
        //context.getSupportActionBar().setLogo(R.drawable.logo);

    }
}
