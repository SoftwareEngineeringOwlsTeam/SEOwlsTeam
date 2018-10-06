package com.example.user.treasurehunter;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
    }

    public void classClicked(MenuItem menuItem)
    {
        Intent classIntent = new Intent(this, PinCreateActivity.class);
        startActivity(classIntent);
    }

    public void buttonOnClick(View v)
    {
        Button button = (Button) v;
        Intent pinIntent = new Intent(this, PinCreateActivity.class);
        startActivity(pinIntent);
    }
}
