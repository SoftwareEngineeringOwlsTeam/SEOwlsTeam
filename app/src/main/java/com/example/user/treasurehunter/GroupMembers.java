package com.example.user.treasurehunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GroupMembers extends AppCompatActivity
{
    private String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        passedID = (String) getIntent().getSerializableExtra("id");
        IOread reader = new IOread();
        TextView tvMembers = findViewById(R.id.tvMembers);
        tvMembers.setText(reader.readGroupMembers(passedID, this));
    }
}
