package com.example.user.treasurehunter;

import java.util.ArrayList;
import java.util.Random;

public class Group
{
    private String groupID;
    private String adminID;
    private String adminName;
    private String groupName;
    private String groupDescription;
    private ArrayList<String> groupPinID;

    private static ArrayList<String> existingIDs = new ArrayList<>();
    // Maybe a field that gives out certain permissions to speccific users

    public Group(String adminID, String groupDescription, String groupName, String adminName)
    {
        // Check all existing groups in database and generate a unique ID for this group
        boolean generated = false;
        while (!generated)
        {
            groupID = "";
            Random rand = new Random();
            for(int j = 0; j <= 9; j++)
            {
                groupID += String.valueOf(rand.nextInt(9));
            }
            generated = true;
            for(int i = 0; i < existingIDs.size(); i++)
            {
                if(existingIDs.get(i).equals(groupID))
                {
                    generated = false;
                }
            }
        }

        // Automatically fill in the adminID with the users who created the apps ID's
        // When group created you initially state the users you want in there (their ID's)
        this.groupDescription = groupDescription;
        this.groupName = groupName;
        this.adminName = adminName;
        this.adminID = adminID;
    }

    // All the getters and setters

    public String getGroupID()
    {
        return groupID;
    }

    public void setGroupID(String groupID)
    {
        this.groupID = groupID;
    }

    public String getAdminID()
    {
        return adminID;
    }

    public void setAdminID(String adminID)
    {
        this.adminID = adminID;
    }

    public ArrayList<String> getGroupPins()
    {
        return groupPinID;
    }

    public void setGroupPins(ArrayList<String> groupPins)
    {
        this.groupPinID = groupPins;
    }

    public String getGroupDescription()
    {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription)
    {
        this.groupDescription = groupDescription;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getAdminName()
    {
        return adminName;
    }

    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }
}
