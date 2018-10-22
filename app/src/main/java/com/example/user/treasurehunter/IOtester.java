package com.example.user.treasurehunter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class IOtester extends AppCompatActivity
{
    PinWriter pinWriter;
    PinReader pinReader;
    TextView pinView;
    TextView groupView;
    TextView userView;
    TextView retrievedPin;
    TextView retrievedGroup;
    TextView retrievedUser;
    EditText pinIDer;
    EditText groupIDer;
    EditText userIDer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_iotester);
        pinView =  findViewById(R.id.pinView);
        groupView = findViewById(R.id.groupView);
        userView = findViewById(R.id.userView);
        retrievedPin = findViewById(R.id.retrievedPin);
        retrievedGroup = findViewById(R.id.retrievedGroup);
        retrievedUser = findViewById(R.id.retrievedUser);
        pinIDer = findViewById(R.id.pinIDer);
        groupIDer = findViewById(R.id.groupIDer);
        userIDer = findViewById(R.id.userIDer);

        pinWriter = new PinWriter();
        pinReader = new PinReader();
    }

    public void generateUser(View view)
    {
        String userID = "1070150293";
        String userName = "Username";
        String password = "Password";
        User user = new User(userID, userName, password);
        try
        {
            pinWriter.writeUser(user, pinReader.read(this, "Users", ""), this);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void generateGroup(View view)
    {
        ArrayList<String> membersID = new ArrayList<String>();
        membersID.add("9752364520");
        membersID.add("1510105971");
        membersID.add("1050177092");
        String groupDescription = "The Description";
        String adminID = "9468350125";
        String groupName = "The Name of Group";
        String adminName = "Creators Name";
        Group group = new Group(membersID, adminID, groupDescription, groupName, adminName);
        try
        {
            pinWriter.writeGroup(group, pinReader.read(this, "Groups", ""), this);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void generatePin(View view)
    {
        Random rand = new Random();
        TreasurePin pin = new TreasurePin();
        pin.setPinID("0386579654");
        pin.setPinName("Pin Name");
        pin.setPinTitle("The Name");
        pin.setPublisher("Publisher");
        pin.setColor("Color");
        pin.setDescription("Description");
        pin.setRadius("Radius");
        pin.setLatitude(rand.nextInt(200) - 100);
        pin.setLongitude(rand.nextInt(200) - 100);
        pin.setAltitude(rand.nextInt(200) - 100);
        pin.setTime("Time");
        pin.setDate("Date");
        try
        {
            pinWriter.writePin(pin, pinReader.read(this, "PersonalPins", ""), this);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void update(View view)
    {
        try {
            pinView.setText(pinReader.read(this, "PersonalPins", ""));
            groupView.setText(pinReader.read(this, "Groups", ""));
            userView.setText(pinReader.read(this, "Users", ""));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void clear(View view)
    {
        pinWriter.clearData(this);
    }

    public void retrievePin(View view)
    {
        PinDS pin = pinReader.retrievePin(this,pinIDer.getText().toString());
        String theLines =   ("Pin ID: "      + pin.getPinID()       + "\n" +
                             "Pin Name: "    + pin.getPinName()     + "\n" +
                             "Pin Title: "   + pin.getPinTitle()    + "\n" +
                             "Publisher: "   + pin.getPublisher()   + "\n" +
                             "Description: " + pin.getDescription() + "\n" +
                             "Radius: "      + pin.getRadius()      + "\n" +
                             "Latitude: "    + pin.getLatitude()    + "\n" +
                             "Longitude: "   + pin.getLongitude()   + "\n" +
                             "Altitude: "    + pin.getAltitude()    + "\n" +
                             "Time: "        + pin.getTime()        + "\n" +
                             "Date: "        + pin.getDate());
        if(pin instanceof MoveablePin)
        {
            theLines += "\n" + "Pin Degrees: " + ((MoveablePin) pin).getDegree() + "\n" +
                               "Pin Speed: "   + ((MoveablePin) pin).getSpeed();
        }
        retrievedPin.setText(theLines);
    }

    public void retrieveGroup(View view)
    {
        Group group = pinReader.retrieveGroup(this,groupIDer.getText().toString());
        String theLines =   ("Group ID: "           + group.getGroupID()            + "\n" +
                             "Admin ID: "           + group.getAdminID()            + "\n" +
                             "Admin Name: "         + group.getAdminName()          + "\n" +
                             "Group Name: "         + group.getGroupName()          + "\n" +
                             "Group Description: "  + group.getGroupDescription()   + "\n" +
                             "Group Memebr ID's "   + group.getMembersID());
        retrievedGroup.setText(theLines);
    }

    public void retrieveUser(View view)
    {
        User user = pinReader.retrieveUser(this,userIDer.getText().toString());
        String theLines =   ("User ID: "           + user.getUserID()       + "\n" +
                             "Username: "          + user.getUserName()     + "\n" +
                             "Password: "          + user.getPassword()     + "\n" +
                             "Associated Groups: " + user.getAssociatedGroupID());
        retrievedUser.setText(theLines);
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
