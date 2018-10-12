package com.example.user.treasurehunter;

import java.io.Serializable;
import java.time.LocalDateTime;
import android.location.*;

public abstract class PinDS implements Serializable
{
    private double latitude;
    private double longitude;
    private double altitude;
    private String namePin;
    private String description;
    private String namePublisher;
    private String color;
    private String time;
    private String date;

    public PinDS(double latitude, double longitude, double altitude,
                 String namePin, String description, String namePublisher, String color)
    {

    }

    public PinDS(){}

    // All the Setters and Getters
    public double getLatitude()
        { return latitude; }
    public void setLatitude(double latitude)
        { this.latitude = latitude; }
    public double getLongitude()
        { return longitude; }
    public void setLongitude(double longitude)
        { this.longitude = longitude; }
    public String getNamePin()
        { return namePin; }
    public void setNamePin(String namePin)
        { this.namePin = namePin; }
    public String getDescription()
        { return description; }
    public void setDescription(String description)
        { this.description = description; }
    public String getNamePublisher()
        { return namePublisher; }
    public void setNamePublisher(String namePublisher)
        { this.namePublisher = namePublisher; }
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
}
