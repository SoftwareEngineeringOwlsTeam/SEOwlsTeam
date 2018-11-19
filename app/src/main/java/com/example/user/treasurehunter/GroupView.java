package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayout;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class GroupView extends AppCompatActivity
{
    private Group currentGroup;

    // INCLUDE DOCUMENTATION*****************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        IOread reader = new IOread();

        TextView tvGroupname = findViewById(R.id.tvGroupName);
        TextView tvUsername = findViewById(R.id.tvUserName);
        TextView tvDescription = findViewById(R.id.tvDescription);

        currentGroup = reader.retrieveGroup(currentLayoutID, this);

        tvGroupname.setText(currentGroup.getGroupName());
        tvUsername.setText(currentGroup.getAdminName());
        tvDescription.setText(currentGroup.getGroupDescription());

        System.out.println(reader.readGroupMemberPermission(currentActiveUser.getUserID(), currentLayoutID, this));
        if(!reader.readGroupMemberPermission(currentActiveUser.getUserID(), currentLayoutID, this).contains("A"))
        {
            findViewById(R.id.buttonMembers5).setVisibility(View.GONE);
        }
    }

    public void groupInvites(View view)
    {
        Intent locIntent = new Intent(this, GroupMemberInvite.class);
        startActivity(locIntent);
    }

    /**
     * Method that deletes a group.
     */
    public void deleteGroup(View view)
    {
        IOwrite writer = new IOwrite();
        writer.removeObject("groups", currentLayoutID, currentLayoutID, this);
        writer.removeFile("groupaudit", currentLayoutID, this);
        writer.removeFile("members", currentLayoutID, this);
        writer.removeAssociation(currentActiveUser, currentActiveUser.getAssociatedGroupID(), "ppin", currentLayoutID, this);
        currentLayoutID = "personal";
        currentLayout = "Personal";
        Intent locIntent = new Intent(this, MainActivity.class);
        startActivity(locIntent);
    }

    /**
     * Method that displays the group's members.
     */
    public void viewGroupMembers(View view)
    {
        Intent locIntent = new Intent(this, GroupMembers.class);
        locIntent.putExtra("id", currentLayoutID);
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
