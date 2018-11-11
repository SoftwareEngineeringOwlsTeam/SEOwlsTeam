package com.example.user.treasurehunter;

import android.graphics.Color;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinMoveableClassHunting extends PinMoveable
{
    private final String pinName = "Hunting Pin";

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
     * Method that sets a Static color to Hunting Pins.
     */
    public PinMoveableClassHunting()
    {
        setColor("Green");
        setDefaultColor(0xff228B22);
        setPinNameHint("Hunting Pin");
        setDescriptionHint("Game was spotted here.");
        setRadiusHint("0");
        setDegreeHint("0");
        setSpeedHint("0");
    }
}
