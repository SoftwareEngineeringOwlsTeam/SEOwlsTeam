package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinMoveableClassHunting extends PinMoveable
{
    private final String pinName = "Hunting Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinMoveableClassHunting()
    {
        setColor("Green");
        setDefaultColor("#228B22");
    }
}
