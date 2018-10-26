package com.example.user.treasurehunter;

public class PinMoveableClassCustom extends PinMoveable {
    private final String pinName = "Custom Pin";

    @Override
    public String getPinName() {
        return pinName;
    }
}
