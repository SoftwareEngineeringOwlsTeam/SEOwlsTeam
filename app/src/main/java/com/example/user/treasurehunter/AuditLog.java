package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AuditLog extends AppCompatActivity
{
    PinDS pin;
    PinReader pReader = new PinReader();
    PinWriter pWriter = new PinWriter();

    public AuditLog() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String previousText = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);
        PinReader reader = new PinReader();
        TextView et2 = (TextView)findViewById(R.id.editText2);
        et2.setText(reader.readPersonalAudit(this));
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
