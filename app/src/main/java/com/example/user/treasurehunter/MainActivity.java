package com.example.user.treasurehunter;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    //private int TIMEOUT = 3000;
    //ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Intent mainIntent = new Intent(this, GUISplashScreen.class);
        //startActivity(mainIntent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);

        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*

        listView = (ListView)findViewById(R.id.Listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,android.R.id.text1,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                if(position == 0)
                {
                    Intent myIntent = new Intent(view.getContext(),Main2Activity.class);
                    startActivityForResult(myintent,0);
                }
                if(position == 1)
                {
                    Intent myIntent = new Intent(view.getContext(),Main2Activity.class);
                    startActivityForResult(myintent,1);
                }
                if(position == 2)
                {
                    Intent myIntent = new Intent(view.getContext(),Main2Activity.class);
                    startActivityForResult(myintent,2);
                }
            }
        });

        */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(nToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pinsClicked(MenuItem menuItem)
    {
        Intent pinIntent = new Intent(this, PinActivity.class);
        startActivity(pinIntent);
    }

    public void testClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, LocationTester.class);
        startActivity(locIntent);
    }
}
