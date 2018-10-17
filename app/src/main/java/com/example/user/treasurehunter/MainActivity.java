package com.example.user.treasurehunter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    static final int REQUEST_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);

        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    /*
    private File getTempFile(Context context, String url) {
        File file;
        try
        {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        }
        catch (IOException e)
        {
            // Error while creating file
        }
        return file;
    }
    */

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

    public void auditClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, AuditLog.class);
        startActivity(locIntent);
    }

    public void logoutClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, LogInScreen.class);
        startActivity(locIntent);
    }

    public void accountClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, AccountManager.class);
        startActivity(locIntent);
    }

    public void settingsClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, Settings.class);
        startActivity(locIntent);
    }

    public void groupsClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, GroupManager.class);
        startActivity(locIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                break;
        }
    }


}


