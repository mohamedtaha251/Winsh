package com.winsh.winsh.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.winsh.winsh.R;
import com.winsh.winsh.model.entity.Trip;

import java.util.ArrayList;

public class TripListViewAdapter extends ArrayAdapter<Trip> {
    ArrayList<Trip> trips;
    Context context;

    public TripListViewAdapter(@NonNull Context context, ArrayList<Trip> trips) {
        super(context, R.layout.list_item_trip);
        this.trips=trips;
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View view1, @NonNull ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.list_item_trip,parent,false);




        return view;
    }
    @Override
    public int getCount() {
        return 10;
    }
}
