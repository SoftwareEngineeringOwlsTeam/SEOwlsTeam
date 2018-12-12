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

    // INCLUDE DOCUMENTATION*****************************************************
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

        IOread reader = new IOread();

        for(int i = 0; i < listOfInvites.size(); i++)
        {
            final TextView myTV = new TextView(this);
            myTV.setText("You have been invited to group: " + reader.retrieveGroup(listOfInvites.get(i), this).getGroupName());
            myTV.setHint(listOfInvites.get(i));

            final Button myButton = new Button(this);
            myButton.setText("Accept");

            final Button myButton2 = new Button(this);
            myButton2.setText("Decline");

            myButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    addInvite(myButton2, myButton, myTV);
                }
            });

            myButton2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    removeInvite(myButton, myButton2, myTV);
                }
            });

            LinearLayout ll = findViewById(R.id.linLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myTV, lp);
            ll.addView(myButton, lp);
            ll.addView(myButton2, lp);
        }
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void removeInvite(Button button, Button button2, TextView tv)
    {
        IOwrite writer = new IOwrite();
        tv.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        ArrayList<String> removingAssociation = new ArrayList<>();
        removingAssociation.add("%" + tv.getHint().toString());
        writer.removeAssociation(currentActiveUser, removingAssociation, "group", "", this);
        writer.removeObject(tv.getHint().toString() + "members", currentActiveUser.getUserID(), "%" + tv.getHint().toString(), this);
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void addInvite(Button button, Button button2, TextView tv)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        tv.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        ArrayList<String> removingAssociation = new ArrayList<>();
        removingAssociation.add("%" + tv.getHint().toString());
        writer.removeAssociation(currentActiveUser, removingAssociation, "group", "", this);
        writer.removeObject(tv.getHint().toString() + "members", "%" + currentActiveUser.getUserID(), tv.getHint().toString(), this);
        ArrayList<String> addingAssociation = new ArrayList<>();
        addingAssociation.add(tv.getHint().toString());
        writer.addAssociation(currentActiveUser, addingAssociation, "      group", "", this);
        ArrayList<String> addingUser = new ArrayList<>();
        addingUser.add(currentActiveUser.getUserID());
        ArrayList<String> addingUserName = new ArrayList<>();
        addingUserName.add(currentActiveUser.getUserName());
        ArrayList<String> addingPermission = new ArrayList<>();
        addingPermission.add("P");
        writer.writeMembers(addingUser, addingUserName, addingPermission, tv.getHint().toString(), this);
        currentActiveUser = reader.retrieveUser(currentActiveUser.getUserID(), this);
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
