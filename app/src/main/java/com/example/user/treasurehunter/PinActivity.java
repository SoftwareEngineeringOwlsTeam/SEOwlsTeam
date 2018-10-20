package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        Intent classIntent = new Intent(this, location_generation.class);
        startActivity(classIntent);
    }


    public void whaleClick(View v)
    {

        whaleButton = (Button) v;
        WhalePin pin = new WhalePin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void treasureClick(View v)
    {

        treasureButton = (Button) v;
        TreasurePin pin = new TreasurePin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void shipwreckClick(View v)
    {
        shipwreckButton = (Button) v;
        Shipwreck pin = new Shipwreck();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void survivorClick(View v)
    {
        survivorButton = (Button) v;
        SurvivorPin pin = new SurvivorPin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void scavengerHuntClick(View v)
    {
        scavengerHuntButton = (Button) v;
        ScavengerHuntPin pin = new ScavengerHuntPin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void forestFireClick(View v)
    {
        forestFireButton = (Button) v;
        ForestFirePin pin = new ForestFirePin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void customClick(View v)
    {
        customButton = (Button) v;
        CustomPin pin = new CustomPin();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    public void huntingClick(View v)
    {
        huntingButton = (Button) v;
        Hunting pin = new Hunting();

        Intent mainIntent = new Intent(this, location_generation.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }
}
