package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinMoveableClassSurvivor extends PinMoveable
{
    private final String pinName = "Survivor Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinMoveableClassSurvivor()
    {
        setColor("Red");
        setDefaultColor("#FF0000");
    }
}
