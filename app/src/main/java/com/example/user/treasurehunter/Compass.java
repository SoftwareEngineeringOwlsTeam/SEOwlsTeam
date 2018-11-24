package com.example.user.treasurehunter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class Compass extends AppCompatActivity
{
    private static final String TAG = "CompassActivity";
    private CompassExtra compass;
    private ImageView arrowView;
    private TextView sotwLabel;  // SOTW is for "side of the world"
    private float currentAzimuth;
    private CompassFormat sotwFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        sotwFormatter = new CompassFormat(this);
        arrowView = findViewById(R.id.main_image_hands);
        sotwLabel = findViewById(R.id.sotw_label);
        setupCompass();
        Double lati = (Double)getIntent().getSerializableExtra("lat");
        Double longi = (Double)getIntent().getSerializableExtra("long");
        Double latiY = (Double)getIntent().getSerializableExtra("latY");
        Double longiY = (Double)getIntent().getSerializableExtra("longY");
        Double dist = (Math.pow((Math.pow((lati - latiY), 2) + Math.pow((longi - longiY), 2)), 0.5))*(69.055);
        Double degrees = 0.0;
        String directName = "";
        if((lati - latiY) > 0 && (longi - longiY) == 0)
        {
            directName = "N";
        }
        if((lati - latiY) > 0 && (longi - longiY) > 0)
        {
            directName = "NE";
            degrees = Math.toDegrees(Math.atan2((longi - longiY) , (lati - latiY)));
            if(degrees < 0){
                degrees += 360;
            }
        }
        if((lati - latiY) == 0 && (longi - longiY) > 0)
        {
            directName = "E";
        }
        if((lati - latiY) < 0 && (longi - longiY) > 0)
        {
            directName = "SE";
            degrees = Math.toDegrees(Math.atan2((longi - longiY) , (lati - latiY)));// + 90;
            if(degrees < 0){
                degrees += 360;
            }
        }
        if((lati - latiY) < 0 && (longi - longiY) == 0)
        {
            directName = "S";
        }
        if((lati - latiY) < 0 && (longi - longiY) < 0)
        {
            directName = "SW";
            degrees = Math.toDegrees(Math.atan2((longi - longiY) , (lati - latiY)));// + 180;
            if(degrees < 0){
                degrees += 360;
            }
        }
        if((lati - latiY) == 0 && (longi - longiY) < 0)
        {
            directName = "W";
        }
        if((lati - latiY) > 0 && (longi - longiY) < 0)
        {
            directName = "NW";
            degrees = Math.toDegrees(Math.atan2((longi - longiY) , (lati - latiY)));// + 270;
            if(degrees < 0){
                degrees += 360;
            }
        }
        double dist2 = Math.round(dist * 100.0) / 100.0;
        double degrees2 = Math.round(degrees * 100.0) / 100.0;

        ((TextView)findViewById(R.id.tvDistance)).setText("Distance: " + dist2 + " miles");
        ((TextView)findViewById(R.id.tvDirection)).setText("Direction: " + degrees2 + "° " + directName);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass()
    {
        compass = new CompassExtra(this);
        CompassExtra.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth)
    {
        Log.d(TAG, "will set rotation from " + currentAzimuth + " to "
                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }

    private void adjustSotwLabel(float azimuth) {
        sotwLabel.setText(sotwFormatter.format(azimuth));
    }

    private CompassExtra.CompassListener getCompassListener() {
        return new CompassExtra.CompassListener() {
            @Override
            public void onNewAzimuth(final float azimuth) {
                // UI updates only in UI thread
                // https://stackoverflow.com/q/11140285/444966
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adjustArrow(azimuth);
                        adjustSotwLabel(azimuth);
                    }
                });
            }
        };
    }
}