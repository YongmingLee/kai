package com.example.yongming.activity.ActivitySubmodule;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.yongming.activity.BaseListActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorTestActivity extends BaseListActivity {

    private ArrayList<String> listDatas = new ArrayList<>();


    private LocationManager locationManager;
    private SensorManager sensorManager;


    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.i("statest", "onLocationChanged");
            Toast.makeText(SensorTestActivity.this, "LBS changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.i("statest", "onStatusChanged: " + s + " i : " + i + " ");
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.i("statest", "onProviderEnabled : " + s);
        }

        @Override
        public void onProviderDisabled(String s) {

            Log.i("statest", "onProviderDisabled : " + s);
        }
    };

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.i("sensortest", "onSensorChanged");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            Log.i("sensortest", "onAccuracyChanged");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        listDatas.add("地理位置测试");
        listDatas.add("光照传感器");

        super.onCreate(savedInstanceState);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public ArrayList<String> CustomDataSource() {
        return listDatas;
    }

    @Override
    public void OnListItemClick(int position) {
        super.OnListItemClick(position);

        switch (position) {

            case 0:

                testLocation();

                break;

            case 1:

                testLightSensor();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        locationManager.removeUpdates(locationListener);
        sensorManager.unregisterListener(sensorEventListener);
    }

    private void testLocation()
    {
        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            String provider = LocationManager.GPS_PROVIDER;
            Location location = locationManager.getLastKnownLocation(provider);

            Log.i("statest", "l:" + location.getLatitude() + "j:" + location.getLongitude());

            List<String> providerList = locationManager.getProviders(true);

            for (String pp : providerList) {
                Log.i("statest", "provider: " + pp);
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void testLightSensor()
    {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
