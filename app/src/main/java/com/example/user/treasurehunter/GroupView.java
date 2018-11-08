package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class GroupView extends AppCompatActivity
{
    Group currentGroup;
    String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        passedID = (String) getIntent().getSerializableExtra("id");

        TextView tvGroupname = findViewById(R.id.tvGroupName);
        TextView tvUsername = findViewById(R.id.tvUserName);
        TextView tvDescription = findViewById(R.id.tvDescription);

        currentGroup = reader.retrieveGroup(passedID, this);

        tvGroupname.setText(currentGroup.getGroupName());
        tvUsername.setText(currentGroup.getAdminName());
        tvDescription.setText(currentGroup.getGroupDescription());
    }

    /**
     * Method that deletes a group.
     */
    public void deleteGroup(View view)
    {
        IOwrite writer = new IOwrite();
        writer.removeObject("groups", passedID, passedID, this);
        writer.removeFile("groupaudit", passedID, this);
        writer.removeFile("members", passedID, this);
        Intent locIntent = new Intent(this, GroupManager.class);
        startActivity(locIntent);
    }
    /**
     * Method that displays the group's audit.
     */
    public void viewGroupAudit(View view)
    {
        Intent locIntent = new Intent(this, GroupAuditLog.class);
        locIntent.putExtra("id", passedID);
        startActivity(locIntent);
    }

    /**
     * Method that displays the group's members.
     */
    public void viewGroupMembers(View view)
    {
        Intent locIntent = new Intent(this, GroupMembers.class);
        locIntent.putExtra("id", passedID);
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
