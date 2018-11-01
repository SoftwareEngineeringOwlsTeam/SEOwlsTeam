package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
            outputStreamWriter.write(data);
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
                     + pin.getAltitude()    + "*" + pin.getTime()    + "*" + pin.getDate());
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
        write(data,"groups", context);

        data = ("0*" + currentTime + "*" + currentDate + "*" + group.getAdminName() + "*" + group.getAdminID());
        write(data,group.getGroupID() + "groupaudit", context);
    }

    /**
     *                Use this to write down a new user that has been created
     * @param user    Set the user you wish to write down in user
     * @param context Set the context in which you are working in
     */
    public void writeUser(User user, Context context)
    {
        String data = (user.getUserID() + "*" + user.getUserName() + "*" + user.getPassword());
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
            if(action == 2)
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
        String data = (action + "*" + currentTime + "*" + currentDate + "*" + groupID);
        if(action == 4)
        {
            PinDS pin = reader.retrievePin(pinID, context);
            data += ("*TestGroup*" + pin.getPinTitle());
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

    public void writeMembers(ArrayList<String> members, ArrayList<String> permissions, String groupID, Context context)
    {
        String data = "";
        for(int i = 0; i < members.size(); i++)
        {
            data += members.get(i) + "*" + permissions.get(i);
            if(i != members.size() - 1)
            {
                data += "\n";
            }
        }
        write(data, (groupID + "members"), context);
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

