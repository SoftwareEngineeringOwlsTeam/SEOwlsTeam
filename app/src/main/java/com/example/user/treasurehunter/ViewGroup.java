package com.example.user.treasurehunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewGroup extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        TextView tvUsername = (TextView)findViewById(R.id.tvUserName);
        TextView tvGroupname = (TextView)findViewById(R.id.tvGroupName);
        TextView tvDescription = (TextView)findViewById(R.id.tvDescription);
    }
}
