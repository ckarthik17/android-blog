package com.ckarthik.blog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends Activity implements LocationListener {
    private Location latestLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initLocationListener();
    }

    private void initLocationListener() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);;
        List<String> allProviders = locationManager.getAllProviders();
        for (String provider : allProviders) {
            locationManager.requestLocationUpdates(provider, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latestLocation = location;
    }

    public Location latestLocation() {
        return latestLocation;
    }

    public void onButtonClick(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderDisabled(String s) {}

    @Override
    public void onProviderEnabled(String s) {}
}
