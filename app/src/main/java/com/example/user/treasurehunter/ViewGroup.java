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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        String passedID = (String) getIntent().getSerializableExtra("id");

        TextView tvUsername = (TextView)findViewById(R.id.tvUserName);
        TextView tvGroupname = (TextView)findViewById(R.id.tvGroupName);
        TextView tvDescription = (TextView)findViewById(R.id.tvDescription);

        ArrayList<Group> groups = ((pinArray) this.getApplication()).groups;

        for(int i = 0; i < groups.size(); i++)
        {
            if(groups.get(i).getGroupID().equals(passedID))
            {
                currentGroup = groups.get(i);
            }
        }
        tvUsername.setText(currentGroup.getAdminName());
        tvGroupname.setText(currentGroup.getGroupName());
        tvDescription.setText(currentGroup.getGroupDescription());
    }

    public void deleteGroup(View view)
    {
        ArrayList<Group> groups = ((pinArray) this.getApplication()).groups;

        for(int i = 0; i < groups.size(); i++)
        {
            if(groups.get(i).getGroupID().equals(currentGroup.getGroupID()))
            {
                groups.remove(groups.get(i));
            }
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
