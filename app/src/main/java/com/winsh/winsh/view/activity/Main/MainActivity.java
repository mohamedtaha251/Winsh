package com.winsh.winsh.view.activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;

import com.winsh.winsh.utils.ViewUtils;
import com.winsh.winsh.view.activity.Base.BaseActivity;
import com.winsh.winsh.view.activity.About.AboutActivity;
import com.winsh.winsh.view.activity.CallUs.CallUsActivity;
import com.winsh.winsh.view.activity.Login.LoginActivity;
import com.winsh.winsh.view.activity.MyTrips.MyTripsActivity;
import com.winsh.winsh.view.activity.Setting.SettingsActivity;
import com.winsh.winsh.view.fragment.MapsFragment.MapsFragment;
import com.winsh.winsh.R;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    MapsFragment mapsFragment;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init
        drawer = findViewById(R.id.drawer_layout);
        toolbar = ViewUtils.setToolBar(this);
        mapsFragment = new MapsFragment();
        navigationView = findViewById(R.id.nav_view);


        //add mapfragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_cointiner, mapsFragment);
        fragmentTransaction.commit();


        //toggle Drawaler
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //se nav view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_trips) {
            startActivity(new Intent(this, MyTripsActivity.class));
        } else if (id == R.id.nav_call_us) {
            startActivity(new Intent(this, CallUsActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
