package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    public boolean buttonSet;
    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>( );

    /**
     * Method that sets the screen to display activity_group_members.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        IOread reader = new IOread();
        buttonSet = false;
        ArrayList<String> members = reader.readGroupMembers(currentLayoutID, this);
        for(int i = 0; i < members.size(); i++)
        {
            final String[] foundLine = members.get(i).split("\\*",14);

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
            else if(reader.retrieveGroup(currentLayoutID, this).getAdminID().equals(foundLine[0]))
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

            TableRow tr = row;
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            tr.addView(userName, tp);
            tr.addView(status, tp);
            tr.addView(checkA, tp);
            tr.addView(checkD, tp);
            tr.addView(checkU, tp);
            tr.addView(checkM, tp);
            tr.addView(checkP, tp);

            if(reader.retrieveGroup(currentLayoutID, this).getAdminID().equals(currentActiveUser.getUserID()) &&
                                    !reader.retrieveGroup(currentLayoutID, this).getAdminID().equals(foundLine[0]))
            {
                checkA.setClickable(true);

                checkA.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cretor(foundLine[0], checkA, "A");
                    }
                });

                checkD.setClickable(true);

                checkD.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cretor(foundLine[0], checkD, "D");
                    }
                });

                checkU.setClickable(true);

                checkU.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cretor(foundLine[0], checkU, "U");
                    }
                });

                checkM.setClickable(true);

                checkM.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cretor(foundLine[0], checkM, "M");
                    }
                });

                checkP.setClickable(true);

                checkP.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cretor(foundLine[0], checkP, "P");
                    }
                });

                final Button delete = new Button(this);
                delete.setWidth(45);
                delete.setText("Remove");
                delete.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        clickHelper(foundLine[0], row);
                    }
                });
                tr.addView(delete, tp);
            }
        }






    }




    public void clickHelper(String id, TableRow row)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        row.setVisibility(View.GONE);
        writer.removeObject(currentLayoutID + "members", id, currentLayoutID, this);
        ArrayList<String> list = new ArrayList<>();
        list.add(currentLayoutID);
        writer.removeAssociation(reader.retrieveUser(id, this), list, "group", currentLayoutID, this);
    }

    public void cretor(String id, CheckBox checkBox, String letter)
    {
        IOread reader = new IOread();
        boolean alreadyEdited = false;
        for(int i = 0; i < ids.size(); i++)
        {
            if(ids.get(i).equals(id))
            {
                alreadyEdited = true;
            }
        }
        if(!alreadyEdited)
        {
            ids.add(id);
            permissions.add(reader.readGroupMemberPermission(id, currentLayoutID, this));
        }
        for(int i = 0; i < ids.size(); i++)
        {
            if(ids.get(i).equals(id))
            {
                if(checkBox.isChecked())
                {
                    if(!permissions.get(i).contains(letter))
                    {
                        permissions.set(i, permissions.get(i) + letter);
                    }
                }
                else{
                    if(permissions.get(i).contains(letter))
                    {
                        permissions.set(i, permissions.get(i).replace(letter,""));
                    }
                }
            }
        }
        if(!buttonSet)
        {
            final TableRow row = new TableRow(this);
            TableLayout tl = findViewById(R.id.tableLayout);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
            tl.addView(row, lp);

            final Button save = new Button(this);
            TableRow tr = row;
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

            save.setWidth(45);
            save.setText("Save");
            save.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    save();
                }
            });
            tr.addView(save, tp);
            buttonSet = true;
        }

    }

    public void save()
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++)
        {
            writer.removeObject(currentLayoutID + "members", ids.get(i), currentLayoutID, this);
            names.add(reader.retrieveUser(ids.get(i), this).getUserName());
        }
        writer.writeMembers(ids, names, permissions, currentLayoutID, this);
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
