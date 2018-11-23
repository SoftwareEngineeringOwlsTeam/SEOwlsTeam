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
                final Button myButton = new Button(this);
                myButton.setText(currentPin.getPinTitle());
                myButton.setBackgroundColor(currentPin.getDefaultColor());

                myButton.setHint(currentPin.getPinID());

                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHelper(myButton);
                    }
                });
                LinearLayout ll = findViewById(R.id.UserPins);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(myButton, lp);
            }


        }







        //Populate the ScrollViews
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
                final Button myButton = new Button(this);
                myButton.setText(currentPin.getPinTitle());
                myButton.setBackgroundColor(currentPin.getDefaultColor());

                myButton.setHint(currentPin.getPinID());

                myButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        clickHelper(myButton);
                    }
                });

                LinearLayout ll = findViewById(R.id.GroupPins);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(myButton, lp);
            }
        }


    }

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
    public void onNothingSelected(AdapterView<?> arg0) {}

    public void clickHelper(Button button)
    {
        IOread reader = new IOread();
        Intent mainIntent = new Intent(this, PinViewAttributes.class);
        mainIntent.putExtra("pin", reader.retrievePin(button.getHint().toString(),this));
        mainIntent.putExtra("class", classPassed);
        startActivity(mainIntent);
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
