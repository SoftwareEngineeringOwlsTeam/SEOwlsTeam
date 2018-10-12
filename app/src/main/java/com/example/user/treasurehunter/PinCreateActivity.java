package com.example.user.treasurehunter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import android.text.format.Time;

public class PinCreateActivity extends AppCompatActivity
{
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    private Date time = Calendar.getInstance().getTime();
    //private double latitude;
    //private double latti;
    //private double longi;
    //private double altii;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincreate2);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        ((TextView)findViewById(R.id.etTime)).setText(time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds());


        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        ((TextView)findViewById(R.id.etDate)).setText(currentDate);
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
                ((TextView)findViewById(R.id.etLocationLat)).setText("" + latti);
                ((TextView)findViewById(R.id.etLocationLong)).setText("" + longi);
                ((TextView)findViewById(R.id.etAltitude)).setText("" + altii + " m");
            }
            else {
                ((TextView)findViewById(R.id.etLocationLat)).setText("Unable to find correct location.");
                ((TextView)findViewById(R.id.etLocationLong)).setText("Unable to find correct location. ");
                ((TextView)findViewById(R.id.etAltitude)).setText("Unable to find correct location. ");
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

    public void createClicked(View view)
    {
        //PinDS thePin = new PinDS(location.getLatitude(), );
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
