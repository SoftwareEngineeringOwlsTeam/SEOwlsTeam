package com.example.user.treasurehunter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
{
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    static final int REQUEST_LOCATION = 1;
    ArrayList<String> groupSpinner = new ArrayList<String>();
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nDrawerLayout = findViewById(R.id.drawerLayout);
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
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));




        IOread reader = new IOread();
        Spinner idselector = findViewById(R.id.spinner2);
        idselector.setOnItemSelectedListener(this);

        groupSpinner.add("Personal");
        for(int i = 0; i < currentActiveUser.getAssociatedGroupID().size() - 1; i++)
        {
            if(!currentActiveUser.getAssociatedGroupID().get(i).equals("null"))
            {
                groupSpinner.add(currentActiveUser.getAssociatedGroupID().get(i));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                groupSpinner
        );
        idselector.setAdapter(adapter);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        selected = item;
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void pinsClicked(MenuItem menuItem)
    {
        Intent pinIntent = new Intent(this, PinActivity.class);
        startActivity(pinIntent);
    }

    public void testClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, IOtester.class);
        startActivity(locIntent);
    }

    public void auditClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, UserAuditLog.class);
        startActivity(locIntent);
    }

    public void logoutClicked(MenuItem menuItem)
    {
        currentActiveUser = null;
        Intent locIntent = new Intent(this, LogInScreen.class);
        startActivity(locIntent);
    }

    public void accountClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, UserAccountManager.class);
        startActivity(locIntent);
    }

    public void settingsClicked(MenuItem menuItem)
    {
        Intent locIntent = new Intent(this, UserSettings.class);
        startActivity(locIntent);
    }

    public void groupsClicked(MenuItem menuItem)
    {
    Intent locIntent = new Intent(this, GroupCreator.class);
    startActivity(locIntent);
    }

    public void groupViewClicked(MenuItem menuItem)
    {
        if(!selected.equals("Personal"))
        {
            Intent locIntent = new Intent(this, GroupView.class);
            locIntent.putExtra("id", selected);
            startActivity(locIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                break;
        }
    }

    /**
     * Method that allows the user to exit the App.
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
