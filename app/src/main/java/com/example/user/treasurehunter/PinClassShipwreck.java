package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinClassShipwreck extends PinDS
{
    private final String pinName = "Shipwreck Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinClassShipwreck()
    {
        setColor("Blue");
        setDefaultColor("#0000CC");
    }
}
