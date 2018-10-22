package com.example.user.treasurehunter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GroupManager extends Activity implements OnItemSelectedListener
{
    ArrayList<String> groupSpinner = new ArrayList<String>();
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manager);

        PinWriter writer = new PinWriter();
        PinReader reader = new PinReader();

        TextView groupIDs = (TextView) findViewById(R.id.groupIDs);
        groupIDs.setText("The Groups");
        Spinner idselector = (Spinner) findViewById(R.id.spinner2);
        idselector.setOnItemSelectedListener(this);

        groupSpinner = reader.existingIDs(this, "groups");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                groupSpinner
        );
        idselector.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        selected = item;
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void cgClicked(View view)
    {
        Intent locIntent = new Intent(this, GroupCreator.class);
        startActivity(locIntent);
    }

    public void enterView(View view)
    {
        Intent locIntent = new Intent(this, ViewGroup.class);
        locIntent.putExtra("id", selected);
        startActivity(locIntent);
    }

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
