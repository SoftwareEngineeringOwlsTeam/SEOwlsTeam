package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class UserAuditLog extends AppCompatActivity
{
    PinDS pin;
    IOread pReader = new IOread();
    IOwrite pWriter = new IOwrite();

    public UserAuditLog() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String previousText = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);
        IOread reader = new IOread();
        TextView et2 = findViewById(R.id.editText2);
        et2.setText(reader.readUserAudit("", this));
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
