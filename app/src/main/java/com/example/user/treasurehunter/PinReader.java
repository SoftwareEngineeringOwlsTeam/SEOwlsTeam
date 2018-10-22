package com.example.user.treasurehunter;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class PinReader extends AppCompatActivity
{

    public PinReader()
    {

    }

    public String read(Context context, String findWhat, String groupID) throws FileNotFoundException
    {
        String dir = "";
        if(findWhat.equals("PersonalPins"))
        {
            dir = context.getFilesDir() + "/pins.txt";
        }
        else if (findWhat.equals("Groups"))
        {
            dir = context.getFilesDir() + "/groups.txt";

        }
        else if (findWhat.equals("GroupPins"))
        {
            dir = context.getFilesDir() + "/" + groupID + "pins.txt";
        }
        else {
            dir = context.getFilesDir() + "/users.txt";
        }
        File file = new File(dir);

        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            System.out.println("FileOutputStream exception: - " + e.toString());
        }
        FileInputStream fileInputStream = context.openFileInput(file.getName());
        String words = "Wrong";
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

        String fin = resultStringBuilder.toString();
        if(!fin.isEmpty())
        {
            fin = fin.substring(0, fin.length() - 4);
        }
        return fin;
    }
}
