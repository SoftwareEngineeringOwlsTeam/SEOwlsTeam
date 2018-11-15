package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

public class GroupMemberInvite extends AppCompatActivity
{
    public EditText memInput1;
    public EditText memInput2;
    public EditText memInput3;
    public EditText memInput4;
    public EditText memInput5;
    public EditText memInput6;
    public EditText memInput7;
    public EditText memInput8;
    public EditText memInput9;
    public EditText memInput10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_invite);

        memInput1 = findViewById(R.id.etNewMem);
        memInput2 = findViewById(R.id.etNewMem2);
        memInput3 = findViewById(R.id.etNewMem3);
        memInput4 = findViewById(R.id.etNewMem4);
        memInput5 = findViewById(R.id.etNewMem5);
        memInput6 = findViewById(R.id.etNewMem6);
        memInput7 = findViewById(R.id.etNewMem7);
        memInput8 = findViewById(R.id.etNewMem8);
        memInput9 = findViewById(R.id.etNewMem9);
        memInput10 = findViewById(R.id.etNewMem10);

        memInput2.setVisibility(View.GONE);
        memInput3.setVisibility(View.GONE);
        memInput4.setVisibility(View.GONE);
        memInput5.setVisibility(View.GONE);
        memInput6.setVisibility(View.GONE);
        memInput7.setVisibility(View.GONE);
        memInput8.setVisibility(View.GONE);
        memInput9.setVisibility(View.GONE);
        memInput10.setVisibility(View.GONE);
    }

    public void addInput(View view)
    {
        if(memInput2.getVisibility() == View.GONE)
        {
            memInput2.setVisibility(View.VISIBLE);
        }
        else if(memInput3.getVisibility() == View.GONE)
        {
            memInput3.setVisibility(View.VISIBLE);
        }
        else if(memInput4.getVisibility() == View.GONE)
        {
            memInput4.setVisibility(View.VISIBLE);
        }
        else if(memInput5.getVisibility() == View.GONE)
        {
            memInput5.setVisibility(View.VISIBLE);
        }
        else if(memInput6.getVisibility() == View.GONE)
        {
            memInput6.setVisibility(View.VISIBLE);
        }
        else if(memInput7.getVisibility() == View.GONE)
        {
            memInput7.setVisibility(View.VISIBLE);
        }
        else if(memInput8.getVisibility() == View.GONE)
        {
            memInput8.setVisibility(View.VISIBLE);
        }
        else if(memInput9.getVisibility() == View.GONE)
        {
            memInput9.setVisibility(View.VISIBLE);
        }
        else if(memInput10.getVisibility() == View.GONE)
        {
            memInput10.setVisibility(View.VISIBLE);
        }
        else{
            memInput10.setError("Can add up to 10");
        }
    }

    public void inviteMembers(View view)
    {
        IOread reader = new IOread();
        IOwrite writer = new IOwrite();
        ArrayList<String> addingUsersIDs = new ArrayList<>();
        ArrayList<String> addingUsersNames = new ArrayList<>();
        ArrayList<String> addingUsersPermissions = new ArrayList<>();
        ArrayList<String> listOfUsers = reader.existingIDs("users", this);
        for(int i = 0; i < listOfUsers.size() - 1; i++)
        {
            String idEntered = memInput1.getText().toString();
            if(idEntered.length() == 10 && idEntered.equals(listOfUsers.get(i)))
            {
                addingUsersIDs.add("%" + idEntered);
                addingUsersNames.add(reader.retrieveUser(idEntered, this).getUserName());
                addingUsersPermissions.add("P");
                ArrayList<String> temp = new ArrayList<>();
                temp.add("%" + currentLayoutID);
                writer.addAssociation(idEntered, temp, "groupinvite", "",this);
            }
        }
        writer.writeMembers(addingUsersIDs, addingUsersNames, addingUsersPermissions, currentLayoutID, this);
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
