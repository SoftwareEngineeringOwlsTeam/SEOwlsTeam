package com.example.user.treasurehunter;

import android.graphics.Color;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinMoveableClassCustom extends PinMoveable {
    private final String pinName = "Custom Pin";

    /**
     * Method that gets the Pin's name.
     * @return The pin Name.
     */
    @Override
    public String getPinName() {
        return pinName;
    }

    /**
     * Method that sets a Static color to Custom Pins.
     */
    public PinMoveableClassCustom()
    {
        setColor("Grey");
        setDefaultColor(0xff696969);
        setPinNameHint("Custom Pin");
        setDescriptionHint("This is a custom pin.");
        setRadiusHint("0");
        setDegreeHint("0");
        setSpeedHint("0");
    }
}
