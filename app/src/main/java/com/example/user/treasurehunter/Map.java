package com.example.user.treasurehunter;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Map extends AppCompatActivity {


    /* To use the application, user MUST be on Pixel 2 XL, or phone with similar
     * Resolution. This app will not work if the user is on a different phone
     * due to the resolution issues.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        FrameLayout root = (FrameLayout)findViewById(R.id.root);
        ImageView img = new ImageView(this);
        img.setBackgroundColor(Color.RED);
//..load something inside the ImageView, we just set the background color


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(20, 20);
        params.leftMargin = 675;
        params.topMargin  = 1088;
        root.addView(img, params);
    }
}