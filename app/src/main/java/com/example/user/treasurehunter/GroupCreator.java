package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

public class GroupCreator extends AppCompatActivity
{
    TextView tvUsername;
    TextView tvUserID;

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

    public void createGroup(View view)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();

        EditText etDescription = findViewById(R.id.etDescription);
        EditText etTitle = findViewById(R.id.etTitle);



        /// As a test
        ArrayList<String> members = new ArrayList<String>();
        members.add("Aldin");
        members.add("Deborion");
        members.add("Estemoor");
        ArrayList<String> permissions = new ArrayList<String>();
        permissions.add("RWAD");
        permissions.add("RWA");
        permissions.add("RW");


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
        Group newGroup = new Group(tvUserID.getText().toString(), etDescription.getText().toString(),
                                    etTitle.getText().toString(), tvUsername.getText().toString(), newGroupID);
        writer.writeGroup(newGroup, this);
        writer.writeMembers(members, permissions, newGroup.getGroupID(),this);

        ArrayList<String> addingAssociation = new ArrayList<>();
        addingAssociation.add(newGroup.getGroupID());
        writer.addAssociation(addingAssociation, "group", "", this);

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
