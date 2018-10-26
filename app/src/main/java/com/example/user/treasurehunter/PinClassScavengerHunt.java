package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinClassScavengerHunt extends PinDS
{
    private final String pinName = "Scavenger Hunt Pin";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    public PinClassScavengerHunt()
    {
        setColor("Purple");
        setDefaultColor("#A020F0");
    }
}
