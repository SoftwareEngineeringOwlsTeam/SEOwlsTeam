package com.example.user.treasurehunter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PinWriter extends AppCompatActivity
{

    public PinWriter(PinDS pin)
    {


    }

    public void writePin(PinDS pin) throws FileNotFoundException {
        BufferedWriter writer = null;
        try {
            FileOutputStream fileOutputStream = openFileOutput("pins.txt", Context.MODE_WORLD_READABLE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

