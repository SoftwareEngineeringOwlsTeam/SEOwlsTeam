package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

public class GroupInvites extends AppCompatActivity
{
    private ArrayList<String> listOfInvites = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_invites);

        for(int i = 0; i < currentActiveUser.getAssociatedGroupID().size() - 1; i++)
        {
            if(currentActiveUser.getAssociatedGroupID().get(i).charAt(0) == '%')
            {
                listOfInvites.add(currentActiveUser.getAssociatedGroupID().get(i).substring(1,11));
            }
        }

        for(int i = 0; i < listOfInvites.size() - 1; i++)
        {
            final TextView myTV = new TextView(this);

            final Button myButton = new Button(this);
            myButton.setText("Accept");

            final Button myButton2 = new Button(this);
            myButton.setText("Decline");

            myButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    removeInvite(myButton);
                    addInvite(myButton2);
                }
            });

            LinearLayout ll = findViewById(R.id.linLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myTV, lp);
            ll.addView(myButton, lp);
            ll.addView(myButton2, lp);
        }
    }

    public void removeInvite(Button button)
    {
        // Remove Text View
        // Remove Buttons
        // Remove from associations
        // Remove from members
    }

    public void addInvite(Button button)
    {
        // Remove Text View
        // Remove Buttons
        // Remove from associations
        // add propper association
        // Remove from members
        // add propper to members
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
