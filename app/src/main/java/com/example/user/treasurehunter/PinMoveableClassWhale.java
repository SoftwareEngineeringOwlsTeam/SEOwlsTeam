package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinMoveableClassWhale extends PinMoveable
{
    private final String pinName = "Whale Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinMoveableClassWhale()
    {
        setColor("Navy Blue");
        setDefaultColor("#3A4384");
    }
}
