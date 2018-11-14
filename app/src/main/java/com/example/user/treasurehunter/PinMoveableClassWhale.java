package com.example.user.treasurehunter;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinMoveableClassWhale extends PinMoveable
{
    private final String pinName = "Whale Pin";

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
     * Method that sets a Static color to Whale Pins.
     */
    public PinMoveableClassWhale()
    {
        setColor("Navy Blue");
        setDefaultColor(0xff3A4384);
        setPinNameHint("Scavenger Hunt");
        setDescriptionHint("This pin is part of a scavenger hunt.");
        setRadiusHint("0");
        setDegreeHint("0");
        setSpeedHint("0");
    }
}
