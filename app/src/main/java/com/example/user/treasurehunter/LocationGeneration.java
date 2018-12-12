package com.example.user.treasurehunter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class LocationGeneration extends AppCompatActivity
{
    static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private String currentDate;
    private String currentTime;
    private Date time = Calendar.getInstance().getTime();
    private PinDS pin;

    /**
     *                              Generates the location information for a pin
     * @param savedInstanceState    The saved instace state of Locaton Genration
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tester);

        if(getIntent().getSerializableExtra("lat") != null)
        {
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            Double lati = Double.parseDouble((String)getIntent().getSerializableExtra("lat"));
            Double longi = Double.parseDouble((String)getIntent().getSerializableExtra("long"));
            Intent mainIntent = new Intent(this, Compass.class);
            mainIntent.putExtra("lat", lati);
            mainIntent.putExtra("long", longi);
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
                    mainIntent.putExtra("latY", location.getLatitude());
                    mainIntent.putExtra("longY", location.getLongitude());
                }
                else {
                    mainIntent.putExtra("latY", -1.0);
                    mainIntent.putExtra("longY", -1.0);
                }
            }
            startActivity(mainIntent);
        }
        else{
            pin = (PinDS) getIntent().getSerializableExtra("pin");
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            getLocation();
            Calendar calendar = Calendar.getInstance();
            currentTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
            pin.setTime(currentTime);
            currentDate = DateFormat.getDateInstance().format(calendar.getTime());
            pin.setDate(currentDate);
            Intent mainIntent = new Intent(this, PinCreateActivity.class);
            mainIntent.putExtra("pin", pin);
            startActivity(mainIntent);
        }
    }

    /**
     * Method to get current GPS location. This information includes, Latitude, Longitude, Altitude.
     * If the above information is not obtainable the values are set to "-1".
     * Also asks the user permission to access their GPS.
     */
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
            }
            else {
                pin.setLatitude(-1);
                pin.setLongitude(-1);
                pin.setAltitude(-1);
            }
        }
    }

    /**
     * Method that allows the user to move back to the MainActivity screen.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
