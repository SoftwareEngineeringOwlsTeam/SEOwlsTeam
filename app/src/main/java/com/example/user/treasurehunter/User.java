package com.example.user.treasurehunter;

import java.util.ArrayList;

public class User
{
    private String userID;
    private String userName;
    private String password;
    private ArrayList<String> personalPinID;
    private ArrayList<String> associatedGroupID;

    public User(String userID, String userName, String password)
    {
        this.userID = userID;
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

    // All the getters and setters

    public String getUserID()
    {
        return userID;
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

    public ArrayList<String> getPersonalPinID() {
        return personalPinID;
    }

    public void setPersonalPinID(ArrayList<String> personalPinID) {
        this.personalPinID = personalPinID;
    }

    public ArrayList<String> getAssociatedGroupID() {
        return associatedGroupID;
    }

    public void setAssociatedGroupID(ArrayList<String> associatedGroupID) {
        this.associatedGroupID = associatedGroupID;
    }
}
