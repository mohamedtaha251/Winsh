package com.winsh.winsh.view.fragment.MyTripsFragment;

import com.winsh.winsh.adapter.TripListViewAdapter;
import com.winsh.winsh.model.entity.Trip;

import java.util.ArrayList;
import java.util.List;

public class MyTripsPresenter {
    private MyTripsFragment view;

    public MyTripsPresenter(MyTripsFragment view) {
        this.view = view;
    }

    public void setPastTripAdapter()
    {
        ArrayList<Trip> trips=new ArrayList<>();
        trips.add(new Trip());
        TripListViewAdapter adapter=new TripListViewAdapter(view.getActivity(),trips);
        view.mListView.setAdapter(adapter);
    }
}
