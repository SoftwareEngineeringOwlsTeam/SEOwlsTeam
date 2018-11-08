package com.example.user.treasurehunter;

import android.graphics.Color;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinClassTreasure extends PinDS
{
    private final String pinName = "Treasure Pin";

    /**
     * Method that gets the Pin's name.
     * @return The pin Name.
     */
    @Override
    public String getPinName()
    {
        return pinName;
    }

    /**
     * Method that sets a Static color to Treasure Pins.
     */
    public PinClassTreasure()
    {
        setColor("Gold");
        setDefaultColor(0xffffbf00);
    }
    /**
     * Method that gets the Default color.
     * @return The default color.
     */
    @Override
    public int getDefaultColor()
    {
        return defaultColor;
    }
}
