package com.winsh.winsh.view.activity.Base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.winsh.winsh.R;
import com.winsh.winsh.utils.ViewUtils;
import com.winsh.winsh.view.activity.Setting.SettingsActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set theme
        ViewUtils.setThemeFromSettings(this);

        //prevent keyboard from opening in start of activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //change colors
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.yello));
            getWindow().setLogo(R.drawable.ic_menu_send);
            getWindow().setTitle("hello world");

        }

        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();


        if (id == android.R.id.home) {
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
