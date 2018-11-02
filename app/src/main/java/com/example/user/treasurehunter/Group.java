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
    private ArrayList<String> associatedPinIDs;
    // Maybe a field that gives out certain permissions to speccific users

    public Group(String adminID, String groupDescription, String groupName, String adminName, String groupID)
    {
        // Automatically fill in the adminID with the users who created the apps ID's
        // When group created you initially state the users you want in there (their ID's)
        this.groupDescription = groupDescription;
        this.groupName = groupName;
        this.adminName = adminName;
        this.adminID = adminID;
        this.groupID = groupID;
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

    public ArrayList<String> getAssociatedPinIDs() {
        return associatedPinIDs;
    }

    public void setAssociatedPinIDs(ArrayList<String> associatedPinIDs) {
        this.associatedPinIDs = associatedPinIDs;
    }
}
