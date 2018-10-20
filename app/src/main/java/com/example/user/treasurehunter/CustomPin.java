package com.example.user.treasurehunter;

public class CustomPin extends MoveablePin {
    private final String pinName = "CUSTOMPIN";

    @Override
    public String getPinName() {
        return pinName;
    }
}
