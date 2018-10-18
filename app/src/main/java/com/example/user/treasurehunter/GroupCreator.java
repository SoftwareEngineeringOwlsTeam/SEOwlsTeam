package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
        ArrayList<Group> groups = ((pinArray) this.getApplication()).groups;
        EditText etUsername = (EditText)findViewById(R.id.username);
        EditText etGroupname = (EditText)findViewById(R.id.groupname);
        EditText etDescription = (EditText)findViewById(R.id.description);
        ArrayList<String> memberID = new ArrayList<String>();
        Group newGroup = new Group(memberID, etDescription.getText().toString(),
                                    etGroupname.getText().toString(), etUsername.getText().toString());
        groups.add(newGroup);
        Intent locIntent = new Intent(this, GroupManager.class);
        startActivity(locIntent);
    }
}
