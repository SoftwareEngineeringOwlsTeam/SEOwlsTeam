package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.FileNotFoundException;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class UserAuditLog extends AppCompatActivity
{
    private PinDS pin;
    private IOread reader = new IOread();
    private IOwrite writer = new IOwrite();

    public UserAuditLog() {}

    /**
     * Method that sets the screen to display acivity_audit_log.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);
        TextView et2 = findViewById(R.id.editText2);

        if(currentLayoutID.equals("personal"))
        {
            et2.setText(reader.readUserAudit(currentActiveUser.getUserID(), this));
        }
        else {
            et2.setText(reader.readGroupAudit(currentLayoutID, this));
        }
    }

    /**
     * Method that allows the user to move back to the MainActivity screen.
     */
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
