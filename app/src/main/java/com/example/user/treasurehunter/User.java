package com.example.user.treasurehunter;

import java.util.ArrayList;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
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
        this.userName = userName;
        this.password = password;
    }

    /**
     * Method that gets an User's ID.
     * @return The user's ID.
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * Method that gets a User's Name.
     * @return The user's Name.
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * Method that sets an User's Name.
     * @param userName Include the Name to set for the User.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * Method that gets a Password.
     * @return The Password.
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Method that sets a Password.
     * @param password Include the Password to set.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Method that gets a list of Personal Pin IDs.
     * @return List of personal pin IDs.
     */
    public ArrayList<String> getPersonalPinID()
    {
        return personalPinID;
    }

    /**
     * Method that sets a Personal Pin ID.
     * @param personalPinID Include a list of personal pin IDs.
     */
    public void setPersonalPinID(ArrayList<String> personalPinID)
    {
        this.personalPinID = personalPinID;
    }

    /**
     * Method that gets all groups an User is Associated with.
     * @return The groups an user is Associated with.
     */
    public ArrayList<String> getAssociatedGroupID()
    {
        return associatedGroupID;
    }

    /**
     * Method that assigns a list of groups to be Associated with an user.
     * @param associatedGroupID Include the list of groups.
     */
    public void setAssociatedGroupID(ArrayList<String> associatedGroupID)
    {
        this.associatedGroupID = associatedGroupID;
    }
}
