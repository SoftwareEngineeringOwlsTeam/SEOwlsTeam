package com.example.user.treasurehunter;

import java.time.LocalDateTime;
import android.location.*;

public class PinDS
{
    private double latitude;
    private double longitude;
    private double altitude;
    private String namePin;
    private String description;
    private String namePublisher;
    private String color;
    private LocalDateTime time;
    private Location location;

    public PinDS(double latitude)
    {
        String l = location.getProvider();
        //location = new LocationManager();
    }

    // All the Setters and Getters
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getNamePin() { return namePin; }
    public void setNamePin(String namePin) { this.namePin = namePin; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getNamePublisher() { return namePublisher; }
    public void setNamePublisher(String namePublisher) { this.namePublisher = namePublisher; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }
    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }
}
