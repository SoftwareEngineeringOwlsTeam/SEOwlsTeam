package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupManager extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manager);
        TextView groupIDs = (TextView) findViewById(R.id.groupIDs);
        if(!((pinArray) this.getApplication()).groups.isEmpty())
        {
            ArrayList<Group> groups = ((pinArray) this.getApplication()).groups;
            for(int i = 0; i < groups.size(); i++)
            {
                Group group = groups.get(i);
                groupIDs.setText(group.getGroupID() + " \n");

                SpannableString ss = new SpannableString(group.getGroupID());

                ClickableSpan click = new ClickableSpan()
                {
                    @Override
                    public void onClick(View widget)
                    {
                        enterView("id");
                    }
                };

                ss.setSpan(click,0,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                groupIDs.setText(ss);
                groupIDs.setMovementMethod((LinkMovementMethod.getInstance()));
            }
        }
        else
        {
            groupIDs.setText("There are no groups");
        }



    }

    public void cgClicked(View view)
    {
        Intent locIntent = new Intent(this, GroupCreator.class);
        startActivity(locIntent);
    }

    public void enterView(String id)
    {
        Intent locIntent = new Intent(this, ViewGroup.class);
        startActivity(locIntent);
    }
}
