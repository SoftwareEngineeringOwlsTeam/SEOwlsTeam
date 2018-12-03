package com.example.user.treasurehunter;

/**
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinClassScavengerHunt extends PinDS
{
    private final String pinName = "Scavenger Hunt Pin";

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
     * Method that sets a Static color to ScavengerHunt Pins.
     */
    public PinClassScavengerHunt()
    {
        setColor("Purple");
        setDefaultColor(0xffA020F0);
        setPinNameHint("Scavenger Hunt");
        setDescriptionHint("This pin is part of a scavenger hunt.");
        setRadiusHint("0");
    }
}
