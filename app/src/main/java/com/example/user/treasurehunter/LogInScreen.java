package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import static com.example.user.treasurehunter.MainActivity.currentLayout;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class LogInScreen extends AppCompatActivity
{
  public static User currentActiveUser;

    /**
     * Method that sets the screen to display activity_log_in_screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        EditText edittext = findViewById(R.id.password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());
    }

    /**
     * Method that, once the user clicks the button to log in, checks if the user name and password is correctly entered.
     * If the information is entered correctly the user is sent to the MainActivity. If not, the user is given an error message.
     */
    public void loginClicked(View view)
    {
        EditText et = findViewById(R.id.password);
        EditText et2 = findViewById(R.id.username);
        IOread reader = new IOread();
        ArrayList<String> listOfUsers = reader.existingIDs("users", this);
        if(listOfUsers.get(0).equals(""))
        {
            et2.setError("No Users");
        }
        else {
            boolean foundU = false;
            boolean foundP = false;
            for(int i = 0; i < listOfUsers.size(); i++)
            {
                User user = reader.retrieveUser(listOfUsers.get(i), this);
                if(user.getUserName().equals(et2.getText().toString()))
                {
                    foundU = true;
                    if(user.getPassword().equals(et.getText().toString()))
                    {
                        foundP = true;
                        currentActiveUser = user;
                        currentLayout = "";
                        currentLayoutID = "";
                        Intent pinIntent = new Intent(this, MainActivity.class);
                        startActivity(pinIntent);
                    }
                }
            }
            if(!foundU)
            {
                et2.setError("Username does not exist");
            }
            if(!foundP)
            {
                et.setError("Wrong password");
            }
        }
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void createAccountClicked(View view)
    {
        Intent pinIntent = new Intent(this, UserCreate.class);
        startActivity(pinIntent);
    }

    /**
     * Method that allows the user to close the App.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            finish();
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    // FOR TESTING, DELETE WHEN TURNING IN
    public void clearData(View view)
    {
        IOwrite writer = new IOwrite();
        writer.clearData(this);
    }
}
