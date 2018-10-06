package com.winsh.winsh.view.fragment.MyTripsFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.winsh.winsh.R;

public class MyTripsFragment extends Fragment {
    public ListView mListView;
    MyTripsPresenter presenter;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public MyTripsFragment() {
    }

    public static MyTripsFragment newInstance(int SectionNumber) {
        MyTripsFragment myTripsFragment = new MyTripsFragment();
        Bundle arges = new Bundle();
        arges.putInt(ARG_SECTION_NUMBER, SectionNumber);
        myTripsFragment.setArguments(arges);

        return myTripsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_trips, container, false);
        mListView = (ListView) rootView.findViewById(R.id.list_view_trips);
        presenter = new MyTripsPresenter(this);

        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
            presenter.setPastTripAdapter();
        } else{

        }


            return rootView;

    }

}
