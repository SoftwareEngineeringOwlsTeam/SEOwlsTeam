package com.example.user.treasurehunter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static com.example.user.treasurehunter.LogInScreen.currentActiveUser;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class IOwrite extends AppCompatActivity implements Serializable
{
    private String currentDate;
    private String currentTime;
    private Date time = Calendar.getInstance().getTime();
    private IOread reader;
    private File file;

    /**
     *  Instantiate time as soon as IOwrite is created
     */
    public IOwrite()
    {
        Calendar calendar = Calendar.getInstance();
        currentTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
    }

    /**
     *                     Use this method to write to a file
     * @param data         Specify what to write with data
     * @param searchingFor Specify what data type you are writing in
     * @param context      Specify the context in which you are working in
    */
    public void write(String data, String searchingFor, Context context)
    {
        reader = new IOread();
        String dir = context.getFilesDir() + "/" + searchingFor + ".txt";
        file = new File(dir);
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            if(!reader.read(searchingFor, "", context).equals(""))
            {
                data = reader.read(searchingFor, "", context)  + "\n" + data;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_PRIVATE));
            data += "\nEOF";


            String dataFinal = "";

            String[] eachLine = data.split("\n", 1000);
            for(int i = 0; i < eachLine.length; i++)
            {
                System.out.println("############ " + dataFinal);
                if(!eachLine[i].equals(""))
                {
                    dataFinal += eachLine[i] + "\n";
                }
            }




            outputStreamWriter.write(dataFinal);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed or FileOutputStream exception: " + e.toString());
        }
    }

    /**
     *                Use this method to write down a new pin that has been created
     * @param pin     Set the pin you wish to write down in pin
     * @param context Set the context in which you are working in
     */
    public void writePin(PinDS pin, Context context)
    {
        String data = (pin.getPinID()       + "*" + pin.getPinName() + "*" + pin.getPinTitle() + "*" + pin.getPublisher() + "*"
                     + pin.getDescription() + "*" + pin.getRadius()  + "*" + pin.getLatitude() + "*" + pin.getLongitude() + "*"
                     + pin.getAltitude()    + "*" + pin.getTime()    + "*" + pin.getDate()     + "*" + pin.getPublisherID());
        if (pin instanceof PinMoveable)
        {
            data += ("*" + ((PinMoveable) pin).getDegree() + "*" + ((PinMoveable) pin).getSpeed());
        }
        write(data,"pins", context);
    }

    /**
     *                Use this method to write down a new group that has been created
     * @param group   Set the group you wish to write down in group
     * @param context Set the context in which you are working in
     */
    public void writeGroup(Group group, Context context)
    {
        String data = (group.getGroupID() + "*" + group.getGroupName() + "*" + group.getAdminID() + "*"
                     + group.getAdminName() + "*" + group.getGroupDescription() + "*");
        if(group.getAssociatedPinIDs() != null)
        {
            for(int i = 0; i < group.getAssociatedPinIDs().size(); i++)
            {
                if(i != 0)
                {
                    data = data + "/";
                }
                data = data + group.getAssociatedPinIDs().get(i);
            }
        }
        write(data,"groups", context);
    }

    /**
     *                Use this to write down a new user that has been created
     * @param user    Set the user you wish to write down in user
     * @param context Set the context in which you are working in
     */
    public void writeUser(User user, Context context)
    {
        String data = (user.getUserID() + "*" + user.getUserName() + "*" + user.getPassword() + "*");
        if(user.getPersonalPinID() != null)
        {
            for(int i = 0; i < user.getPersonalPinID().size(); i++)
            {
                if(i != 0)
                {
                    data = data + "/";
                }
                data = data + user.getPersonalPinID().get(i);
            }
        }
        data += "*";
        if(user.getAssociatedGroupID() != null)
        {
            for (int i = 0; i < user.getAssociatedGroupID().size(); i++)
            {
                if (i != 0)
                {
                    data = data + "/";
                }
                data = data + user.getAssociatedGroupID().get(i);
            }
        }
        write(data,"users", context);
    }

    /**
     *                     Remove an object from one of the three general lists or member list
     * @param searchingFor Include the specific file name you are searching for
     * @param idToRemove   Include the specific ID of object to remove
     * @param groupID      Include the groupID of a members list, leave blank if not looking for members list
     * @param context      Include the context you are working in
     */
    public void removeObject(String searchingFor, String idToRemove, String groupID, Context context)
    {
        reader = new IOread();
        String everything = "";
        String newEverything = "";
        try
        {
            everything = reader.read(searchingFor, "", context);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String[] eachLine = everything.split("\n", 1000);
        for(int i = 0; i < eachLine.length; i++)
        {
            String[] foundLine = eachLine[i].split("\\*",14);
            if(!foundLine[0].equals(idToRemove))
            {
                newEverything += eachLine[i];
                if(i != eachLine.length - 1)
                {
                    newEverything += "\n";
                }
            }
            System.out.println(newEverything + "\nend");
        }
        removeFile(searchingFor, "", context);
        write(newEverything, searchingFor, context);
    }

    /**
     *                 Use this method to delete an entire file
     * @param fileName Specify the name of the file you wish to delete
     * @param id       If file has an id, specify the id
     * @param context  Include the context you are working in
     */
    public void removeFile(String fileName, String id, Context context)
    {
        String dir = context.getFilesDir() + "/" + id + fileName + ".txt";
        File file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
    }

    public void writeGroupAudit(String groupID, int action, User user, String deletedUserID, String pinID, Context context)
    {
        String data = (action + "*" + currentTime + "*" + currentDate + "*" + user.getUserName() + "*" + user.getUserID());
        if(!pinID.equals(""))
        {
            PinDS pin = reader.retrievePin(pinID, context);
            if(action == 2 || action == 1)
            {
                data += ("*" + pin.getPinTitle());
                if(action == 1)
                {
                    data += ("*" + pin.getPinID());
                }
            }
        }
        if(!deletedUserID.equals(""))
        {
            User dUser = reader.retrieveUser(deletedUserID, context);
            data += ("*" + dUser.getUserName() + "*" + dUser.getUserID());
        }
        write(data, (groupID + "groupaudit"), context);
    }

    public void writeUserAudit(String userID, int action, String pinID, String groupID, Context context)
    {
        //0. You created account
        //1. You created a group
        //2. You created a pin for a group
        //3. You deleted a group
        //4. You left a group
        //5. You deleted a pin for a group

        //for testing
        String data = (action + "*" + currentTime + "*" + currentDate);
        if(action == 1)
        {
            Group group = reader.retrieveGroup(groupID, context);
            data += ("*" + group.getGroupName());
        }
        if(action == 4)
        {
            Group group = reader.retrieveGroup(groupID, context);
            PinDS pin = reader.retrievePin(pinID, context);
            data += ("*" + group.getGroupName() + "*" + pin.getPinTitle());
        }
        if(action == 5)
        {
            PinDS pin = reader.retrievePin(pinID, context);
            data += ("*" + pin.getPinTitle());
        }

        /*
        User theUser = reader.retrieveUser(userID, context);
        String data = (action + "*" + currentTime + "*" + currentDate + "*" + theUser.getUserName());

        if(action > 0)
        {
            Group group = reader.retrieveGroup(groupID, context);
            data += ("*" + group.getGroupName());
            if(action > 3)
            {
                PinDS pin = reader.retrievePin(pinID, context);
                data += ("*" + pin.getPinTitle());
            }
        }
        */
        write(data, (userID + "useraudit"), context);
    }

    public void writeMembers(ArrayList<String> membersID, ArrayList<String> membersName, ArrayList<String> permissions, String groupID, Context context)
    {
        String data = "";
        for(int i = 0; i < membersName.size(); i++)
        {
            data += membersID.get(i) + "*" + membersName.get(i) + "*" + permissions.get(i);
            if(i != membersName.size() - 1)
            {
                data += "\n";
            }
        }
        write(data, (groupID + "members"), context);
    }

    /**
     *                     Use this method add a specific association of someone to pin or someone to group
     * @param addingID     Specify what exactly are you adding
     * @param addingToWhat Specify if adding, personal pin(ppin), group pin(gpin), or associated group(association)
     * @param groupID      If adding to a group, or adding a group, specify the group id
     * @param context      Include the context you are working in
     */
    public void addAssociation(User toUser, ArrayList<String> addingID, String addingToWhat, String groupID, Context context)
    {
        if(addingToWhat.equals("gpin"))
        {
            Group changedGroup = reader.retrieveGroup(groupID, context);
            removeObject("groups", groupID, "", context);
            if(changedGroup.getAssociatedPinIDs() != null)
            {
                for(int i = 0; i < changedGroup.getAssociatedPinIDs().size(); i++)
                {
                    addingID.add(changedGroup.getAssociatedPinIDs().get(i));
                }
                changedGroup.setAssociatedPinIDs(addingID);
                writeGroup(changedGroup, context);
            }
        }
        else if(addingToWhat.equals("groupinvite"))
        {
            User changedUser = toUser;
            removeObject("users", changedUser.getUserID(), "", context);
            for(int i = 0; i < changedUser.getAssociatedGroupID().size(); i++)
            {
                addingID.add(changedUser.getAssociatedGroupID().get(i));
            }
            changedUser.setAssociatedGroupID(addingID);
            writeUser(changedUser, context);
        }
        else {
            User changedUser = currentActiveUser;
            removeObject("users", changedUser.getUserID(), "", context);
            if(addingToWhat.equals("ppin"))
            {
                for(int i = 0; i < changedUser.getPersonalPinID().size(); i++)
                {
                    addingID.add(changedUser.getPersonalPinID().get(i));
                }
                changedUser.setPersonalPinID(addingID);
            }
            else{
                for(int i = 0; i < changedUser.getAssociatedGroupID().size(); i++)
                {
                    addingID.add(changedUser.getAssociatedGroupID().get(i));
                }
                changedUser.setAssociatedGroupID(addingID);
            }
            writeUser(changedUser, context);
        }
    }

    public void removeAssociation(User user, ArrayList<String> addingID, String removingFromWhat, String groupID, Context context)
    {
        if(removingFromWhat.equals("gpin"))
        {
//            Group changedGroup = reader.retrieveGroup(groupID, context);
//            removeObject("groups", groupID, "", context);
//            if(changedGroup.getAssociatedPinIDs() != null)
//            {
//                for(int i = 0; i < changedGroup.getAssociatedPinIDs().size(); i++)
//                {
//                    addingID.add(changedGroup.getAssociatedPinIDs().get(i));
//                }
//                changedGroup.setAssociatedPinIDs(addingID);
//                writeGroup(changedGroup, context);
//            }
        }
        else {
            User changedUser = currentActiveUser;
            removeObject("users", currentActiveUser.getUserID(), "", context);
            if(removingFromWhat.equals("ppin"))
            {
                for(int i = 0; i < changedUser.getPersonalPinID().size(); i++)
                {
                    addingID.remove(changedUser.getPersonalPinID().get(i));
                }
                changedUser.setPersonalPinID(addingID);
            }
            else{
                for(int i = 0; i < changedUser.getAssociatedGroupID().size(); i++)
                {
                    addingID.remove(changedUser.getAssociatedGroupID().get(i));
                }
                changedUser.setAssociatedGroupID(addingID);
            }
            writeUser(changedUser, context);
        }
    }












    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //             CODE ONLY FOR TESTING BELOW
    ///////////////////////////////////////////////////////////////////////////////////////////////

    // Clear all existing files
    public void clearData(Context context)
    {
        reader = new IOread();
        ArrayList<String> removeList = reader.existingIDs("groups", context);
        for(int i = 0; i < removeList.size(); i++)
        {
            String dir = context.getFilesDir() + "/" + removeList.get(i) + "groupaudit.txt";
            File file = new File(dir);
            if (file.exists())
            {
                file.delete();
            }
            dir = context.getFilesDir() + "/" + removeList.get(i) + "members.txt";
            file = new File(dir);
            if (file.exists())
            {
                file.delete();
            }
        }
        removeList = reader.existingIDs("users", context);
        for(int i = 0; i < removeList.size(); i++)
        {
            String dir = context.getFilesDir() + "/" + removeList.get(i) + "users.txt";
            File file = new File(dir);
            if (file.exists())
            {
                file.delete();
            }
        }
        String dir = context.getFilesDir() + "/pins.txt";
        File file = new File(dir);
        if (file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/groups.txt";
        file = new File(dir);
        if (file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/users.txt";
        file = new File(dir);
        if (file.exists())
        {
            file.delete();
        }




        //Deletes test user audit
        dir = context.getFilesDir() + "/1234567890useraudit.txt";
        file = new File(dir);
        if (file.exists())
        {
            file.delete();
        }
    }
}
