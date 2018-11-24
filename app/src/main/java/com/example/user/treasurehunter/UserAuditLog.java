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

import java.io.FileNotFoundException;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class UserAuditLog extends AppCompatActivity
{
    private IOread reader = new IOread();

    public UserAuditLog() {}

    /**
     * Method that sets the screen to display acivity_audit_log.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);

        if(currentLayoutID.equals("personal"))
        {
            final TableRow row = new TableRow(this);

            TableLayout tl = findViewById(R.id.auditLayout);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tl.addView(row, lp);

            final TextView words = new TextView(this);
            words.setWidth(700);
            words.setText(reader.readUserAudit(currentActiveUser.getUserID(), this));
            TableRow tr = row;
            TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            tr.addView(words, tp);
        }
        else {
            String everything = reader.readGroupAudit(currentLayoutID, this);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i = i + 3)
            {
                final TableRow row = new TableRow(this);

                TableLayout tl = findViewById(R.id.auditLayout);
                TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tl.addView(row, lp);

                if(eachLine[i].equals("*********************************************") && (i + 2) != eachLine.length)
                {
                    final int numPass = i;
                    final TextView words = new TextView(this);
                    final CheckBox check = new CheckBox(this);
                    check.setChecked(true);
                    words.setText(eachLine[i]);
                    words.setWidth(700);
                    TableRow tr = row;
                    TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    tr.addView(words, tp);
                    tr.addView(check, tp);

                    final TableRow row2 = new TableRow(this);
                    TableLayout tl2 = findViewById(R.id.auditLayout);
                    TableLayout.LayoutParams lp2 = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    tl2.addView(row2, lp2);
                    final TextView words2 = new TextView(this);
                    words2.setText(eachLine[i + 2]);
                    words2.setWidth(700);
                    TableRow tr2 = row2;
                    TableRow.LayoutParams tp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    tr2.addView(words2, tp2);
                    check.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            creator(row2, check, (numPass / 3));
                        }
                    });
                    if(eachLine[i + 1].equals("0"))
                    {
                        check.setChecked(false);
                    }
                    else{
                        check.setChecked(true);
                    }
                    if(check.isChecked())
                    {
                        row2.setVisibility(View.VISIBLE);
                    }
                    else{
                        row2.setVisibility(View.GONE);
                    }
                    if(!reader.retrieveGroup(currentLayoutID, this).getAdminID().equals(currentActiveUser.getUserID()))
                    {
                        check.setClickable(false);
                    }
                }
                else{
                    final TextView words = new TextView(this);
                    words.setText(eachLine[i]);
                    words.setWidth(700);
                    TableRow tr = row;
                    TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    tr.addView(words, tp);
                }
            }
        }
    }

    public void creator(TableRow row, CheckBox check, int i)
    {
        IOwrite writer = new IOwrite();
        String readAudit = "";
        try
        {
            readAudit = reader.read("groupaudit", currentLayoutID, this);
            System.out.println(readAudit);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        if(check.isChecked())
        {
            row.setVisibility(View.VISIBLE);
            writer.editGroupAudit(1, readAudit, currentLayoutID, i, this);
        }
        else{
            row.setVisibility(View.GONE);
            writer.editGroupAudit(0, readAudit, currentLayoutID, i, this);
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
