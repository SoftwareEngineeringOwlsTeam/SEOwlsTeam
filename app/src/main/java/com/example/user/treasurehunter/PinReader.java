package com.example.user.treasurehunter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PinReader extends AppCompatActivity
{

    public PinReader()
    {

    }

    public String read() throws FileNotFoundException
    {
        FileInputStream fileInputStream = openFileInput("pins.txt");
        String words = "";
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream)))
        {
            while ((words = br.readLine()) != null)
            {
                resultStringBuilder.append(words).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //tv1.setText(resultStringBuilder.toString());
        if(!words.isEmpty())
        {
            words = words.substring(0,words.length() - 3);
        }
        return words;
    }
}
