package com.example.user.treasurehunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AuditLog extends AppCompatActivity
{
    PinDS pin;
    PinReader pReader = new PinReader();
    PinWriter pWriter = new PinWriter();

    public AuditLog() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String previousText = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_log);


        if(!((pinArray) this.getApplication()).pins.isEmpty())
        {
            ArrayList<PinDS> pins = ((pinArray) this.getApplication()).pins;
            TextView et2 = (TextView) findViewById(R.id.editText2);
            et2.setText("");
            for(int i = 0; i < pins.size(); i++)
            {
                pin = pins.get(i);
                String data = (et2.getText().toString() + pin.getPublisher() + " " + pin.getPinName() + " " +
                        pin.getDescription() + " " + pin.getColor() + " " + pin.getRadius() + " \n");
                et2.setText(data);
                try
                {
                    previousText = pReader.read();
                    pWriter.writePin(pin, previousText);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            TextView et2 = (TextView)findViewById(R.id.editText2);
            et2.setText("There are no pins");
        }
    }
}
