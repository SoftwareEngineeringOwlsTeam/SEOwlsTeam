package com.example.user.treasurehunter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GUISplashScreen extends AppCompatActivity
{
    private int TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guisplash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(GUISplashScreen.this, LogInScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, TIMEOUT);

    }
}
