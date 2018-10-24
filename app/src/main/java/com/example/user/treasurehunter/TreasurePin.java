package com.example.user.treasurehunter;

import android.graphics.Color;

public class TreasurePin extends PinDS
{
    private final String pinName = "Treasure Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    TreasurePin()
    {
        color = "Gold";

        Color colorr = new Color();
        defaultColor = colorr.parseColor("#ffbf00");
    }

    @Override
    public int getDefaultColor()
    {
        return defaultColor;
    }
}
