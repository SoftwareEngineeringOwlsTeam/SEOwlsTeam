package com.example.user.treasurehunter;

import java.util.ArrayList;

public class User
{
    private String userID;
    private String userName;
    private String password;
    private pinArray personalPins;
    private ArrayList<Group> associatedGroups;

    public User(String userName, String password)
    {
        // Sets the username that you stated when creating account
        this.userName = userName;
        // Sets the password that you stated when creating account
        this.password = password;
        // Must also generate a unique ID different from other users
    }

    /*
    public void addPin(PinDS newPin)
    {
        personalPins.addPin(newPin);
    }
    */

    public void addGroup(Group newGroup)
    {
        associatedGroups.add(newGroup);
    }






    // All the getters and setters

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public pinArray getPersonalPins()
    {
        return personalPins;
    }

    public void setPersonalPins(pinArray personalPins)
    {
        this.personalPins = personalPins;
    }

    public ArrayList<Group> getAssociatedGroups()
    {
        return associatedGroups;
    }

    public void setAssociatedGroups(ArrayList<Group> associatedGroups)
    {
        this.associatedGroups = associatedGroups;
    }
}
