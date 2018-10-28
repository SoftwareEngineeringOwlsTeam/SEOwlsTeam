package com.example.user.treasurehunter;

import android.graphics.Color;

/**
 *
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public class PinClassShipwreck extends PinDS
{
    private final String pinName = "Shipwreck Pin";

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
     * Method that sets a Static color to Shipwreck Pins.
     */
    public PinClassShipwreck()
    {
        setColor("Blue");
        setDefaultColor("#0000CC");
    }
}
