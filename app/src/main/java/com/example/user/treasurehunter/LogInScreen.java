package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LogInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

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
