package com.example.user.treasurehunter;

import android.content.Context;
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

    // Instantiate time as soon as IOwrite is created
    public IOwrite()
    {
        Calendar calendar = Calendar.getInstance();
        currentTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        IOread reader = new IOread();
    }

    // Use this to write to a file
    // Specify file directory with fileDir
    // Specify what to write with data
    // Specify what data type you are writing in
    // Specify the context in which you are working in
    public void write(String data, String searchingFor, Context context)
    {
        String dir = context.getFilesDir() + "/" + searchingFor + ".txt";
        System.out.println(dir);
        File file = new File(dir);
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            System.out.println(data);
            System.out.println(context.toString());
            data = reader.read(searchingFor, "", context) + data;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed or FileOutputStream exception: " + e.toString());
        }
    }

    // Use this to write down a new pin that has been created
    // Set the pin you wish to write down in pin
    // Set the context in which you are working in
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

    // Use this to write down a new group that has been created
    // Set the group you wish to write down in group
    // Set the context in which you are working in
    public void writeGroup(Group group, Context context)
    {
        String data = (group.getGroupID() + "*" + group.getGroupName() + "*" + group.getAdminID() + "*"
                     + group.getAdminName() + "*" + group.getGroupDescription() + "*");
        write(data,"groups", context);

        data = ("0*" + currentTime + "*" + currentDate + "*" + group.getAdminName() + "*" + group.getAdminID());
        write(data,group.getGroupID() + "groupaudit", context);
    }

    // Use this to write down a new user that has been created
    // Set the user you wish to write down in user
    // Set the context in which you are working in
    public void writeUser(User user, Context context)
    {
        String data = (user.getUserID() + "*" + user.getUserName() + "*" + user.getPassword());
        write(data,"users", context);
    }

    // Remove an object from one of the three general lists or member list
    // Include the specific file name you are searching for
    // Include the specific ID to remove
    // Include the groupID of a members list, leave blank if not looking for members list
    // Include the context you are working in
    public void removeObject(String searchingFor, String idToRemove, String groupID, Context context)
    {
        String everything = "";
        String newEverything = "";
        try
        {
            everything = reader.read(searchingFor, groupID, context);
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
                newEverything += eachLine[i] + "\n";
            }
        }
        write(newEverything, searchingFor, context);
    }

    public void removeFile(String fileName, String id, Context context)
    {
        String dir = context.getFilesDir() + "/" + id + fileName + ".txt";
        File file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
    }


















    ////////////////////////////////////////////////////////////Actual Method

    public void writeAudit(String groupID, int action, User user, User deletedUser, PinDS pin, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/" + groupID + "group_audit.txt";
        File file = new File(dir);
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + action + "*" + currentTime + "*" + currentDate + "*" + user.getUserName() + "*" + user.getUserID());
            if(action == 2)
            {
                data += ("*" + pin.getPinTitle());
                if(action == 1)
                {
                    data += ("*" + pin.getPinID());
                }
            }
            else {
                data += ("*" + deletedUser.getUserName() + "*" + deletedUser.getUserID());
            }
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }



    ///////////////////////////////////////////////////////////Testing Method

    public void writeAuditTest(int action, PinDS pin, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/personal_audit.txt";
        File file = new File(dir);
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + action + "*" + currentTime + "*" + currentDate + "*" + pin.getPublisher());
            if(action == 2 || action == 1)
            {
                data += ("*" + pin.getPinTitle());
                if(action == 1)
                {
                    data += ("*" + pin.getPinID());
                }
            }
            data += "\nEOF";
            System.out.println(data);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }























    ////////////////////////////////////////////////////////////Actual Method

    public void writeMembers(String groupID, int action, User user, User deletedUser, PinDS pin, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/" + groupID + "group_audit.txt";
        File file = new File(dir);
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + action + "*" + currentTime + "*" + currentDate + "*" + user.getUserName() + "*" + user.getUserID());
            if(action == 2)
            {
                data += ("*" + pin.getPinTitle());
                if(action == 1)
                {
                    data += ("*" + pin.getPinID());
                }
            }
            else {
                data += ("*" + deletedUser.getUserName() + "*" + deletedUser.getUserID());
            }
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }



    ///////////////////////////////////////////////////////////Testing Method

    public void writeMembersTest(String groupID, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/" + groupID + "members.txt";
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

        try
        {
            IOread reader = new IOread();
            Group group = reader.retrieveGroup(groupID, context);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = "";
            if(!group.getMembersID().isEmpty())
            {
                for(int i = 0; i > group.getMembersID().size(); i++)
                {
                    //test
                    data += group.getMembersID().get(i);

                    //actual
                    //String anid = group.getMembersID().get(i);
                    //data += reader.retrieveUser(context, anid).getUserName() + "\n";
                }
            }
            data += "EOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }
    }

















    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //             CODE ONLY FOR TESTING BELOW
    ///////////////////////////////////////////////////////////////////////////////////////////////

    // Clear all existing files
    public void clearData(Context context)
    {
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
    }
}

