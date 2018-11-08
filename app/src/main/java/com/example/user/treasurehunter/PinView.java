package com.example.user.treasurehunter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

public class PinView extends AppCompatActivity implements Serializable
{
    String passedID;
    PinDS currentPin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);
        passedID = (String) getIntent().getSerializableExtra("id");
        
        IOread reader = new IOread();
        ArrayList<String> listOfPins = new ArrayList<String>();
        if(passedID.equals("personal"))
        {
            if(currentActiveUser.getPersonalPinID() != null)
            {
                listOfPins = currentActiveUser.getPersonalPinID();
            }
        }
        else{
            Group group = reader.retrieveGroup(passedID, this);
            if(group.getAssociatedPinIDs() != null)
            {
                listOfPins = group.getAssociatedPinIDs();
            }
        }

        for(int i = 0; i < listOfPins.size() - 1; i++)
        {
            currentPin = reader.retrievePin(listOfPins.get(i), this);

            final Button myButton = new Button(this);
            myButton.setText(currentPin.getPinTitle());
            myButton.setBackgroundColor(currentPin.getDefaultColor());

            myButton.setHint(currentPin.getPinID());


            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHelper(myButton);
                    //Button myButton = (Button) v;
                }
            });

            LinearLayout ll = (LinearLayout)findViewById(R.id.pinViewLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
    }

    public void clickHelper(Button button)
    {
        IOread reader = new IOread();
        Intent mainIntent = new Intent(this, PinViewAttributes.class);
        mainIntent.putExtra("pin", reader.retrievePin(button.getHint().toString(),this));
        startActivity(mainIntent);
    }
}
