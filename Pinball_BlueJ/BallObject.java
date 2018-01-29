import java.awt.*;
import java.util.*;

/**
 *  This is the superclass for all objects inside the pinball machine. Pinballs, bumpers and holes all 
 *  derive from this class.
 *  
 *  It holds the static values of the subclass object (such as position, radius, score) and 
 *  returns these values using the appropriate "get" methods.
 *  
 *  It also determines if an object is removed from the machine (such as a pinball falling into a hole).
 *  
 * @author 819474 
 * 
 */
public class BallObject
{
    protected int currentXLocation;
    protected int currentYLocation;
    protected Color colour;
    protected int radius;
    protected Machine machine;
    protected boolean isFlashing; // determines if pinball is flashing.
    protected boolean isRemoved; // determines if object is removed from the machine.
    protected Random random = new Random(); // random generator for numbers, booleans etc.
    protected Color storedColour; // stores the current colour of the pinball (for changing colours).
    protected int score;

    /**
     * Constructor for objects of class BallObject
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    
    public BallObject(int xPos, int yPos, int objectRadius, Machine theMachine)
    {
        currentXLocation = xPos;
        currentYLocation = yPos;
        radius = objectRadius;
        machine = theMachine;
        isFlashing = false; // object starts off not flashing.
        isRemoved = false; // object is not initially removed.
    }
    
    /**
     * return the horizontal position of this object
     */
    public int getXPosition()
    {
        return currentXLocation;
    }
    
    /**
     * return the vertical position of this object
     */
    public int getYPosition()
    {
        return currentYLocation;
    }
    
    /**
     * return the radius of this object
     */
    public int getRadius()
    {
        return radius;
    }
    
    /**
     * return the diameter of this object
     */
    public int getDiameter()
    {
        return 2*radius;
    }
    
    /**
     * return the colour of this object
     */
    public Color getColor()
    {
        return colour;
    }
    
    /**
     * flag the object as removed from the machine and place it under the hole it fell through
     * 
     * @param holeXPos  the x coordinate of the hole to be placed under
     * @param holeYPos  the y coordinate of the hole to be placed under
     */
    public void removeObject(int holeXPos, int holeYPos)
    {
        isRemoved = true;
        currentXLocation = holeXPos;
        currentYLocation = holeYPos;
        score = 0;
    }
    
    /**
     * check if object has been removed from the machine
     */
    public boolean checkIfRemoved()
    {
        return isRemoved;
    }
}