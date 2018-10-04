package com.winsh.winsh.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.LOCATION_SERVICE;

public class LocationUtils {

    public static boolean isGpsEnabled(Context context) {
        int off = 0;
        try {
            off = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return off != 0;

    }

    public static void openLocationSetting(Context context) {
        Intent onGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(onGPS);

    }

    public static boolean isPermissionGranted(Context context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    }

    public static void checkPermission(Activity activity) {
        if (!isPermissionGranted(activity))
            requestPermission(activity);
    }

    @SuppressLint("MissingPermission")
    public static void getCurrentLocation(Context context, LocationListener mLocationListener, LocationManager mLocationManager) {

        mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        checkPermission((Activity) context);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        Log.d("taha","get current Location");
    }

    @SuppressLint("MissingPermission")
    public static LatLng getCurrentLatLng(final Context context) {
        final LatLng[] latLng = {new LatLng(0, 0)};
        LocationManager mLocationManager;
        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latLng[0] = new LatLng(location.getLatitude(), location.getLongitude());

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


        mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        checkPermission((Activity) context);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, mLocationListener);

        return latLng[0];
    }

    public static void initializeMap(Context context) {
        try {
            MapsInitializer.initialize(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableGPS(Context context) {

        if (!LocationUtils.isGpsEnabled(context)) {
            LocationUtils.openLocationSetting(context);

        }
    }

    public static void drawPathBetween2points(LatLng source,LatLng destination)
    {

    }


}
