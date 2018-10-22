package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_iotester);
        pinView =  findViewById(R.id.pinView);
        groupView = findViewById(R.id.groupView);
        userView = findViewById(R.id.userView);

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
        String groupName = "The Name of Group";
        String adminName = "Creators Name";
        Group group = new Group(membersID, groupDescription, groupName, adminName);
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
        pin.setClassName("The Name");
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
}
