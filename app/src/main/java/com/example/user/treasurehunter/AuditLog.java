package com.example.user.treasurehunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AuditLog extends AppCompatActivity
{
    PinDS pin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);

        if(!((pinArray) this.getApplication()).pins.isEmpty())
        {
            ArrayList<PinDS> pins = ((pinArray) this.getApplication()).pins;
            TextView et2 = (TextView) findViewById(R.id.editText2);
            et2.setText("");
            for(int i = 0; i < pins.size(); i++)
            {
                String a = " \n";
                pin = pins.get(i);
                et2.setText(et2.getText().toString() + pin.getPublisher() + " " + pin.getPinName() + " " +
                        pin.getDescription() + " " + pin.getColor() + " " + pin.getRadius() + " \n");
            }
        }
        else
        {
            TextView et2 = (TextView)findViewById(R.id.editText2);
            et2.setText("There are no pins");
        }
    }
}
