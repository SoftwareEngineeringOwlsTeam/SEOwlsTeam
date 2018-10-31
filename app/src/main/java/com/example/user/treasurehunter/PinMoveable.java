package com.example.user.treasurehunter;

public abstract class PinMoveable extends PinDS
{
    private double degree;
    private double speed;


    public double getDegree()
    {
        return degree;
    }

    public void setDegree(double degree)
    {
        this.degree = degree;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
}
