package com.example.user.treasurehunter;

public class WhalePin extends MoveablePin
{
    private final String pinName = "WHALEPIN";
    private final String color = "Blue";

    @Override
    public String getPinName()
    {
        return pinName;
    }

    @Override
    public String getColor()
    {
        return color;
    }


}
