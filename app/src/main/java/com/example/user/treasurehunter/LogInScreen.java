package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class LogInScreen extends AppCompatActivity {

    /**
     * Method that sets the screen to display activity_log_in_screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

    /**
     * Method that, once the user clicks the button to log in, checks if the user name and password is correctly entered.
     * If the information is entered correctly the user is sent to the MainActivity. If not, the user is given an error message.
     */
    public void loginClicked(View view)
    {
        EditText et = findViewById(R.id.password);
        EditText et2 = findViewById(R.id.username);
        if(et.getText().toString().equals("owls"))
        {
            if(!(et2.getText().toString().equals("")))
            {
                Intent pinIntent = new Intent(this, MainActivity.class);
                startActivity(pinIntent);
            }
            else
            {
                et2.setError("Input Username");
            }
        }
        else
        {
            et.setError("Wrong Password");
        }
    }


}
