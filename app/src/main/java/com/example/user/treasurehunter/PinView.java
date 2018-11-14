package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import java.io.Serializable;
import java.util.ArrayList;
import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

public class PinView extends AppCompatActivity implements Serializable
{
    private PinDS currentPin;

    // INCLUDE DOCUMENTATION*****************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);

        IOread reader = new IOread();
        ArrayList<String> listOfPins = new ArrayList<String>();
        if(currentLayoutID.equals("personal"))
        {
            if(currentActiveUser.getPersonalPinID() != null)
            {
                listOfPins = currentActiveUser.getPersonalPinID();
            }
        }
        else{
            Group group = reader.retrieveGroup(currentLayoutID, this);
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

            myButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    clickHelper(myButton);
                }
            });

            LinearLayout ll = findViewById(R.id.pinViewLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void clickHelper(Button button)
    {
        IOread reader = new IOread();
        Intent mainIntent = new Intent(this, PinViewAttributes.class);
        mainIntent.putExtra("pin", reader.retrievePin(button.getHint().toString(),this));
        startActivity(mainIntent);
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
