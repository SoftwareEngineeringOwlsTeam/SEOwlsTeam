package com.example.user.treasurehunter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import android.location.*;

public abstract class PinDS implements Serializable
{
    private String pinID;
    private double latitude;
    private double longitude;
    private double altitude;
    private String description;
    private String color;
    private String time;
    private String date;
    private String publisher = "";
    private String pinName;
    private String radius;
    private String className;
    private static ArrayList<String> existingPinIDs = new ArrayList<>();

    public PinDS(double latitude, double longitude, double altitude,
                 String pinName, String description, String publisher, String color,
                 String className, String time, String date, String radius)
    {
        boolean generated = false;
        while (!generated)
        {
            pinID = "";
            Random rand = new Random();
            for(int j = 0; j <= 9; j++)
            {
                pinID += String.valueOf(rand.nextInt(9));
            }
            generated = true;
            for(int i = 0; i < existingPinIDs.size(); i++)
            {
                if(existingPinIDs.get(i).equals(pinID))
                {
                    generated = false;
                }
            }
        }
    }

    public PinDS(){}

    // All the Setters and Getters
    public String getPinID()
    { return pinID; }
    public void setPinID(String pinID)
    { this.pinID = pinID; }
    public double getLatitude()

        { return latitude; }
    public void setLatitude(double latitude)
        { this.latitude = latitude; }
    public double getLongitude()
        { return longitude; }
    public void setLongitude(double longitude)
        { this.longitude = longitude; }
    public String getDescription()
        { return description; }
    public void setDescription(String description)
        { this.description = description; }
    public String getColor()
        { return color; }
    public void setColor(String color)
        { this.color = color; }
    public double getAltitude()
        { return altitude; }
    public void setAltitude(double altitude)
        { this.altitude = altitude; }
        public void setTime(String time)
        {this.time = time;}
        public String getTime()
        {return time;}
        public void setDate(String date)
        {this.date = date;}
        public String getDate()
        {return date;}
        public void setPublisher(String publisher)
        {
            this.publisher = publisher;
        }
        public String getPublisher()
        {
            return publisher;
        }
        public void setPinName(String pinName)
        {
            this.pinName = pinName;
        }
        public String getPinName()
        {
            return this.pinName;
        }
        public void setRadius(String radius)
        {
            this.radius = radius;
        }
        public String getRadius()
        {
            return radius;
        }

    public String getClassName() {
        return className;
    }
}
