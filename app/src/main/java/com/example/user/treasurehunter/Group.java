package com.example.user.treasurehunter;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
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

    /**
     * Method that sets an unique ID for a group. All groups are checked to make sure the ID is unique.
     * @param adminID Include the Admins ID.
     * @param groupDescription Include the Description for the group.
     * @param groupName Include the Name of the Group.
     * @param adminName Include the Name of the Admin.
     */
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

    /**
     * Method to get the ID for the group.
     * @return The Group ID
     */
    public String getGroupID()
    {
        return groupID;
    }

    /**
     * Method to set the ID for the group.
     * @param groupID Include the ID to set for the group.
     */
    public void setGroupID(String groupID)
    {
        this.groupID = groupID;
    }

    /**
     * Method to get the Admin's ID.
     * @return The Admin's ID.
     */
    public String getAdminID()
    {
        return adminID;
    }

    /**
     * Method to set the ID for the Admin.
     * @param adminID Include the ID to set for the Admin.
     */
    public void setAdminID(String adminID)
    {
        this.adminID = adminID;
    }

    /**
     * Method to get all the IDs for the pins in a group.
     * @return The List of Pin IDs within a groujp.
     */
    public ArrayList<String> getGroupPins()
    {
        return groupPinID;
    }

    /**
     * Method to set the IDs for the pins within a group.
     * @param groupPins Include the List of IDs to set the pins as.
     */
    public void setGroupPins(ArrayList<String> groupPins)
    {
        this.groupPinID = groupPins;
    }

    /**
     * Method to get the Description of a group.
     * @return The Description of a group.
     */
    public String getGroupDescription()
    {
        return groupDescription;
    }

    /**
     * Method to set the Description of a group.
     * @param groupDescription Include the Description to set for the group.
     */
    public void setGroupDescription(String groupDescription)
    {
        this.groupDescription = groupDescription;
    }

    /**
     * Method to get the name of a Group.
     * @return The name of the group.
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * Method to set the Name for a group.
     * @param groupName Include the name to set for the group.
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * Method to get the Name of the Admin.
     * @return The name of the Admin.
     */
    public String getAdminName()
    {
        return adminName;
    }

    /**
     * Method to set the Name for an Admin.
     * @param adminName Include the name to set for an Admin.
     */
    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }
}
