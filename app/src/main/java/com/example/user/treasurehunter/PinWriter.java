package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PinWriter extends AppCompatActivity implements Serializable
{

    //private PinDS pin;
    //private Group group;
    //private User user;

    public PinWriter() { }

    public void writePin(PinDS pin, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/pins.txt";
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

        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + pin.getPinID() + "*" + pin.getPinName() + "*" + pin.getClassName() + "*" + pin.getPublisher() + "*" +
                    pin.getDescription() + "*" + pin.getRadius() + "*" + pin.getLatitude() + "*"
                    + pin.getLongitude() + "*" + pin.getAltitude() + "*" + pin.getTime() + "*" + pin.getDate());
            if (pin instanceof MoveablePin)
            {
                data += ("*" + ((MoveablePin) pin).getDegree() + "*" + ((MoveablePin) pin).getSpeed());
            }
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }



    public void writeGroup(Group group, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/groups.txt";
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

        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + group.getGroupID() + "*" + group.getGroupName() + "*" + group.getAdminID() + "*" + group.getAdminName() + "*" +
                                        group.getGroupDescription() + "*");
            if(group.getMembersID() != null)
            {
                for(int i = 0; i > group.getMembersID().size(); i++)
                {
                    data += group.getMembersID().get(i);
                }
            }
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }



    public void writeUser(User user, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/users.txt";
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

        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + user.getUserID() + "*" + user.getUserName() + "*" + user.getPassword() + "*");
            if(user.getAssociatedGroupID() != null)
            {
                for(int i = 0; i > user.getAssociatedGroupID().size(); i++)
                {
                    data += user.getAssociatedGroupID().get(i);
                }
            }
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }

    public void clearData(Context context)
    {
        String dir = context.getFilesDir() + "/pins.txt";
        File file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/groups.txt";
        file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/users.txt";
        file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
    }
}

