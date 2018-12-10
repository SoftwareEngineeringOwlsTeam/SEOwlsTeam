package com.example.user.treasurehunter;

import java.io.Serializable;

/**
 * Class that creates the Data structure to be used by all Pins.
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
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
    private String publisher;
    private String publisherID;
    private String pinName;
    private String radius;
    private String pinTitle;
    protected int defaultColor;
    protected String pinNameHint;
    protected String descriptionHint;
    protected String radiusHint;

    public PinDS(){}

    /**
     * Method to get a Pin ID.
     * @return The Pin ID.
     */
    public String getPinID()
    {
        return pinID;
    }

    /**
     * Method to set a Pin ID.
     * @param pinID Include the Pin ID.
     */
    public void setPinID(String pinID)
    {
        this.pinID = pinID;
    }

    /**
     * Method to get the Latitude for a Pin.
     * @return The Latitude.
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Method to set the Latitude for a Pin.
     * @param latitude Include the Latitude.
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * Method to get the Longitude for a Pin.
     * @return The Longitude.
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Method to set the Longitude for a Pin.
     * @param longitude Imclude the Longitude.
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * Method to get the Description of a Pin.
     * @return The Description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method to set the Description for a Pin.
     * @param description Include the Description.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Method to get the Color of a Pin.
     * @return The Color.
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Method to set the Color for a Pin.
     * @param color Include the Color.
     */
    public void setColor(String color)
    {
        this.color = color;
    }

    /**
     * Method to get the Altitude of a Pin.
     * @return The Altitude.
     */
    public double getAltitude()
    {
        return altitude;
    }

    /**
     * Method to set the Altitude for a Pin.
     * @param altitude Include the Altitude.
     */
    public void setAltitude(double altitude)
    {
        this.altitude = altitude;
    }

    /**
     * Method to set the Time for a Pin.
     * @param time Include the Time.
     */
    public void setTime(String time)
    {
        this.time = time;
    }

    /**
     * Method to get the Time a Pin was created.
     * @return The time.
     */
    public String getTime()
    {
        return time;
    }

    /**
     * Method to set the Date of a pin.
     * @param date Include the Date.
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * Method to get the Date a pin was created.
     * @return The Date.
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Method to set the creator (Publisher) of a Pin.
     * @param publisher Include the creator(Publisher).
     */
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    /**
     * Method to get the creator (Publisher) of a Pin.
     * @return The creator (Publisher).
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * Method to set the name of a Pin.
     * @param pinName Include the name.
     */
    public void setPinName(String pinName)
    {
        this.pinName = pinName;
    }

    /**
     * Method to get the name of a Pin.
     * @return The name.
     */
    public String getPinName()
    {
        return this.pinName;
    }

    /**
     * Method to set the Radius for a pin.
     * @param radius Include the Radius.
     */
    public void setRadius(String radius)
    {
        this.radius = radius;
    }

    /**
     * Method to get the Radius of a Pin.
     * @return The Radius
     */
    public String getRadius()
    {
        return radius;
    }

    /**
     * Method to get the Title of a pin.
     * @return The title.
     */
    public String getPinTitle()
    {
        return pinTitle;
    }

    /**
     * Method to set the title for a pin.
     * @param pinTitle Include the title.
     */
    public void setPinTitle(String pinTitle)
    {
        this.pinTitle = pinTitle;
    }

    /**
     * Method to get the Default Color of a pin.
     * @return The color.
     */
    public int getDefaultColor()
    {
        return defaultColor;
    }

    /**
     * Method to set the Default Color for a pin.
     * @param hexColor Include the Default Color.
     */
    protected void setDefaultColor(int hexColor)
    {
        defaultColor = hexColor;
    }

    /**
     * Method to get the ID of the publisher.
     * @return The publishers ID.
     */
    public String getPublisherID()
    {
        return publisherID;
    }

    /**
     * Method to set the ID of the publisher.
     * @param publisherID Include the publishers ID.
     */
    public void setPublisherID(String publisherID)
    {
        this.publisherID = publisherID;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public String getDescriptionHint()
    {
        return descriptionHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void setDescriptionHint(String descriptionHint)
    {
        this.descriptionHint = descriptionHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public String getPinNameHint()
    {
        return pinNameHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void setPinNameHint(String pinNameHint)
    {
        this.pinNameHint = pinNameHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public String getRadiusHint()
    {
        return radiusHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void setRadiusHint(String radiusHint)
    {
        this.radiusHint = radiusHint;
    }
}
