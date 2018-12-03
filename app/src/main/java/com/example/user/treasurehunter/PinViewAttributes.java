package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.Serializable;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
import static com.example.user.treasurehunter.MainActivity.currentLayoutID;

public class PinViewAttributes extends AppCompatActivity implements Serializable
{
    public PinDS pin;
    public TextView tvColor;

    public TableRow row1;
    public TableRow row2;
    public TableRow row3;
    public TableRow row4;
    public TableRow row5;
    public TableRow row6;
    public TableRow row7;
    public TableRow row8;
    public TableRow row9;
    public TableRow row10;
    public TableRow row11;
    public TableRow row12;
    public ConstraintLayout row13;
    public ConstraintLayout row14;

    public TableRow row2alt;
    public TableRow row3alt;
    public TableRow row5alt;
    public TableRow row6alt;
    public TableRow row7alt;
    public TableRow row8alt;
    public TableRow row10alt;
    public TableRow row11alt;
    public ConstraintLayout row13alt;
    public ConstraintLayout row14alt;

    public Button goBackButton;
    public Button editButton;
    public Button cancelButton;
    public Button saveButton;
    public Button compassButton;

    public EditText etPinName;
    public EditText etDescription;
    public EditText etRadius;
    public EditText etLocationLat;
    public EditText etLocationLong;
    public EditText etAltitude;
    public EditText etDegree;
    public EditText etSpeed;
    public String classPassed;
    public TextView tvBanner;

