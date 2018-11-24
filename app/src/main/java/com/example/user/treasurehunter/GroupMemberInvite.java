package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

        IOread reader = new IOread();

        ArrayList<String> listOfUsers = reader.existingIDs("users", this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listOfUsers);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                memInput1;
        textView.setAdapter(adapter);
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
        ArrayList<String> checkedUsersIDs = new ArrayList<>();
        ArrayList<String> addingUsersIDs = new ArrayList<>();
        ArrayList<String> addingUsersNames = new ArrayList<>();
        ArrayList<String> addingUsersPermissions = new ArrayList<>();
        ArrayList<String> listOfUsers = reader.existingIDs("users", this);
        if(!memInput1.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput1.getText().toString());
        }
        if(!memInput2.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput2.getText().toString());
        }
        if(!memInput3.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput3.getText().toString());
        }
        if(!memInput4.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput4.getText().toString());
        }
        if(!memInput5.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput5.getText().toString());
        }
        if(!memInput6.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput6.getText().toString());
        }
        if(!memInput7.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput7.getText().toString());
        }
        if(!memInput8.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput8.getText().toString());
        }
        if(!memInput9.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput9.getText().toString());
        }
        if(!memInput10.getText().toString().equals(""))
        {
            checkedUsersIDs.add(memInput10.getText().toString());
        }
        for(int j = 0; j < checkedUsersIDs.size(); j++)
        {
            for(int i = 0; i < listOfUsers.size(); i++)
            {
                addingUsersIDs.add("%" + addingUsersIDs.get(j));
                addingUsersNames.add(reader.retrieveUser(checkedUsersIDs.get(j), this).getUserName());
                addingUsersPermissions.add("P");
                ArrayList<String> temp = new ArrayList<>();
                temp.add("%" + currentLayoutID);
                writer.addAssociation(reader.retrieveUser(checkedUsersIDs.get(j), this), temp, "groupinvite", "",this);
            }
        }
        writer.writeMembers(addingUsersIDs, addingUsersNames, addingUsersPermissions, currentLayoutID, this);
        Intent mainIntent = new Intent(this, GroupView.class);
        startActivity(mainIntent);
    }

    /**
     * Method that allows the user to move back to the MainActivity screen.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent mainIntent = new Intent(this, GroupView.class);
            startActivity(mainIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
