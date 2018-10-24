package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewGroup extends AppCompatActivity
{
    Group currentGroup;
    String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        PinWriter writer = new PinWriter();
        PinReader reader = new PinReader();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        passedID = (String) getIntent().getSerializableExtra("id");

        TextView tvUsername = (TextView)findViewById(R.id.tvUserName);
        TextView tvGroupname = (TextView)findViewById(R.id.tvGroupName);
        TextView tvDescription = (TextView)findViewById(R.id.tvDescription);

        currentGroup = reader.retrieveGroup(this, passedID);

        tvUsername.setText(currentGroup.getAdminName());
        tvGroupname.setText(currentGroup.getGroupName());
        tvDescription.setText(currentGroup.getGroupDescription());
    }

    public void deleteGroup(View view)
    {
        PinWriter writer = new PinWriter();
        writer.removeObject(this, "groups", passedID);
        writer.removeGroupAudit(this, passedID);
        Intent locIntent = new Intent(this, GroupManager.class);
        startActivity(locIntent);
    }

    public void viewGroupAudit(View view)
    {
        Intent locIntent = new Intent(this, GroupAuditLog.class);
        locIntent.putExtra("id", passedID);
        startActivity(locIntent);
    }

    public void viewGroupMembers(View view)
    {
        Intent locIntent = new Intent(this, GroupMembers.class);
        locIntent.putExtra("id", passedID);
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
