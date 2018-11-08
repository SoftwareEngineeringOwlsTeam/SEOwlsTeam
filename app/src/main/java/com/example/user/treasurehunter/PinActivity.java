package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinActivity extends AppCompatActivity
{
    Button whaleButton;
    Button treasureButton;
    Button shipwreckButton;
    Button survivorButton;
    Button scavengerHuntButton;
    Button forestFireButton;
    Button customButton;
    Button huntingButton;


    /**
     * Method that displays a screen to allow a user to select a type of Pin to create.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        whaleButton = findViewById(R.id.button4);
        treasureButton = findViewById(R.id.button3);
        shipwreckButton = findViewById(R.id.button1);
        survivorButton = findViewById(R.id.button2);
        scavengerHuntButton = findViewById(R.id.button);
        forestFireButton = findViewById(R.id.forrestFireButton);
        customButton = findViewById(R.id.customButton);
        huntingButton = findViewById(R.id.huntingButton);
    }

    public void classClicked(MenuItem menuItem)
    {
        Intent classIntent = new Intent(this, LocationGeneration.class);
        startActivity(classIntent);
    }

    /**
     * If the user selects Whale Pin they will be sent to a screen to create a new Whale Pin.
     */
    public void whaleClick(View v)
    {

        whaleButton = (Button) v;
        //New Pin of the correct type is created.
        PinMoveableClassWhale pin = new PinMoveableClassWhale();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects Treasure Pin they will be sent to a screen to create a new Treasure Pin.
     */
    public void treasureClick(View v)
    {

        treasureButton = (Button) v;
        //New Pin of the correct type is created.
        PinClassTreasure pin = new PinClassTreasure();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects ShipWreck Pin they will be sent to a screen to create a new ShipWreck Pin.
     */
    public void shipwreckClick(View v)
    {
        shipwreckButton = (Button) v;
        //New Pin of the correct type is created.
        PinClassShipwreck pin = new PinClassShipwreck();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects Survivor Pin they will be sent to a screen to create a new Survivor Pin.
     */
    public void survivorClick(View v)
    {
        survivorButton = (Button) v;
        //New Pin of the correct type is created.
        PinMoveableClassSurvivor pin = new PinMoveableClassSurvivor();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects ScavengerHunter Pin they will be sent to a screen to create a new ScavengerHunt Pin.
     */
    public void scavengerHuntClick(View v)
    {
        scavengerHuntButton = (Button) v;
        //New Pin of the correct type is created.
        PinClassScavengerHunt pin = new PinClassScavengerHunt();
        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects ForestFire Pin they will be sent to a screen to create a new ForestFire Pin.
     */
    public void forestFireClick(View v)
    {
        forestFireButton = (Button) v;
        //New Pin of the correct type is created.
        PinMoveableClassForestFire pin = new PinMoveableClassForestFire();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects Custom Pin they will be sent to a screen to create a new Custom Pin.
     */
    public void customClick(View v)
    {
        customButton = (Button) v;
        //New Pin of the correct type is created.
        PinMoveableClassCustom pin = new PinMoveableClassCustom();

        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    /**
     * If the user selects Hunting Pin they will be sent to a screen to create a new Hunting Pin.
     */
    public void huntingClick(View v)
    {
        huntingButton = (Button) v;
        PinMoveableClassHunting pin = new PinMoveableClassHunting();
        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

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