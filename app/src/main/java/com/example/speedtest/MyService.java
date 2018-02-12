package com.example.speedtest;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sergey Panshyn on 09.02.2018.
 */

public class MyService extends Service {
    private LocationManager locManager;
    private LocationListener locListener = new myLocationListener();
    static final Double EARTH_RADIUS = 6371.00;

    private boolean gps_enabled = false;
    private boolean network_enabled = false;

    private Handler handler = new Handler();
    Thread t;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


    @Override
    public void onStart(Intent intent, int startid) {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getBaseContext(), "Service Started", Toast.LENGTH_SHORT).show();

        final Runnable r = new Runnable() {
            public void run() {
                Log.v("Debug", "Hello");
                location();
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(r, 5000);
        return START_STICKY;
    }

    public void location() {
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        Log.v("Debug", "in on create.. 2");
        if (gps_enabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
            Log.v("Debug", "Enabled..");
        }
        if (network_enabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locListener);
            Log.v("Debug", "Disabled..");
        }
        Log.v("Debug", "in on create..3");
    }

    private class myLocationListener implements LocationListener
    {
        double lat_old=0.0;
        double lon_old=0.0;
        double lat_new;
        double lon_new;
        double time=10;
        double speed=0.0;

        @Override
        public void onLocationChanged(Location location) {
            Log.v("Debug", "in onLocation changed..");
            if(location!=null){
                locManager.removeUpdates(locListener);
                lat_new=location.getLongitude();
                lon_new =location.getLatitude();
                String longitude = "Longitude: " +location.getLongitude();
                String latitude = "Latitude: " +location.getLatitude();
                double distance =0;
//                CalculationByDistance(lat_new, lon_new, lat_old, lon_old);
                speed = distance/time;
                Toast.makeText(getApplicationContext(), longitude+"\n"+latitude+"\nDistance is: "
                        +distance+"\nSpeedOur is: "+speed + "\nSpeed location:" + location.getSpeed(), Toast.LENGTH_SHORT).show();
                lat_old=lat_new;
                lon_old=lon_new;
            }
        }
        @Override
        public void onProviderDisabled(String provider) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

}
