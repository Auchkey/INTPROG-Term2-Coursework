import java.awt.*;
import java.util.*;

/**
 * Holes are a subclass of the BallObject class, inheriting the position, radius and machine parameters 
 * from the BallObject constructor.
 * 
 * Holes are black, static objects which other objects do not collide with. Instead, when they go 
 * through/over the hole, their score is reset to 0. 
 * 
 * Objects with a smaller radius than the hole it touches are removed from the machine.
 * 
 * @author 819474
 */
public class Hole extends BallObject
{
    /**
     * Constructor for objects of class Hole
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theMachine  the machine this object is in
     */
    public Hole(int xPos, int yPos, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, objectRadius, theMachine);
        colour = Color.BLACK;
        machine.addObj(this);
        machine.draw(this);
    }
}