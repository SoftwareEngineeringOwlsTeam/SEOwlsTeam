package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayout;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

public class YourPins extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ArrayList<String> groupSpinner = new ArrayList<>();
    private ArrayList<String> idSpinner = new ArrayList<>();
    public String classPassed;
    private String selected;
    private PinDS currentPin;

    // INCLUDE DOCUMENTATION*****************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_pins);

        IOread reader = new IOread();
        Spinner idselector = findViewById(R.id.spinner2);
        idselector.setOnItemSelectedListener(this);
        for(int i = 0; i < currentActiveUser.getAssociatedGroupID().size() - 1; i++)
        {
            if(!currentActiveUser.getAssociatedGroupID().get(i).equals("null") &&
                    currentActiveUser.getAssociatedGroupID().get(i).charAt(0) != '%')
            {
                idSpinner.add(currentActiveUser.getAssociatedGroupID().get(i));
                Group group = reader.retrieveGroup(currentActiveUser.getAssociatedGroupID().get(i), this);
                groupSpinner.add(group.getGroupName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                groupSpinner
        );
        idselector.setAdapter(adapter);

        // On entering, select the spinner with the current selected layout
        for(int i = 0; i < groupSpinner.size() - 1; i++)
        {
            if(idselector.getSelectedItem().equals(currentLayout))
            {
                int selectedPosition = idselector.getSelectedItemPosition();
                idselector.setSelection(selectedPosition, false);
                currentLayoutID = idSpinner.get(i);
            }
            else{
                int selectedPosition = idselector.getSelectedItemPosition() + 1;
                idselector.setSelection(selectedPosition, false);
            }
        }

        classPassed = (String)getIntent().getSerializableExtra("class");

        //Populate the ScrollViews
        ArrayList<String> listOfPins = new ArrayList<>();
        if(currentActiveUser.getPersonalPinID() != null)
        {
            listOfPins = currentActiveUser.getPersonalPinID();
        }
        for(int i = 0; i < listOfPins.size() - 1; i++)
        {
            currentPin = reader.retrievePin(listOfPins.get(i), this);

            if(currentPin.getPinName().equals(classPassed))
            {
                final TableRow row = new TableRow(this);
                TableLayout tl = findViewById(R.id.UserPins);
                TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tl.addView(row, lp);

                final Button myButton = new Button(this);
                myButton.setText(currentPin.getPinTitle());
                myButton.setBackgroundColor(currentPin.getDefaultColor());
                myButton.setWidth(800);
                myButton.setHint(currentPin.getPinID());
                row.setPadding(0,10,0,10);

                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHelper(myButton);
                    }
                });

                final TextView time = new TextView(this);
                time.setText("Made: " + currentPin.getTime() + " " + currentPin.getDate());
                time.setBackgroundColor(currentPin.getDefaultColor());
                time.setWidth(650);
                time.setTextSize(24);
                TableRow tr = row;

                TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                tr.addView(myButton, tp);
                tr.addView(time, tp);
            }
        }

        //Populate the ScrollViews
        if(!currentLayoutID.equals(""))
        {
            listOfPins = new ArrayList<>();
            Group group = reader.retrieveGroup(currentLayoutID, this);
            if(group.getAssociatedPinIDs() != null)
            {
                listOfPins = group.getAssociatedPinIDs();
            }
            for(int i = 0; i < listOfPins.size() - 1; i++)
            {
                currentPin = reader.retrievePin(listOfPins.get(i), this);
                if(currentPin.getPinName().equals(classPassed))
                {
                    final TableRow row = new TableRow(this);
                    TableLayout tl = findViewById(R.id.GroupPins);
                    TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    tl.addView(row, lp);
                    row.setPadding(0,10,0,10);

                    final Button myButton = new Button(this);
                    myButton.setText(currentPin.getPinTitle());
                    myButton.setBackgroundColor(currentPin.getDefaultColor());
                    myButton.setWidth(800);
                    myButton.setHint(currentPin.getPinID());

                    myButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            clickHelper(myButton);
                        }
                    });

                    final TextView time = new TextView(this);
                    time.setText("Made: " + currentPin.getTime() + " " + currentPin.getDate());
                    time.setBackgroundColor(currentPin.getDefaultColor());
                    time.setWidth(650);
                    time.setTextSize(24);
                    TableRow tr = row;

                    TableRow.LayoutParams tp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    tr.addView(myButton, tp);
                    tr.addView(time, tp);
                }
            }
        }
        ((TextView)findViewById(R.id.aboveSpinner)).setText("Group Selected : " + currentLayout);
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item = parent.getItemAtPosition(position).toString();
        String savedID = currentLayoutID;
        currentLayout = item;
        currentLayoutID = idSpinner.get(position);
        selected = item;
        Toast.makeText(parent.getContext(), "Current Layout: " + item, Toast.LENGTH_LONG).show();
        if(!savedID.equals(idSpinner.get(position)))
        {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void onNothingSelected(AdapterView<?> arg0)
    {
        currentLayoutID = "";
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void clickHelper(Button button)
    {
        IOread reader = new IOread();
        Intent mainIntent = new Intent(this, PinViewAttributes.class);
        mainIntent.putExtra("pin", reader.retrievePin(button.getHint().toString(),this));
        mainIntent.putExtra("class", classPassed);
        startActivity(mainIntent);
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void clickPPin(View view)
    {
        PinDS pin = null;
        if(classPassed.equals("Treasure Pin"))
        {
            pin = new PinClassTreasure();
        }
        if(classPassed.equals("Scavenger Hunt Pin"))
        {
            pin = new PinClassScavengerHunt();
        }
        if(classPassed.equals("Shipwreck Pin"))
        {
            pin = new PinClassShipwreck();
        }
        if(classPassed.equals("Custom Pin"))
        {
            pin = new PinMoveableClassCustom();
        }
        if(classPassed.equals("Forest Fire Pin"))
        {
            pin = new PinMoveableClassForestFire();
        }
        if(classPassed.equals("Hunting Pin"))
        {
            pin = new PinMoveableClassHunting();
        }
        if(classPassed.equals("Survivor Pin"))
        {
            pin = new PinMoveableClassSurvivor();
        }
        if(classPassed.equals("Whale Pin"))
        {
            pin = new PinMoveableClassWhale();
        }
        Intent mainIntent = new Intent(this, LocationGeneration.class);
        currentLayout = "Personal";
        currentLayoutID = "personal";
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void clickGPin(View view)
    {
        PinDS pin = null;
        if(classPassed.equals("Treasure Pin"))
        {
            pin = new PinClassTreasure();
        }
        if(classPassed.equals("Scavenger Hunt Pin"))
        {
            pin = new PinClassScavengerHunt();
        }
        if(classPassed.equals("Shipwreck Pin"))
        {
            pin = new PinClassShipwreck();
        }
        if(classPassed.equals("Custom Pin"))
        {
            pin = new PinMoveableClassCustom();
        }
        if(classPassed.equals("Forest Fire Pin"))
        {
            pin = new PinMoveableClassForestFire();
        }
        if(classPassed.equals("Hunting Pin"))
        {
            pin = new PinMoveableClassHunting();
        }
        if(classPassed.equals("Survivor Pin"))
        {
            pin = new PinMoveableClassSurvivor();
        }
        if(classPassed.equals("Whale Pin"))
        {
            pin = new PinMoveableClassWhale();
        }
        Intent mainIntent = new Intent(this, LocationGeneration.class);
        mainIntent.putExtra("pin", pin);
        startActivity(mainIntent);
    }

    // INCLUDE DOCUMENTATION*****************************************************
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
