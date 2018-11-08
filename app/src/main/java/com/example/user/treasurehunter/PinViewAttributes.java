package com.example.user.treasurehunter;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;

public class PinViewAttributes extends AppCompatActivity implements Serializable
    {
        public PinDS pin;
        public TextView tvColor;

        TableRow row1;
        TableRow row2;
        TableRow row3;
        TableRow row4;
        TableRow row5;
        TableRow row6;
        TableRow row7;
        TableRow row8;
        TableRow row9;
        TableRow row10;
        TableRow row11;
        TableRow row12;
        ConstraintLayout row13;
        ConstraintLayout row14;

        TableRow row2alt;
        TableRow row3alt;
        TableRow row5alt;
        TableRow row6alt;
        TableRow row7alt;
        TableRow row8alt;
        TableRow row10alt;
        TableRow row11alt;
        ConstraintLayout row13alt;
        ConstraintLayout row14alt;

        EditText text;
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
            setContentView(R.layout.activity_pin_view_attributes);

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

            pin = (PinDS) getIntent().getSerializableExtra("pin");
            goBackButton = findViewById(R.id.goBack);
            placePinButton = findViewById(R.id.button16);
            tvColor = findViewById(R.id.tvcolor);
            tvColor.setTextColor(pin.getDefaultColor());
            tvColor.setText(pin.getColor());
            tvBanner = findViewById(R.id.tvBanner);
            tvBanner.setBackgroundColor(pin.getDefaultColor());
            tvBanner.setText("   " + pin.getPinName());
            //goBackButton.setBackgroundColor(pin.getDefaultColor());
            //placePinButton.setBackgroundColor(pin.getDefaultColor());


            //Hides the degree and speed rows from displaying if the pin is not Moveable.
            row10.setVisibility(View.GONE);
            row11.setVisibility(View.GONE);
            if(pin instanceof PinMoveable)
            {
                PinMoveable movPin = (PinMoveable) pin;
                row10.setVisibility(View.VISIBLE);
                row11.setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.etDegree)).setText("" + movPin.getDegree());
                ((TextView)findViewById(R.id.etSpeed)).setText("" + movPin.getSpeed());
            }

            ((TextView)findViewById(R.id.publisher)).setText(pin.getPublisher());
            ((TextView)findViewById(R.id.pinName)).setText(pin.getPinTitle());
            ((TextView)findViewById(R.id.description)).setText(pin.getDescription());
            ((TextView)findViewById(R.id.radius)).setText(pin.getRadius());
            ((TextView)findViewById(R.id.etLocationLat)).setText("" + pin.getLatitude());
            ((TextView)findViewById(R.id.etLocationLong)).setText("" + pin.getLongitude());
            ((TextView)findViewById(R.id.etAltitude)).setText("" + pin.getAltitude());
            ((TextView)findViewById(R.id.etTime)).setText(pin.getTime());
            ((TextView)findViewById(R.id.etDate)).setText(pin.getDate());


            passedID = (String) getIntent().getSerializableExtra("id");
        }

        public void backClicked(View view)
        {
            Intent mainIntent = new Intent(this, MainActivity.class);
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
            row10.setVisibility(View.GONE);
            row11.setVisibility(View.GONE);
            row13.setVisibility(View.GONE);
            row14.setVisibility(View.GONE);

            row2alt.setVisibility(View.VISIBLE);
            row3alt.setVisibility(View.VISIBLE);
            row5alt.setVisibility(View.VISIBLE);
            row6alt.setVisibility(View.VISIBLE);
            row7alt.setVisibility(View.VISIBLE);
            row8alt.setVisibility(View.VISIBLE);
            row10alt.setVisibility(View.VISIBLE);
            row11alt.setVisibility(View.VISIBLE);
            row13alt.setVisibility(View.VISIBLE);
            row14alt.setVisibility(View.VISIBLE);
        }

        public void cancelClicked(View view)
        {
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

            row2.setVisibility(View.VISIBLE);
            row3.setVisibility(View.VISIBLE);
            row5.setVisibility(View.VISIBLE);
            row6.setVisibility(View.VISIBLE);
            row7.setVisibility(View.VISIBLE);
            row8.setVisibility(View.VISIBLE);
            row10.setVisibility(View.VISIBLE);
            row11.setVisibility(View.VISIBLE);
            row13.setVisibility(View.VISIBLE);
            row14.setVisibility(View.VISIBLE);
        }

        public void saveClicked(View view)
        {
            row2.setVisibility(View.GONE);
            row3.setVisibility(View.GONE);
            row5.setVisibility(View.GONE);
            row6.setVisibility(View.GONE);
            row7.setVisibility(View.GONE);
            row8.setVisibility(View.GONE);
            row10.setVisibility(View.GONE);
            row11.setVisibility(View.GONE);
            row13.setVisibility(View.GONE);
            row14.setVisibility(View.GONE);

            row2alt.setVisibility(View.VISIBLE);
            row3alt.setVisibility(View.VISIBLE);
            row5alt.setVisibility(View.VISIBLE);
            row6alt.setVisibility(View.VISIBLE);
            row7alt.setVisibility(View.VISIBLE);
            row8alt.setVisibility(View.VISIBLE);
            row10alt.setVisibility(View.VISIBLE);
            row11alt.setVisibility(View.VISIBLE);
            row13alt.setVisibility(View.VISIBLE);
            row14alt.setVisibility(View.VISIBLE);
        }

        /**
         * Method that allows the user to move back to the MainActivity screen.
         */
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK )
            {
                Intent mainIntent = new Intent(this, PinView.class);
                startActivity(mainIntent);
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
}
