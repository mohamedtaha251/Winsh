package com.winsh.winsh.view.activity.About;

import android.os.Bundle;

import com.winsh.winsh.R;
import com.winsh.winsh.utils.ViewUtils;
import com.winsh.winsh.view.activity.Base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //set tool bar
        ViewUtils.setToolBar(this);
    }
}
