package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class PinWriter extends AppCompatActivity implements Serializable
{

    private PinDS pin;

    public PinWriter()
    {

    }

    public void writePin(PinDS pin) throws FileNotFoundException {
        BufferedWriter writer = null;
        try {
            FileOutputStream fileOutputStream = openFileOutput("pins.txt", Context.MODE_WORLD_READABLE);
            String data = ("Pin: " + pin.getPublisher() + "*" + pin.getDescription() + "*" + pin.getRadius() + "*" + pin.getLatitude() + "*"
            + pin.getLongitude() + "*" + pin.getAltitude() + "*" + pin.getTime() + "*" + pin.getDate());
            if(pin instanceof MoveablePin)
            {
                data += ("*" + ((MoveablePin) pin).getDegree() + "*" + ((MoveablePin) pin).getSpeed());
            }

            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(data);
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

