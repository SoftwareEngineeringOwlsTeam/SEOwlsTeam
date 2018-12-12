package com.example.user.treasurehunter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class GUISplashScreen extends AppCompatActivity
{
    private int TIMEOUT = 2000;

    /**
     * Method that sets the screen to display activity_guisplash_screen. A delay is also put in place to make sure the logo is displayed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guisplash_screen);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent homeIntent = new Intent(GUISplashScreen.this, LogInScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, TIMEOUT);
    }

    /**
     * Method that allows the user to close the App.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            finish();
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
