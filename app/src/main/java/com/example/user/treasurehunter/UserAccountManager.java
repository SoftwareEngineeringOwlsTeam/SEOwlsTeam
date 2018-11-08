package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class UserAccountManager extends AppCompatActivity
{

  TextView tvHeading;
  TextView tvUsername;
  TextView tvID;
  Button buChangePass1;
  EditText etOrigin1;
  EditText etOrigin2;
  EditText etOrigin3;
  Button buChangePass2;
  Button buChangePass3;
  Button buDeleteUser;

    /**
     * Method that sets the screen to display activity_account_manager.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        tvHeading = findViewById(R.id.tvHeading);
        tvUsername = findViewById(R.id.tvUsername);
        tvID = findViewById(R.id.tvUserID);
        buChangePass1 = findViewById(R.id.buChangePassword);
        etOrigin1 = findViewById(R.id.etOriginalPass);
        etOrigin2 = findViewById(R.id.etOriginalPass2);
        etOrigin3 = findViewById(R.id.etOriginalPass3);
        buChangePass2 = findViewById(R.id.buChangePassword2);
        buChangePass3 = findViewById(R.id.buChangePassword3);
        buDeleteUser = findViewById(R.id.buDeleteUser);

        tvUsername.setText("Username: " + currentActiveUser.getUserName());
        tvID.setText("ID: " + currentActiveUser.getUserID());
        etOrigin1.setVisibility(View.GONE);
        etOrigin2.setVisibility(View.GONE);
        etOrigin3.setVisibility(View.GONE);
        buChangePass2.setVisibility(View.GONE);
        buChangePass3.setVisibility(View.GONE);
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

    public void changePassword(View view)
    {
        tvHeading.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);
        tvID.setVisibility(View.GONE);
        buChangePass1.setVisibility(View.GONE);
        etOrigin1.setVisibility(View.VISIBLE);
        buChangePass2.setVisibility(View.VISIBLE);
        buDeleteUser.setVisibility(View.GONE);
    }

    public void changePassword2(View view)
    {
        if(etOrigin1.getText().toString().equals(currentActiveUser.getPassword()))
        {
            etOrigin1.setVisibility(View.GONE);
            etOrigin2.setVisibility(View.VISIBLE);
            etOrigin3.setVisibility(View.VISIBLE);
            buChangePass2.setVisibility(View.GONE);
            buChangePass3.setVisibility(View.VISIBLE);
        }
        else{
            etOrigin1.setError("Wrong Password");
        }
    }

    public void changePassword3(View view)
    {
        if(!etOrigin2.getText().toString().equals(etOrigin3.getText().toString()))
        {
            etOrigin3.setError("Passwords don't match");
        }
        else if(etOrigin2.getText().toString().equals(""))
        {
            etOrigin3.setError("Passwords may not be blank");
        }
        else{
            IOwrite writer = new IOwrite();
            writer.removeObject("users", currentActiveUser.getUserID(), "", this);
            currentActiveUser.setPassword(etOrigin3.getText().toString());
            writer.writeUser(currentActiveUser, this);
            tvHeading.setVisibility(View.VISIBLE);
            tvUsername.setVisibility(View.VISIBLE);
            tvID.setVisibility(View.VISIBLE);
            buChangePass1.setVisibility(View.VISIBLE);
            etOrigin2.setVisibility(View.GONE);
            etOrigin3.setVisibility(View.GONE);
            buChangePass3.setVisibility(View.GONE);
            buDeleteUser.setVisibility(View.VISIBLE);
        }
    }

    public void deleteUser(View view)
    {
        IOwrite writer = new IOwrite();
        writer.removeObject("users", currentActiveUser.getUserID(), "", this);
        Intent pinIntent = new Intent(this, LogInScreen.class);
        startActivity(pinIntent);
    }
}
