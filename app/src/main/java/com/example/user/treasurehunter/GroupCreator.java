package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
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
        PinWriter writer = new PinWriter();
        PinReader reader = new PinReader();
        EditText etUsername = (EditText)findViewById(R.id.etUsername);
        EditText etUserID = (EditText)findViewById(R.id.etUserID);
        EditText etDescription = (EditText)findViewById(R.id.etDescription);
        EditText etTitle = (EditText)findViewById(R.id.etTitle);
        ArrayList<String> memberID = new ArrayList<String>();
        Group newGroup = new Group(memberID, etUserID.getText().toString(), etDescription.getText().toString(),
                                    etTitle.getText().toString(), etUsername.getText().toString());
        try
        {
            writer.writeGroup(newGroup, reader.read(this, "Groups", ""), this);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
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
