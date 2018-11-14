package com.example.user.treasurehunter;

/**
 * The PinMoveablePin extends PinDS. It had addition characteristics that differ from pins that do not move.
 * @author Zach Curll, Matthew Finnegan, Alexander Kulpin, Dominic Marandino, Brandon Ostasewski, Paul Sigloch
 * @version Sprint 2
 */
public abstract class PinMoveable extends PinDS
{
    private double degree;
    private double speed;
    protected String degreeHint;
    protected String speedHint;

    /**
     * Method that gets the Degree.
     * @return The Degree.
     */
    public double getDegree()
    {
        return degree;
    }

    /**
     * Method that sets the Degree.
     * @param degree Include the degree to set.
     */
    public void setDegree(double degree)
    {
        this.degree = degree;
    }

    /**
     * Method that gets the Speed.
     * @return The Speed.
     */
    public double getSpeed()
    {
        return speed;
    }

    /**
     * Method that sets the Speed.
     * @param speed Include the speed to set.
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public String getDegreeHint()
    {
        return degreeHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void setDegreeHint(String degreeHint)
    {
        this.degreeHint = degreeHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public String getSpeedHint()
    {
        return speedHint;
    }

    // INCLUDE DOCUMENTATION*****************************************************
    public void setSpeedHint(String speedHint)
    {
        this.speedHint = speedHint;
    }
}
