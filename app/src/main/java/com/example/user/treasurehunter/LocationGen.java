package com.example.user.treasurehunter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocationGen extends AppCompatActivity
{
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    double lati;
    double longi;
    double alti;
    String currentDate;
    String currentTime;
    private Date time = Calendar.getInstance().getTime();
    PinDS pin = new TreasurePin();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.pin = pin;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tester);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();

        Calendar calendar = Calendar.getInstance();
        currentTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        pin.setTime(currentTime);
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        pin.setDate(currentDate);

        Intent mainIntent = new Intent(this, PinCreateActivity.class);
        mainIntent.putExtra("pin", (Serializable) pin);
        startActivity(mainIntent);

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
                pin.setLatitude(location.getLatitude());
                pin.setLongitude(location.getLongitude());
                pin.setAltitude(location.getAltitude());
                //lati = location.getLatitude();
                //longi = location.getLongitude();
                //alti = location.getAltitude();
                //((EditText)findViewById(R.id.etLocationLat)).setText("Latitude: " + latti);
                //((EditText)findViewById(R.id.etLocationLong)).setText("Longitude: " + longi);
                //((EditText)findViewById(R.id.etAltitude)).setText("Altitude: " + altii + " m");
            }
            else {
                pin.setLatitude(-1);
                pin.setLongitude(-1);
                pin.setAltitude(-1);
                //((EditText)findViewById(R.id.etLocationLat)).setText("Unable to find correct location.");
                //((EditText)findViewById(R.id.etLocationLong)).setText("Unable to find correct location. ");
                //((EditText)findViewById(R.id.etAltitude)).setText("Unable to find correct location. ");
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
