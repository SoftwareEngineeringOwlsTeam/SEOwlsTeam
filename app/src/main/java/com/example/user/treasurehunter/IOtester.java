package com.example.user.treasurehunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Method is used for testing, will be removed at a later time.
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class IOtester extends AppCompatActivity
{
    IOwrite IOwrite;
    IOread IOread;
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

        IOwrite = new IOwrite();
        IOread = new IOread();
    }

    public void generateUser(View view)
    {
        String userID = "1070150293";
        String userName = "Username";
        String password = "Password";
        User user = new User(userID, userName, password);
        IOwrite.writeUser(user, this);
    }
    public void generateGroup(View view)
    {
        ArrayList<String> membersID = new ArrayList<>();
        membersID.add("9752364520");
        membersID.add("1510105971");
        membersID.add("1050177092");
        String groupDescription = "The Description";
        String adminID = "9468350125";
        String groupName = "The Name of Group";
        String adminName = "Creators Name";
        Group group = new Group(adminID, groupDescription, groupName, adminName, "1415112456");
        IOwrite.writeGroup(group,this);
    }
    public void generatePin(View view)
    {
        Random rand = new Random();
        PinClassTreasure pin = new PinClassTreasure();
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
        IOwrite.writePin(pin, this);
    }

    public void update(View view)
    {
        try {
            pinView.setText(IOread.read("pins", "", this));
            groupView.setText(IOread.read("groups", "", this));
            userView.setText(IOread.read("users", "", this));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void clear(View view)
    {
        IOwrite.clearData(this);
    }

    public void retrievePin(View view)
    {
        PinDS pin = IOread.retrievePin(pinIDer.getText().toString(), this);
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
        if(pin instanceof PinMoveable)
        {
            theLines += "\n" + "Pin Degrees: " + ((PinMoveable) pin).getDegree() + "\n" +
                               "Pin Speed: "   + ((PinMoveable) pin).getSpeed();
        }
        retrievedPin.setText(theLines);
    }

    public void retrieveGroup(View view)
    {
        Group group = IOread.retrieveGroup(groupIDer.getText().toString(), this);
        String theLines =   ("Group ID: "           + group.getGroupID()            + "\n" +
                             "Admin ID: "           + group.getAdminID()            + "\n" +
                             "Admin Name: "         + group.getAdminName()          + "\n" +
                             "Group Name: "         + group.getGroupName()          + "\n" +
                             "Group Description: "  + group.getGroupDescription());
        retrievedGroup.setText(theLines);
    }

    public void retrieveUser(View view)
    {
        User user = IOread.retrieveUser(userIDer.getText().toString(), this);
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
