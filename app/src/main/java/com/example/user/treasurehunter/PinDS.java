package com.example.user.treasurehunter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import android.graphics.Color;

import android.location.*;

public abstract class PinDS implements Serializable
{
    private String pinID;
    private double latitude;
    private double longitude;
    private double altitude;
    private String description;
    protected String color;
    private String time;
    private String date;
    private String publisher = "";
    private String pinName;
    private String radius;
    private String pinTitle;
    private static ArrayList<String> existingPinIDs = new ArrayList<>();
    protected int defaultColor;

    public PinDS(String pinID, double latitude, double longitude, double altitude,
                 String pinName, String description, String publisher, String color,
                 String pinTitle, String time, String date, String radius)
    {

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

    public String getPinTitle() {
        return pinTitle;
    }

    public void setPinTitle(String pinTitle) {
        this.pinTitle = pinTitle;
    }

    public int getDefaultColor()
    {
        return defaultColor;
    }
}
