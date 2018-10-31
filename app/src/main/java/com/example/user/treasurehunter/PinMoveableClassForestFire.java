package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinMoveableClassForestFire extends PinMoveable {
    private final String pinName = "Forest Fire Pin";

    @Override
    public String getPinName() {
        return pinName;
    }

    public PinMoveableClassForestFire()
    {
        setColor("Orange");
        setDefaultColor("#FF8C00");
    }
}
