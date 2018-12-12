package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class GroupCreator extends AppCompatActivity
{
    private TextView tvUsername;
    private TextView tvUserID;

    /**
     * Method that sets the screen to display activity_group_creator.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator);
        tvUsername = findViewById(R.id.tvUsername);
        tvUserID = findViewById(R.id.tvUserID);
        tvUsername.setText(currentActiveUser.getUserName());
        tvUserID.setText(currentActiveUser.getUserID());
    }

    /**
     * Method that creates a Group.
     */
    public void createGroup(View view)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();

        EditText etDescription = findViewById(R.id.etDescription);
        EditText etTitle = findViewById(R.id.etTitle);

        ArrayList<String> membersIDs = new ArrayList<>();
        membersIDs.add(currentActiveUser.getUserID());
        ArrayList<String> membersNames = new ArrayList<>();
        membersNames.add(currentActiveUser.getUserName());
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("ADUMP");

        boolean generated = false;
        String newGroupID = "";
        ArrayList<String> existingIDs = reader.existingIDs("groups", this);
        while (!generated)
        {
            Random rand = new Random();
            for(int j = 0; j <= 9; j++)
            {
                newGroupID += String.valueOf(rand.nextInt(9));
            }
            generated = true;
            for(int i = 0; i < existingIDs.size(); i++)
            {
                if(existingIDs.get(i).equals(newGroupID))
                {
                    generated = false;
                }
            }
        }
        Group newGroup = new Group(currentActiveUser.getUserID(), etDescription.getText().toString(),
                                    etTitle.getText().toString(), currentActiveUser.getUserName(), newGroupID);
        writer.writeGroup(newGroup, this);
        writer.writeMembers(membersIDs, membersNames, permissions, newGroup.getGroupID(),this);

        writer.writeGroupAudit(1, newGroupID,0,currentActiveUser,"","",this);
        writer.writeUserAudit(currentActiveUser.getUserID(),1, "", newGroupID, this);

        ArrayList<String> addingAssociation = new ArrayList<>();
        addingAssociation.add(newGroup.getGroupID());
        writer.addAssociation(currentActiveUser, addingAssociation, "group", "", this);

        Intent locIntent = new Intent(this, MainActivity.class);
        startActivity(locIntent);
    }

    /**
     * Method that allows the user to move back to the MainActivity screen.
     */
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
