package com.example.user.treasurehunter;

import android.graphics.Color;

public class PinMoveableClassCustom extends PinMoveable {
    private final String pinName = "Custom Pin";

    @Override
    public String getPinName() {
        return pinName;
    }

    public PinMoveableClassCustom()
    {
        setColor("Grey");
        setDefaultColor("#696969");
    }
}
