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
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class PinReader extends AppCompatActivity
{
    PinDS pin;
    Group group;
    User user;

    public PinReader() { }

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

    public PinDS retrievePin(Context context, String pinID)
    {
        PinDS retrievedPin = null;
        try
        {
            String everything = read(context,"PersonalPins","");
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",14);
                if(foundLine[0].equals(pinID))
                {
                    if(foundLine[1].equals("TREASUREPIN"))
                    {
                        retrievedPin = new TreasurePin();
                    }
                    else if(foundLine[1].equals("SHIPWRECKPIN"))
                    {
                        retrievedPin = new Shipwreck();
                    }
                    else if(foundLine[1].equals("SCAVENGERHUNTPIN"))
                    {
                        retrievedPin = new ScavengerHuntPin();
                    }
                    else if(foundLine[1].equals("SURVIVORPIN"))
                    {
                        retrievedPin = new SurvivorPin();
                        ((SurvivorPin) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((SurvivorPin) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("FORESTFIREPIN"))
                    {
                        retrievedPin = new ForestFirePin();
                        ((ForestFirePin) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((ForestFirePin) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("WHALEPIN"))
                    {
                        retrievedPin = new WhalePin();
                        ((WhalePin) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((WhalePin) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("HUNTINGPIN"))
                    {
                        retrievedPin = new Hunting();
                        ((Hunting) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((Hunting) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else
                    {
                        retrievedPin = new CustomPin();
                    }
                    retrievedPin.setPinID(foundLine[0]);
                    retrievedPin.setPinTitle(foundLine[2]);
                    retrievedPin.setPublisher(foundLine[3]);
                    retrievedPin.setDescription(foundLine[4]);
                    retrievedPin.setRadius(foundLine[5]);
                    retrievedPin.setLatitude(Double.parseDouble(foundLine[6]));
                    retrievedPin.setLongitude(Double.parseDouble(foundLine[7]));
                    retrievedPin.setAltitude(Double.parseDouble(foundLine[8]));
                    retrievedPin.setTime(foundLine[9]);
                    retrievedPin.setDate(foundLine[10]);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retrievedPin;
    }

    public Group retrieveGroup(Context context, String groupID)
    {
        Group retrievedGroup = null;
        try
        {
            String everything = read(context,"Groups","");
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",6);
                if(foundLine[0].equals(groupID))
                {
                    String[] eachMember = foundLine[5].split("/", 1000);
                    ArrayList<String> allMembers = new ArrayList<String>();
                    for(int j = 0; j < eachMember.length; j++)
                    {
                        allMembers.add(eachMember[j]);
                    }
                    retrievedGroup = new Group(allMembers, foundLine[1], foundLine[4], foundLine[3], foundLine[2]);
                    retrievedGroup.setGroupID(foundLine[0]);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retrievedGroup;
    }



    public User retrieveUser(Context context, String userID)
    {
        User retrievedUser = null;
        try
        {
            String everything = read(context,"Users","");
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",6);
                if(foundLine[0].equals(userID))
                {
                    String[] eachAssociation = foundLine[3].split("/", 1000);
                    ArrayList<String> allAssociations = new ArrayList<String>();
                    for(int j = 0; j < eachAssociation.length; j++)
                    {
                        allAssociations.add(eachAssociation[j]);
                    }
                    retrievedUser = new User(foundLine[0], foundLine[1], foundLine[2]);
                    retrievedUser.setAssociatedGroupID(allAssociations);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retrievedUser;
    }

    public ArrayList<String> existingIDs (Context context, String whatOf)
    {
        ArrayList<String> existingIDs = new ArrayList<String>();
        try
        {
            String everything = "";
            if(whatOf.equals("ppins"))
            {
                everything = read(context,"PersonalPins","");
            }
            else if(whatOf.equals("groups"))
            {
                everything = read(context,"Groups","");
            }
            else if(whatOf.equals("users"))
            {
                everything = read(context,"Users","");
            }
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",14);
                existingIDs.add(foundLine[0]);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return existingIDs;
    }
}
