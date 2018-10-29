package com.example.user.treasurehunter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IOread extends AppCompatActivity
{
    PinDS pin;
    Group group;
    User user;

    public IOread() { }

    /**
     *                      Use this method to read and return the entire file
     *  @param searchingFor Include the specific file name you are searching for
     *  @param id           If the file you are trying to read has an id, specify the id
     *  @param context      Include the context you are working in
     *  @return             The contents of specified file with EOF removed at the end
     */
    public String read(String searchingFor, String id, Context context) throws FileNotFoundException
    {
        String dir = context.getFilesDir() + "/" + id + searchingFor + ".txt";
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
        String fin = resultStringBuilder.toString();
        if(!fin.isEmpty())
        {
            fin = fin.substring(0, fin.length() - 4);
        }

        return fin;
    }

    /**
     *                      Use this method to retrieve a specific pin from file
     *  @param pinID        Specify the id of the pin you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The pin associated with the id
     */
    public PinDS retrievePin(String pinID, Context context)
    {
        PinDS retrievedPin = null;
        try
        {
            String everything = read("pins","", context);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",14);
                if(foundLine[0].equals(pinID))
                {
                    if(foundLine[1].equals("Treasure Pin"))
                    {
                        retrievedPin = new PinClassTreasure();
                    }
                    else if(foundLine[1].equals("PinClassShipwreck Pin"))
                    {
                        retrievedPin = new PinClassShipwreck();
                    }
                    else if(foundLine[1].equals("Scavenger Hunt Pin"))
                    {
                        retrievedPin = new PinClassScavengerHunt();
                    }
                    else if(foundLine[1].equals("Survivor Pin"))
                    {
                        retrievedPin = new PinMoveableClassSurvivor();
                        ((PinMoveableClassSurvivor) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((PinMoveableClassSurvivor) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("Forest Fire Pin"))
                    {
                        retrievedPin = new PinMoveableClassForestFire();
                        ((PinMoveableClassForestFire) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((PinMoveableClassForestFire) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("Whale Pin"))
                    {
                        retrievedPin = new PinMoveableClassWhale();
                        ((PinMoveableClassWhale) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((PinMoveableClassWhale) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else if(foundLine[1].equals("PinMoveableClassHunting Pin"))
                    {
                        retrievedPin = new PinMoveableClassHunting();
                        ((PinMoveableClassHunting) retrievedPin).setDegree(Double.parseDouble(foundLine[11]));
                        ((PinMoveableClassHunting) retrievedPin).setSpeed(Double.parseDouble(foundLine[12]));
                    }
                    else
                    {
                        retrievedPin = new PinMoveableClassCustom();
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

    /**
     *                      Use this method to retrieve a specific group from file
     *  @param groupID      Specify the id of the group you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The group associated with the id
     */
    public Group retrieveGroup(String groupID, Context context)
    {
        Group retrievedGroup = null;
        try
        {
            String everything = read("groups","", context);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",6);
                if(foundLine[0].equals(groupID))
                {
                    retrievedGroup = new Group(foundLine[1], foundLine[4], foundLine[3], foundLine[2]);
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

    /**
     *                      Use this method to retrieve a specific user from file
     *  @param userID       Specify the id of the user you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The user associated with the id
     */
    public User retrieveUser(String userID, Context context)
    {
        User retrievedUser = null;
        try
        {
            String everything = read("users","", context);
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

    /**
     *                      Use this method to get every id that exists in a specified file
     *  @param searchingFor Specify what file you are searching for
     *  @param context      Include the context you are working in
     *  @return             The list of every id that exists in the specified file
     */
    public ArrayList<String> existingIDs (String searchingFor, Context context)
    {
        ArrayList<String> existingIDs = new ArrayList<>();
        try
        {
            String everything =  read(searchingFor, "", context);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length - 1; i++)
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

    /**
     *                      Use this method to read the audit of the specific group
     *  @param groupID      Specify the id of the group you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The audit log of the group specified by ID
     */
    public String readGroupAudit(String groupID, Context context)
    {
        String fullAudit = "";
        try
        {
            String everything = read("groupaudit", groupID, context);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length - 1; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",7);
                System.out.println(foundLine[3]);
                fullAudit += foundLine[1] + " " + foundLine[2] + " - " + foundLine[3];
                if(foundLine[0].equals("0"))
                {
                    fullAudit += " Created This Group";
                }
                else if (foundLine[0].equals("1"))
                {
                    fullAudit += (" Placed Pin: " + foundLine[5] + " ID: " + foundLine[6]);
                }
                else if (foundLine[0].equals("2"))
                {
                    fullAudit += (" Removed Pin: " + foundLine[5]);
                }
                else if (foundLine[0].equals("3"))
                {
                    fullAudit += (" Added Member: " + foundLine[5] + " ID: " + foundLine[6]);
                }
                else
                {
                    fullAudit += (" Deleted Member: " + foundLine[5] + " ID: " + foundLine[6]);
                }
                fullAudit += "\n";
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return fullAudit;
    }

    /**
     *                      Use this method to get and format for display all the members and their permissions
     *  @param groupID      Specify the id of the group you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The display of all existing members and their permissions
     */
    public String readGroupMembers(String groupID, Context context)
    {
        String fullAudit = "";
        try
        {
            String everything = read("members", groupID, context);
            String[] eachLine = everything.split("\n", 1000);
            for(int i = 0; i < eachLine.length - 1; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",3);
                fullAudit += (foundLine[0] + " is able to " + foundLine[1]);
                if(i != eachLine.length - 1)
                {
                    fullAudit += "\n";
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return fullAudit;
    }

    /**
     *                      Use this method to get the users audit formatted for display
     *  @param userID       Specify the id of the user you are trying to retrieve
     *  @param context      Include the context you are working in
     *  @return             The audit associated with the user
     */
    public String readUserAudit(String userID, Context context)
    {
        String fullAudit = "";
        try
        {
            String everything = read("useraudit",userID, context);
            String[] eachLine = everything.split("\n", 1000);
            System.out.println(everything);
            for(int i = 0; i < eachLine.length - 1; i++)
            {
                String[] foundLine = eachLine[i].split("\\*",7);
                fullAudit += foundLine[1] + " " + foundLine[2] + " - " + foundLine[3];
                if (foundLine[0].equals("0"))
                {
                    fullAudit += (" created the account" + "\n"
                            + "**********************************************" + "\n");
                }
                if (foundLine[0].equals("1"))
                {
                    fullAudit += (" created the Group: " + foundLine[4] + "\n"
                            + "**********************************************" + "\n");
                }
                if (foundLine[0].equals("2"))
                {
                    fullAudit += (" deleted the Group: " + foundLine[4] + "\n"
                            + "**********************************************" + "\n");
                }
                if (foundLine[0].equals("3"))
                {
                    fullAudit += (" left the Group: " + foundLine[4] + "\n"
                            + "**********************************************" + "\n");
                }
                if (foundLine[0].equals("4"))
                {
                    fullAudit += (" created the Pin: " + foundLine[5] + " for Group: " + foundLine[4] + "\n"
                            + "*********************************************" + "\n");
                }
                else
                {
                    fullAudit += (" deleted the Pin: " + foundLine[5] + " for Group: " + foundLine[4] + "\n"
                            + "*********************************************" + "\n");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return fullAudit;
    }
}
