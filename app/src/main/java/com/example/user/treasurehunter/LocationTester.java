package com.example.user.treasurehunter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LocationTester extends AppCompatActivity
{
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tester);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }

    void getLocation()
    {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
            {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null)
            {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                double altii = location.getAltitude();
                ((EditText)findViewById(R.id.etLocationLat)).setText("Latitude: " + latti);
                ((EditText)findViewById(R.id.etLocationLong)).setText("Longitude: " + longi);
                ((EditText)findViewById(R.id.etAltitude)).setText("Altitude: " + altii + " m");
            }
            else {
                ((EditText)findViewById(R.id.etLocationLat)).setText("Unable to find correct location.");
                ((EditText)findViewById(R.id.etLocationLong)).setText("Unable to find correct location. ");
                ((EditText)findViewById(R.id.etAltitude)).setText("Unable to find correct location. ");
                }
            }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }
}
