package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinClassTreasure extends PinDS
{
    private final String pinName = "Treasure Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinClassTreasure()
    {
        setColor("Gold");
        setDefaultColor("#ffbf00");
    }

    @Override
    public int getDefaultColor()
    {
        return defaultColor;
    }
}
