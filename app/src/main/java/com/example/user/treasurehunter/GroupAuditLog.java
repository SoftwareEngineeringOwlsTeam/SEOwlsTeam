package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class GroupAuditLog extends AppCompatActivity
{
    private String passedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_audit_log);

        passedID = (String) getIntent().getSerializableExtra("id");
        IOread reader = new IOread();
        TextView tvAudit = findViewById(R.id.tvGroupAudit);
        tvAudit.setText(reader.readGroupAudit(passedID, this));
    }

    public void returnToView(View view)
    {
        Intent locIntent = new Intent(this, GroupView.class);
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
