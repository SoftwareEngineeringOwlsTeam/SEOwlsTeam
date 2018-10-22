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

    public PinDS retreavePin(Context context, String pinID)
    {
        PinDS retreavedPin = null;
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
                        retreavedPin = new TreasurePin();
                    }
                    else if(foundLine[1].equals("SHIPWRECKPIN"))
                    {
                        retreavedPin = new Shipwreck();
                    }
                    else if(foundLine[1].equals("SCAVENGERHUNTPIN"))
                    {
                        retreavedPin = new ScavengerHuntPin();
                    }
                    else if(foundLine[1].equals("SURVIVORPIN"))
                    {
                        retreavedPin = new SurvivorPin();
                        ((SurvivorPin) retreavedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((SurvivorPin) retreavedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("FORESTFIREPIN"))
                    {
                        retreavedPin = new ForestFirePin();
                        ((ForestFirePin) retreavedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((ForestFirePin) retreavedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("WHALEPIN"))
                    {
                        retreavedPin = new WhalePin();
                        ((WhalePin) retreavedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((WhalePin) retreavedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("HUNTINGPIN"))
                    {
                        retreavedPin = new Hunting();
                        ((Hunting) retreavedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((Hunting) retreavedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else
                    {
                        retreavedPin = new CustomPin();
                    }
                    retreavedPin.setPinID(foundLine[0]);
                    retreavedPin.setPinTitle(foundLine[2]);
                    retreavedPin.setPublisher(foundLine[3]);
                    retreavedPin.setDescription(foundLine[4]);
                    retreavedPin.setRadius(foundLine[5]);
                    retreavedPin.setLatitude(Double.parseDouble(foundLine[6]));
                    retreavedPin.setLongitude(Double.parseDouble(foundLine[7]));
                    retreavedPin.setAltitude(Double.parseDouble(foundLine[8]));
                    retreavedPin.setTime(foundLine[9]);
                    retreavedPin.setDate(foundLine[10]);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retreavedPin;
    }

    public Group retreaveGroup(Context context, String groupID)
    {
        Group retreavedGroup = null;
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
                    retreavedGroup = new Group(allMembers, foundLine[4], foundLine[3], foundLine[2]);
                    retreavedGroup.setGroupID(foundLine[0]);
                    retreavedGroup.setAdminID(foundLine[1]);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retreavedGroup;
    }



    public User retreaveUser(Context context, String userID)
    {
        User retreavedUser = null;
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
                    retreavedUser = new User(foundLine[0], foundLine[1], foundLine[2]);
                    retreavedUser.setAssociatedGroupID(allAssociations);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return retreavedUser;
    }

    public ArrayList<String> existingPinIDs (Context context)
    {
        ArrayList<String> existingIDs = new ArrayList<String>();
        try
        {
            String everything = read(context,"PersonalPins","");
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
