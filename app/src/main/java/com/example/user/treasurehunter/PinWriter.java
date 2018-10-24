package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class PinWriter extends AppCompatActivity implements Serializable
{

    private String currentDate;
    private String currentTime;
    private Date time = Calendar.getInstance().getTime();

    public PinWriter()
    {
        Calendar calendar = Calendar.getInstance();
        currentTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
    }

    public void writePin(PinDS pin, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/pins.txt";
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
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + pin.getPinID() + "*" + pin.getPinName() + "*" + pin.getPinTitle() + "*" + pin.getPublisher() + "*" +
                    pin.getDescription() + "*" + pin.getRadius() + "*" + pin.getLatitude() + "*"
                    + pin.getLongitude() + "*" + pin.getAltitude() + "*" + pin.getTime() + "*" + pin.getDate());
            if (pin instanceof MoveablePin)
            {
                data += ("*" + ((MoveablePin) pin).getDegree() + "*" + ((MoveablePin) pin).getSpeed());
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



    public void writeGroup(Group group, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/groups.txt";
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
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + group.getGroupID() + "*" + group.getGroupName() + "*" + group.getAdminID() + "*" + group.getAdminName() + "*" +
                                        group.getGroupDescription() + "*");
            if(group.getMembersID() != null)
            {
                for(int i = 0; i < group.getMembersID().size(); i++)
                {
                    data += group.getMembersID().get(i) + "/";
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



        dir = context.getFilesDir() + "/" + group.getGroupID() + "group_audit.txt";
        file = new File(dir);
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
                try
                {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
                    String data = ("0*" + currentTime + "*" + currentDate + "*" + group.getAdminName() + "*" + group.getAdminID());
                    data += "\nEOF";
                    outputStreamWriter.write(data);
                    outputStreamWriter.close();
                }
                catch (IOException e)
                {
                    System.out.println("File write failed: " + e.toString());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("FileOutputStream exception: - " + e.toString());
        }
    }



    public void writeUser(User user, String previousText, Context context)
    {
        String dir = context.getFilesDir() + "/users.txt";
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
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = (previousText + user.getUserID() + "*" + user.getUserName() + "*" + user.getPassword() + "*");
            if(user.getAssociatedGroupID() != null)
            {
                for(int i = 0; i > user.getAssociatedGroupID().size(); i++)
                {
                    data += user.getAssociatedGroupID().get(i);
                }
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

    public void removeObject(Context context, String whatOf, String id)
    {
        PinReader reader = new PinReader();
        String everything = "";
        try
        {
            if(whatOf.equals("ppins"))
            {
                everything = reader.read(context,"PersonalPins","");
            }
            else if(whatOf.equals("groups"))
            {
                everything = reader.read(context,"Groups","");
            }
            else if(whatOf.equals("users"))
            {
                everything = reader.read(context,"Users","");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String newEverything = "";
        String[] eachLine = everything.split("\n", 1000);
        for(int i = 0; i < eachLine.length; i++)
        {
            String[] foundLine = eachLine[i].split("\\*",14);
            if(!foundLine[0].equals(id))
            {
                newEverything += eachLine[i] + "\n";
            }
        }



        ///////////////////////////////////////////////////BAD CODE



        String dir = context.getFilesDir() + "/groups.txt";
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
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE));
            String data = newEverything;
            data += "\nEOF";
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("File write failed: " + e.toString());
        }

        /////////////////////////////////////////////////////////////////////////BAD CODE











    }

    public void clearData(Context context)
    {
        String dir = context.getFilesDir() + "/pins.txt";
        File file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/groups.txt";
        file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/users.txt";
        file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
        dir = context.getFilesDir() + "/personal_audit.txt";
        file = new File(dir);
        if(file.exists())
        {
            file.delete();
        }
    }



    public void removeGroupAudit(Context context, String groupID)
    {
        String dir = context.getFilesDir() + "/" + groupID + "users.txt";
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
            PinReader reader = new PinReader();
            Group group = reader.retrieveGroup(context, groupID);
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
}