    /**
     * Method displays a screen to the user so they can Create a pin.
     * Assigns the entered data into the correct fields.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view_attributes);

        IOread reader = new IOread();
        classPassed = (String)getIntent().getSerializableExtra("class");

        row1 = findViewById(R.id.Row1);
        row2 = findViewById(R.id.Row2);
        row3 = findViewById(R.id.Row3);
        row4 = findViewById(R.id.Row4);
        row5 = findViewById(R.id.Row5);
        row6 = findViewById(R.id.Row6);
        row7 = findViewById(R.id.Row7);
        row8 = findViewById(R.id.Row8);
        row9 = findViewById(R.id.Row9);
        row10 = findViewById(R.id.Row10);
        row11 = findViewById(R.id.Row11);
        row12 = findViewById(R.id.Row12);
        row13 = findViewById(R.id.Row13);
        row14 = findViewById(R.id.Row14);

        row2alt = findViewById(R.id.Row2Alt);
        row3alt = findViewById(R.id.Row3Alt);
        row5alt = findViewById(R.id.Row5Alt);
        row6alt = findViewById(R.id.Row6Alt);
        row7alt = findViewById(R.id.Row7Alt);
        row8alt = findViewById(R.id.Row8Alt);
        row10alt = findViewById(R.id.Row10Alt);
        row11alt = findViewById(R.id.Row11Alt);
        row13alt = findViewById(R.id.Row13Alt);
        row14alt = findViewById(R.id.Row14Alt);

        row2alt.setVisibility(View.GONE);
        row3alt.setVisibility(View.GONE);
        row5alt.setVisibility(View.GONE);
        row6alt.setVisibility(View.GONE);
        row7alt.setVisibility(View.GONE);
        row8alt.setVisibility(View.GONE);
        row10alt.setVisibility(View.GONE);
        row11alt.setVisibility(View.GONE);
        row13alt.setVisibility(View.GONE);
        row14alt.setVisibility(View.GONE);

        goBackButton = findViewById(R.id.button16);
        editButton = findViewById(R.id.button17);
        cancelButton = findViewById(R.id.button18);
        saveButton = findViewById(R.id.button19);
        compassButton = findViewById(R.id.button20);

        etPinName = findViewById(R.id.etPinName);
        etDescription = findViewById(R.id.etDescription);
        etRadius = findViewById(R.id.etRadius);
        etLocationLat = findViewById(R.id.etLocationLat);
        etLocationLong = findViewById(R.id.etLocationLong);
        etAltitude = findViewById(R.id.etAltitude);
        etDegree = findViewById(R.id.etDegree);
        etSpeed = findViewById(R.id.etSpeed);

        pin = (PinDS) getIntent().getSerializableExtra("pin");

        tvColor = findViewById(R.id.tvcolor);
        tvColor.setTextColor(pin.getDefaultColor());
        tvColor.setText(pin.getColor());

        tvBanner = findViewById(R.id.tvBanner);
        tvBanner.setBackgroundColor(pin.getDefaultColor());
        tvBanner.setText("   " + pin.getPinName());

        goBackButton.setBackgroundColor(pin.getDefaultColor());
        editButton.setBackgroundColor(pin.getDefaultColor());
        cancelButton.setBackgroundColor(pin.getDefaultColor());
        saveButton.setBackgroundColor(pin.getDefaultColor());
        compassButton.setBackgroundColor(pin.getDefaultColor());

        //Hides the degree and speed rows from displaying if the pin is not Moveable.
        row10.setVisibility(View.GONE);
        row11.setVisibility(View.GONE);
        if(pin instanceof PinMoveable)
        {
            PinMoveable movPin = (PinMoveable) pin;
            row10.setVisibility(View.VISIBLE);
            row11.setVisibility(View.VISIBLE);

            ((TextView)findViewById(R.id.tvDegree)).setText("" + movPin.getDegree());
            ((TextView)findViewById(R.id.tvSpeed)).setText("" + movPin.getSpeed());

            ((EditText)findViewById(R.id.etDegree)).setText("" + movPin.getDegree());
            ((EditText)findViewById(R.id.etSpeed)).setText("" + movPin.getSpeed());
        }
        ((TextView)findViewById(R.id.tvPinName)).setText(pin.getPinTitle());
        ((TextView)findViewById(R.id.tvDescription)).setText(pin.getDescription());
        ((TextView)findViewById(R.id.tvRadius)).setText(pin.getRadius());
        ((TextView)findViewById(R.id.tvLocationLat)).setText("" + pin.getLatitude());
        ((TextView)findViewById(R.id.tvLocationLong)).setText("" + pin.getLongitude());
        ((TextView)findViewById(R.id.tvAltitude)).setText("" + pin.getAltitude());

        ((EditText)findViewById(R.id.etPinName)).setText(pin.getPinTitle());
        ((EditText)findViewById(R.id.etDescription)).setText(pin.getDescription());
        ((EditText)findViewById(R.id.etRadius)).setText(pin.getRadius());
        ((EditText)findViewById(R.id.etLocationLat)).setText("" + pin.getLatitude());
        ((EditText)findViewById(R.id.etLocationLong)).setText("" + pin.getLongitude());
        ((EditText)findViewById(R.id.etAltitude)).setText("" + pin.getAltitude());

        ((TextView)findViewById(R.id.tvPublisher)).setText(pin.getPublisher());
        ((TextView)findViewById(R.id.etTime)).setText(pin.getTime());
        ((TextView)findViewById(R.id.etDate)).setText(pin.getDate());

        if(!((TextView)findViewById(R.id.tvPublisher)).getText().toString().equals(currentActiveUser.getUserName()));
        {
            row14.setVisibility(View.GONE);
            if(reader.readGroupMemberPermission(currentActiveUser.getUserID(), currentLayoutID, this).contains("A"))
            {
                row14.setVisibility(View.VISIBLE);
            }
        }
    }

    public void backClicked(View view)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("class", classPassed);
        startActivity(mainIntent);
    }

    public void editClicked(View view)
    {
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        row5.setVisibility(View.GONE);
        row6.setVisibility(View.GONE);
        row7.setVisibility(View.GONE);
        row8.setVisibility(View.GONE);
        row13.setVisibility(View.GONE);
        row14.setVisibility(View.GONE);

        if(pin instanceof PinMoveable)
        {
            row10.setVisibility(View.GONE);
            row11.setVisibility(View.GONE);
        }

        row2alt.setVisibility(View.VISIBLE);
        row3alt.setVisibility(View.VISIBLE);
        row5alt.setVisibility(View.VISIBLE);
        row6alt.setVisibility(View.VISIBLE);
        row7alt.setVisibility(View.VISIBLE);
        row8alt.setVisibility(View.VISIBLE);
        row13alt.setVisibility(View.VISIBLE);
        row14alt.setVisibility(View.VISIBLE);

        if(pin instanceof PinMoveable)
        {
            row10alt.setVisibility(View.VISIBLE);
            row11alt.setVisibility(View.VISIBLE);
        }
    }

    public void cancelClicked(View view)
    {
        row2alt.setVisibility(View.GONE);
        row3alt.setVisibility(View.GONE);
        row5alt.setVisibility(View.GONE);
        row6alt.setVisibility(View.GONE);
        row7alt.setVisibility(View.GONE);
        row8alt.setVisibility(View.GONE);
        row13alt.setVisibility(View.GONE);
        row14alt.setVisibility(View.GONE);

        if(pin instanceof PinMoveable)
        {
            row10alt.setVisibility(View.GONE);
            row11alt.setVisibility(View.GONE);
        }

        row2.setVisibility(View.VISIBLE);
        row3.setVisibility(View.VISIBLE);
        row5.setVisibility(View.VISIBLE);
        row6.setVisibility(View.VISIBLE);
        row7.setVisibility(View.VISIBLE);
        row8.setVisibility(View.VISIBLE);
        row13.setVisibility(View.VISIBLE);
        row14.setVisibility(View.VISIBLE);

        if(pin instanceof PinMoveable)
        {
            row10.setVisibility(View.VISIBLE);
            row11.setVisibility(View.VISIBLE);
        }

        ((EditText)findViewById(R.id.etPinName)).setText(pin.getPinTitle());
        ((EditText)findViewById(R.id.etDescription)).setText(pin.getDescription());
        ((EditText)findViewById(R.id.etRadius)).setText(pin.getRadius());
        ((EditText)findViewById(R.id.etLocationLat)).setText("" + pin.getLatitude());
        ((EditText)findViewById(R.id.etLocationLong)).setText("" + pin.getLongitude());
        ((EditText)findViewById(R.id.etAltitude)).setText("" + pin.getAltitude());
    }

    public void saveClicked(View view)
    {
        IOwrite writer = new IOwrite();
        writer.removeObject("pins", pin.getPinID(), "", this);

        pin.setPinTitle(((EditText)findViewById(R.id.etPinName)).getText().toString());
        pin.setDescription(((EditText)findViewById(R.id.etDescription)).getText().toString());
        pin.setRadius(((EditText)findViewById(R.id.etRadius)).getText().toString());
        pin.setLatitude(Double.parseDouble(((EditText)findViewById(R.id.etLocationLat)).getText().toString()));
        pin.setLongitude(Double.parseDouble(((EditText)findViewById(R.id.etLocationLong)).getText().toString()));
        pin.setAltitude(Double.parseDouble(((EditText)findViewById(R.id.etAltitude)).getText().toString()));

        if(pin instanceof PinMoveable)
        {
            ((PinMoveable) pin).setDegree(Double.parseDouble(((EditText)findViewById(R.id.etDegree)).getText().toString()));
            ((PinMoveable) pin).setSpeed(Double.parseDouble(((EditText)findViewById(R.id.etSpeed)).getText().toString()));
        }

        writer.writePin(pin, this);

        ((TextView)findViewById(R.id.tvPinName)).setText(pin.getPinTitle());
        ((TextView)findViewById(R.id.tvDescription)).setText(pin.getDescription());
        ((TextView)findViewById(R.id.tvRadius)).setText(pin.getRadius());
        ((TextView)findViewById(R.id.tvLocationLat)).setText("" + pin.getLatitude());
        ((TextView)findViewById(R.id.tvLocationLong)).setText("" + pin.getLongitude());
        ((TextView)findViewById(R.id.tvAltitude)).setText("" + pin.getAltitude());

        if(pin instanceof PinMoveable)
        {
            ((TextView)findViewById(R.id.tvDegree)).setText("" + ((PinMoveable) pin).getDegree());
            ((TextView)findViewById(R.id.tvSpeed)).setText("" + ((PinMoveable) pin).getSpeed());
        }

        row2.setVisibility(View.VISIBLE);
        row3.setVisibility(View.VISIBLE);
        row5.setVisibility(View.VISIBLE);
        row6.setVisibility(View.VISIBLE);
        row7.setVisibility(View.VISIBLE);
        row8.setVisibility(View.VISIBLE);
        row13.setVisibility(View.VISIBLE);
        row14.setVisibility(View.VISIBLE);

        if(pin instanceof PinMoveable)
        {
            row10.setVisibility(View.VISIBLE);
            row11.setVisibility(View.VISIBLE);
        }

        row2alt.setVisibility(View.GONE);
        row3alt.setVisibility(View.GONE);
        row5alt.setVisibility(View.GONE);
        row6alt.setVisibility(View.GONE);
        row7alt.setVisibility(View.GONE);
        row8alt.setVisibility(View.GONE);
        row13alt.setVisibility(View.GONE);
        row14alt.setVisibility(View.GONE);

        if(pin instanceof PinMoveable)
        {
            row10alt.setVisibility(View.GONE);
            row11alt.setVisibility(View.GONE);
        }
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void viewCompass(View view)
    {
        Intent locIntent = new Intent(this, LocationGeneration.class);
        locIntent.putExtra("lat", ((TextView)findViewById(R.id.tvLocationLat)).getText().toString());
        locIntent.putExtra("long", ((TextView)findViewById(R.id.tvLocationLong)).getText().toString());
        startActivity(locIntent);
    }

    /**
     * Method that allows the user to move back to the MainActivity screen.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent mainIntent = new Intent(this, YourPins.class);
            mainIntent.putExtra("class", classPassed);
            startActivity(mainIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
