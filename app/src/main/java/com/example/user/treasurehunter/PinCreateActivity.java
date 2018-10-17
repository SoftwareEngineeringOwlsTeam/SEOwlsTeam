
package com.example.user.treasurehunter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import android.text.format.Time;

public class PinCreateActivity extends AppCompatActivity implements Serializable
{
    public PinDS pin;
    public Spinner spin;
    EditText text;

    TableRow degreeRow;
    TableRow speedRow;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincreate2);

        pin = (PinDS) getIntent().getSerializableExtra("pin");
        degreeRow = findViewById(R.id.Row10);
        speedRow = findViewById(R.id.Row11);


        if(!(pin instanceof MoveablePin))
        {
            degreeRow.setVisibility(View.GONE);
            speedRow.setVisibility(View.GONE);

        }
        spin = findViewById(R.id.spinner);

        ((TextView)findViewById(R.id.etTime)).setText(pin.getTime());
        ((TextView)findViewById(R.id.etDate)).setText(pin.getDate());
        ((EditText)findViewById(R.id.etLocationLat)).setText(""+ pin.getLatitude());
        ((EditText)findViewById(R.id.etLocationLong)).setText("" + pin.getLongitude());
        ((EditText)findViewById(R.id.etAltitude)).setText("" + pin.getAltitude());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_picker, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }

    public void createClicked(View view)
    {
        EditText et = (EditText)findViewById(R.id.publisher);
        pin.setPublisher(et.getText().toString());
        et = (EditText)findViewById(R.id.pinName);
        pin.setPinName(et.getText().toString());
        et = (EditText)findViewById(R.id.description);
        pin.setDescription(et.getText().toString());



        //et = (EditText)findViewById(R.id.color);
        pin.setColor(spin.getSelectedItem().toString());




        et = (EditText)findViewById(R.id.radius);
        pin.setRadius(et.getText().toString());
        Intent mainIntent = new Intent(this, MainActivity.class);
        ((pinArray) this.getApplication()).pins.add(pin);
        startActivity(mainIntent);
    }
}








