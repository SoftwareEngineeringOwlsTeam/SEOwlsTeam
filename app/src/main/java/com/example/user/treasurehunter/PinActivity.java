package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.annotation.TransitionRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class PinActivity extends AppCompatActivity
{
    Button whaleButton;
    Button treasureButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        whaleButton = findViewById(R.id.button4);
        treasureButton = findViewById(R.id.button3);
    }

    public void classClicked(MenuItem menuItem)
    {
        Intent classIntent = new Intent(this, location_generation.class);
        startActivity(classIntent);
    }

    public void buttonOnClick(View v)
    {
        Button button = (Button) v;
        Intent pinIntent = new Intent(this, location_generation.class);
        startActivity(pinIntent);
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
}
