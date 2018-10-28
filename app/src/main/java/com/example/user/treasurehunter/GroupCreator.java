package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GroupCreator extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator);
    }

    public void createGroup(View view)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etUserID = findViewById(R.id.etUserID);
        EditText etDescription = findViewById(R.id.etDescription);
        EditText etTitle = findViewById(R.id.etTitle);
        ArrayList<String> memberID = new ArrayList<String>();

        /// As a test
        memberID.add("Aldin");
        memberID.add("Deborion");
        memberID.add("Estemoor");


        Group newGroup = new Group(etUserID.getText().toString(), etDescription.getText().toString(),
                                    etTitle.getText().toString(), etUsername.getText().toString());
        writer.writeGroup(newGroup, this);
        writer.writeMembersTest(newGroup.getGroupID(), reader.readGroupMembers(newGroup.getGroupID(), this), this);
        Intent locIntent = new Intent(this, GroupManager.class);
        startActivity(locIntent);
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
