package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class LogInScreen extends AppCompatActivity
{
    public static User currentActiveUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        EditText edittext = (EditText)findViewById(R.id.password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());
    }

    public void loginClicked(View view)
    {
        EditText et = findViewById(R.id.password);
        EditText et2 = findViewById(R.id.username);
        IOread reader = new IOread();
        ArrayList<String> listOfUsers = reader.existingIDs("users", this);
        for(int i = 0; i < listOfUsers.size(); i++)
        {
            User user = reader.retrieveUser(listOfUsers.get(i), this);
            if(user.getUserName().equals(et2.getText().toString()))
            {
                if(user.getPassword().equals(et.getText().toString()))
                {
                    currentActiveUser = user;
                    Intent pinIntent = new Intent(this, MainActivity.class);
                    startActivity(pinIntent);
                }
                else {
                    et2.setError("Wrong password");
                }
            }
            else {
                et2.setError("Username does not exist");
            }
        }
    }

    public void createAccountClicked(View view)
    {
        Intent pinIntent = new Intent(this, UserCreate.class);
        startActivity(pinIntent);
    }
}
