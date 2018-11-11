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

    public String getDegreeHint() { return degreeHint;  }

    public void setDegreeHint(String degreeHint)
    {
        this.degreeHint = degreeHint;
    }

    public String getSpeedHint() { return speedHint;  }

    public void setSpeedHint(String speedHint)
    {
        this.speedHint = speedHint;
    }

}
