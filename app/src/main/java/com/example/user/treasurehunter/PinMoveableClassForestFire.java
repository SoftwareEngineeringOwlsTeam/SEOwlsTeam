package com.example.user.treasurehunter;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinMoveableClassForestFire extends PinMoveable
{
    private final String pinName = "Forest Fire Pin";

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
     * Method that sets a Static color to Forest Fire Pins.
     */
    public PinMoveableClassForestFire()
    {
        setColor("Orange");
        setDefaultColor(0xffFF8C00);
        setPinNameHint("Forest Fire");
        setDescriptionHint("There is a fire here.");
        setRadiusHint("0");
        setDegreeHint("0");
        setSpeedHint("0");
    }
}
