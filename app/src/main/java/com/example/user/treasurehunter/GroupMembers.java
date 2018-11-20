package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class GroupMembers extends AppCompatActivity
{

    /**
     * Method that sets the screen to display activity_group_members.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        IOread reader = new IOread();
        ArrayList<String> members = reader.readGroupMembers(currentLayoutID, this);
        for(int i = 0; i < members.size(); i++)
        {
            String[] foundLine = members.get(i).split("\\*",14);

            final TableRow row = new TableRow(this);

            TableLayout tl = findViewById(R.id.tableLayout);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
            tl.addView(row, lp);

            final TextView userName = new TextView(this);
            userName.setText(foundLine[1]);

            final TextView status = new TextView(this);
            if(foundLine[0].charAt(0) == '%')
            {
                status.setText("Pending");
            }
            else if(reader.retrieveGroup(currentLayoutID, this).getAdminName().equals(foundLine[1]))
            {
                status.setText("Admin");
            }
            else {
                status.setText("Member");
            }

            final CheckBox checkA = new CheckBox(this);
            if(foundLine[2].contains("A"))
            {
                checkA.setChecked(true);
            }
            final CheckBox checkD = new CheckBox(this);
            if(foundLine[2].contains("D"))
            {
                checkD.setChecked(true);
            }
            final CheckBox checkU = new CheckBox(this);
            if(foundLine[2].contains("U"))
            {
                checkU.setChecked(true);
            }
            final CheckBox checkM = new CheckBox(this);
            if(foundLine[2].contains("M"))
            {
                checkM.setChecked(true);
            }
            final CheckBox checkP = new CheckBox(this);
            if(foundLine[2].contains("P"))
            {
                checkP.setChecked(true);
            }

            checkA.setClickable(false);
            checkD.setClickable(false);
            checkU.setClickable(false);
            checkM.setClickable(false);
            checkP.setClickable(false);
            if(reader.retrieveGroup(currentLayoutID, this).getAdminName().equals(currentActiveUser.getUserName()) &&
                                    !reader.retrieveGroup(currentLayoutID, this).getAdminName().equals(foundLine[1]))
            {
                checkA.setClickable(true);
                checkD.setClickable(true);
                checkU.setClickable(true);
                checkM.setClickable(true);
                checkP.setClickable(true);
            }

            final Button delete = new Button(this);
            delete.setWidth(45);
            delete.setText("Remove");
            delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    clickHelper(delete);
                }
            });

            TableRow tr = row;
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            tr.addView(userName, tp);
            tr.addView(status, tp);
            tr.addView(checkA, tp);
            tr.addView(checkD, tp);
            tr.addView(checkU, tp);
            tr.addView(checkM, tp);
            tr.addView(checkP, tp);
            tr.addView(delete, tp);
        }






    }




    public void clickHelper(Button button)
    {
        //delete member
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
