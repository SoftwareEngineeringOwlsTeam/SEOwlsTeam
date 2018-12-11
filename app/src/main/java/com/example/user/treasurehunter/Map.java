package com.example.user.treasurehunter;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.*;

import static java.lang.StrictMath.abs;

public class Map extends AppCompatActivity {


    public FrameLayout root;

    /* To use the application, user MUST be on Pixel 2 XL, or phone with similar
     * Resolution. This app will not work if the user is on a different phone
     * due to the resolution issues.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        root = (FrameLayout)findViewById(R.id.root);
        ImageView img1 = new ImageView(this);
        img1.setBackgroundColor(Color.RED);

        ImageView img2 = new ImageView(this);
        img2.setBackgroundColor(Color.BLUE);

        ImageView img3 = new ImageView(this);
        img3.setBackgroundColor(Color.GREEN);



        //Rowan
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(30, 30);
        params1.leftMargin = 376;
        params1.topMargin = 1576;
        root.addView(img1, params1);

        //Trenton
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(30, 30);
        params2.leftMargin = 675;
        params2.topMargin = 1088;
        root.addView(img2, params2);

        //ASRC
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(30, 30);
        params3.leftMargin = 494;
        params3.topMargin = 1323;
        root.addView(img3, params3);


    }

    private double calcLat(double lat)
    {
        return ((41.3593 - lat)/.00104671);
    }

    private double calcLong(double longitude)
    {
        return  (75.5708 - abs(longitude))/.00120164;
    }






}