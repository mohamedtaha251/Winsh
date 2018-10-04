package com.winsh.winsh.view.fragment.SmallMapFragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.winsh.winsh.R;
import com.winsh.winsh.utils.LocationUtils;
import com.winsh.winsh.view.fragment.MapsFragment.MapsFragment;

public class SmallMapFragment extends Fragment {
    MapView mMapView;
    private GoogleMap mMap;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_small_map, container, false);

        mMapView = rootView.findViewById(R.id.small_map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // For zooming automatically to the location of the marker
                LatLng curretLocation = new LatLng(30.1, 31.3);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(curretLocation).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return rootView;
    }
}
