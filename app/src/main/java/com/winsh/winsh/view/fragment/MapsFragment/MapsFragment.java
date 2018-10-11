package com.winsh.winsh.view.fragment.MapsFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.winsh.winsh.R;
import com.winsh.winsh.sync.connectAsyncTask;
import com.winsh.winsh.utils.LocationUtils;
import com.winsh.winsh.utils.PathDrawer;
import com.winsh.winsh.view.activity.Request.RequestActivity;

import static android.content.Context.LOCATION_SERVICE;

public class MapsFragment extends Fragment {
    MapView mMapView;
    GoogleMap mMap;
    LocationListener mLocationListener;
    LocationManager mLocationManager;
    FloatingActionButton BtnSearch;
    FloatingActionButton BtnMechanic;
    FloatingActionButton BtnWinsh;
    DrawerLayout drawer;
    ImageButton BtnLeftMenu;
    ImageView pinButton;
    LatLng currentLatLng;
    Button BtnConfirmPickUp;
    private FusedLocationProviderClient mFusedLocationClient;


    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);


        init(rootView);
        initMapView(savedInstanceState);
        actions(rootView);

        mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, mLocationListener);

        //LocationUtils.checkPermission(getActivity());
        //LocationUtils.enableGPS(getActivity());
        //LocationUtils.getCurrentLocation(getActivity(), mLocationListener, mLocationManager);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {

                            currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            // For zooming automatically to the location of the marker
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(currentLatLng).zoom(12).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    }
                });

        return rootView;
    }

    private void initMapView(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
    }

    private void init(View rootView) {

        mMapView = rootView.findViewById(R.id.mapView);
        BtnSearch = rootView.findViewById(R.id.fab_search);
        BtnMechanic = rootView.findViewById(R.id.fab_order_mechanic);
        BtnWinsh = rootView.findViewById(R.id.fab_order_winsh);
        pinButton = rootView.findViewById(R.id.iv_left_menu);
        BtnConfirmPickUp = rootView.findViewById(R.id.btn_confirm_pickup);
        BtnLeftMenu = rootView.findViewById(R.id.iv_left_menu);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        BtnConfirmPickUp.setVisibility(View.INVISIBLE);


    }


    private void actions(View rootView) {
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                if (mMap == null)
                    return;

                LatLng curretLocation = new LatLng(location.getLatitude(), location.getLongitude());

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(curretLocation).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMyLocationEnabled(true);


            }
        });


        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toggle visibility
                BtnMechanic.setVisibility(BtnMechanic.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                BtnWinsh.setVisibility(BtnWinsh.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

            }
        });

        BtnLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        BtnMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnConfirmPickUp.setVisibility(BtnConfirmPickUp.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

            }
        });

        BtnWinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnConfirmPickUp.setVisibility(BtnConfirmPickUp.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.logo_winch);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

                LatLng latLng = new LatLng(30.1, 31.3);
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Winch")
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                );

                //make the https URL to send it and return json result
                String url = PathDrawer.makeURL(currentLatLng.latitude, currentLatLng.longitude, latLng.latitude, latLng.longitude);

                //make path drawing in async task(in background)
                new connectAsyncTask(url, getActivity(), mMap).execute();


            }
        });


        BtnConfirmPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RequestActivity.class));


//
//                myView.setVisibility(View.VISIBLE);
//                TranslateAnimation animate = new TranslateAnimation(
//                        0,                 // fromXDelta
//                        0,                 // toXDelta
//                        view.getHeight(),  // fromYDelta
//                        0);                // toYDelta
//                animate.setDuration(500);
//                animate.setFillAfter(true);
//                myView.startAnimation(animate);


            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}
