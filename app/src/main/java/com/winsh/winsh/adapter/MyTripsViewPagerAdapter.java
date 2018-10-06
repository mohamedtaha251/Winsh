package com.winsh.winsh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.winsh.winsh.view.fragment.MyTripsFragment.MyTripsFragment;

public class MyTripsViewPagerAdapter extends FragmentPagerAdapter {
    public MyTripsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return MyTripsFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }


}
