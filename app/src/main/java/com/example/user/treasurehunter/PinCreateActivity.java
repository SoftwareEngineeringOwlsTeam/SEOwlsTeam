
package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;
/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinCreateActivity extends AppCompatActivity implements Serializable
{
    public PinDS pin;
    public TextView tvColor;
    EditText text;
    TableRow degreeRow;
    TableRow speedRow;
    Button goBackButton, placePinButton;
    TextView tvBanner;
    String passedID;

    /**
     * Method displays a screen to the user so they can Create a pin.
     * Assigns the entered data into the correct fields.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincreate2);

        pin = (PinDS) getIntent().getSerializableExtra("pin");
        degreeRow = findViewById(R.id.Row10);
        speedRow = findViewById(R.id.Row11);
        goBackButton = findViewById(R.id.goBack);
        placePinButton = findViewById(R.id.button16);
        tvColor = findViewById(R.id.tvcolor);
        tvColor.setTextColor(pin.getDefaultColor());
        tvColor.setText(pin.getColor());
        tvBanner = findViewById(R.id.tvBanner);
        tvBanner.setBackgroundColor(pin.getDefaultColor());
        tvBanner.setText("   " + pin.getPinName());
        goBackButton.setBackgroundColor(pin.getDefaultColor());
        placePinButton.setBackgroundColor(pin.getDefaultColor());


        //Hides the degree and speed rows from displaying if the pin is not Moveable.
        if(!(pin instanceof PinMoveable))
        {
            degreeRow.setVisibility(View.GONE);
            speedRow.setVisibility(View.GONE);

        }


        ((TextView)findViewById(R.id.etTime)).setText(pin.getTime());
        ((TextView)findViewById(R.id.etDate)).setText(pin.getDate());
        ((EditText)findViewById(R.id.etLocationLat)).setText(""+ pin.getLatitude());
        ((EditText)findViewById(R.id.etLocationLong)).setText("" + pin.getLongitude());
        ((EditText)findViewById(R.id.etAltitude)).setText("" + pin.getAltitude());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_picker, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        passedID = (String) getIntent().getSerializableExtra("id");
    }

    public void createClicked(View view)
    {
        IOwrite writer = new IOwrite();
        IOread reader = new IOread();

        EditText et = findViewById(R.id.publisher);
        pin.setPublisher(et.getText().toString());
        et = findViewById(R.id.pinName);
        pin.setPinTitle(et.getText().toString());
        et = findViewById(R.id.description);
        pin.setDescription(et.getText().toString());
        et = findViewById(R.id.etLocationLat);
        pin.setLatitude(Double.parseDouble(et.getText().toString()));
        et = findViewById(R.id.etLocationLong);
        pin.setLongitude(Double.parseDouble(et.getText().toString()));
        et = findViewById(R.id.etAltitude);
        pin.setAltitude(Double.parseDouble(et.getText().toString()));

        String pinID = "";
        boolean generated = false;
        while (!generated)
        {
            pinID = "";
            Random rand = new Random();
            for(int j = 0; j <= 9; j++)
            {
                pinID += String.valueOf(rand.nextInt(9));
            }
            generated = true;
            for(int i = 0; i < reader.existingIDs("pins", this).size(); i++)
            {
                if(reader.existingIDs("pins", this).get(i).equals(pinID))
                {
                    generated = false;
                }
            }
        }
        pin.setPinID(pinID);


        //et = (EditText)findViewById(R.id.color);
        pin.setColor(pin.getColor());

        if(pin instanceof PinMoveable)
        {
            et = findViewById(R.id.etDegree);
            ((PinMoveable) pin).setDegree(Double.parseDouble(et.getText().toString()));
            et = findViewById(R.id.etSpeed);
            ((PinMoveable) pin).setSpeed(Double.parseDouble(et.getText().toString()));
        }


        et = findViewById(R.id.radius);
        pin.setRadius(et.getText().toString());
        Intent mainIntent = new Intent(this, MainActivity.class);
        writer.writePin(pin, this);

        writer.writeUserAudit(currentActiveUser.getUserID(),4, pinID, pin.getPublisher(), this);

        ArrayList<String> addingList = new ArrayList<>();
        addingList.add(pin.getPinID());
        if(passedID.equals("personal"))
        {
            writer.addAssociation(addingList, "ppin", "", this);
        }
        else{
            writer.addAssociation(addingList, "gpin", passedID, this);
        }

        startActivity(mainIntent);
    }

    public void clickPinSelect(View v)
    {
        goBackButton = (Button) v;
        Intent mainIntent = new Intent(this, PinActivity.class);
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
            Intent mainIntent = new Intent(this, PinActivity.class);
            startActivity(mainIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
