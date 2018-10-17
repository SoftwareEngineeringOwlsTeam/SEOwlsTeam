package com.example.user.treasurehunter;

import java.io.Serializable;
import java.time.LocalDateTime;
import android.location.*;

public abstract class PinDS implements Serializable
{
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

    public PinDS(double latitude, double longitude, double altitude,
                 String pinName, String description, String publisher, String color)
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
