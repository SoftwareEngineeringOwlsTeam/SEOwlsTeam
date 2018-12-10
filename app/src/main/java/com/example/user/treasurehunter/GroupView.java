package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    public TextView tvGroupName;
    public TextView tvUsername;
    public TextView tvDescription;
    public EditText etGroupName;
    public EditText etDescription;
    public Button buViewMember;
    public Button buInviteMember;
    public Button buLeaveGroup;
    public Button buEditInfo;
    public Button buDelete;
    public Button buCancel;
    public Button buSave;

    /**
     *                              Creates the group view
     * @param savedInstanceState    The saved instance state of group view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        IOread reader = new IOread();

        tvGroupName = findViewById(R.id.tvGroupName);
        tvUsername = findViewById(R.id.tvUserName);
        tvDescription = findViewById(R.id.tvDescription);
        etGroupName = findViewById(R.id.etGroupName);
        etDescription = findViewById(R.id.etDescription);
        buViewMember = findViewById(R.id.buttonMembers);
        buInviteMember = findViewById(R.id.buttonMembers2);
        buLeaveGroup = findViewById(R.id.buttonMembers3);
        buEditInfo = findViewById(R.id.buttonMembers4);
        buDelete = findViewById(R.id.buttonMembers5);
        buCancel = findViewById(R.id.buttonMembers6);
        buSave = findViewById(R.id.buttonMembers7);

        currentGroup = reader.retrieveGroup(currentLayoutID, this);

        tvGroupName.setText(currentGroup.getGroupName());
        tvUsername.setText(currentGroup.getAdminName());
        tvDescription.setText(currentGroup.getGroupDescription());
        etGroupName.setText(currentGroup.getGroupName());
        etDescription.setText(currentGroup.getGroupDescription());

        etGroupName.setVisibility(View.GONE);
        etDescription.setVisibility(View.GONE);
        buCancel.setVisibility(View.GONE);
        buSave.setVisibility(View.GONE);

        if(!reader.readGroupMemberPermission(currentActiveUser.getUserID(), currentLayoutID, this).contains("A"))
        {
            buEditInfo.setVisibility(View.GONE);
            buDelete.setVisibility(View.GONE);
        }
    }

    public void groupInvites(View view)
    {
        Intent locIntent = new Intent(this, GroupMemberInvite.class);
        startActivity(locIntent);
    }

    /**
     *              This method displays the group edit page
     * @param view  Displays the group edit screen
     */
    public void groupEdit(View view)
    {
        tvGroupName.setText("");
        tvDescription.setText("");
        etGroupName.setVisibility(View.VISIBLE);
        etDescription.setVisibility(View.VISIBLE);
        buViewMember.setVisibility(View.GONE);
        buInviteMember.setVisibility(View.GONE);
        buLeaveGroup.setVisibility(View.GONE);
        buEditInfo.setVisibility(View.GONE);
        buDelete.setVisibility(View.GONE);
        buCancel.setVisibility(View.VISIBLE);
        buSave.setVisibility(View.VISIBLE);
    }

    /**
     *              This method cancels the edit to a group and does not save the edited changed
     * @param view  Displays the group edit screen
     */
    public void cancelEdit(View view)
    {
        tvGroupName.setText(currentGroup.getGroupName());
        tvDescription.setText(currentGroup.getGroupDescription());
        etGroupName.setVisibility(View.GONE);
        etDescription.setVisibility(View.GONE);
        buViewMember.setVisibility(View.VISIBLE);
        buInviteMember.setVisibility(View.VISIBLE);
        buLeaveGroup.setVisibility(View.VISIBLE);
        buEditInfo.setVisibility(View.VISIBLE);
        buDelete.setVisibility(View.VISIBLE);
        buCancel.setVisibility(View.GONE);
        buSave.setVisibility(View.GONE);
        etGroupName.setText(currentGroup.getGroupName());
        etDescription.setText(currentGroup.getGroupDescription());
    }

    /**
     *              This methods saves the edit to a group
     * @param view  Displays the group edit screen
     */
    public void saveEdit(View view)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        Group changeGroup = reader.retrieveGroup(currentLayoutID, this);
        changeGroup.setGroupName(etGroupName.getText().toString());
        changeGroup.setGroupDescription(etDescription.getText().toString());
        writer.removeObject("groups", currentLayoutID, "", this);

        etGroupName.setVisibility(View.GONE);
        etDescription.setVisibility(View.GONE);
        buViewMember.setVisibility(View.VISIBLE);
        buInviteMember.setVisibility(View.VISIBLE);
        buLeaveGroup.setVisibility(View.VISIBLE);
        buEditInfo.setVisibility(View.VISIBLE);
        buDelete.setVisibility(View.VISIBLE);
        buCancel.setVisibility(View.GONE);
        buSave.setVisibility(View.GONE);
        tvGroupName.setText(etGroupName.getText().toString());
        tvDescription.setText(etDescription.getText().toString());

        writer.writeGroup(changeGroup, this);
        currentLayout = etGroupName.getText().toString();
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
     * Displays the current layou
     */
    public void editGroupInfo(View view)
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
