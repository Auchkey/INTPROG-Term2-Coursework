import java.awt.*;
import java.util.*;

/**
 * The class for Type A pinballs. They are coloured initially coloured red, randomly changing between red, 
 * green and blue when striking a wall. When colliding with another pinball, it randomly changes its
 * angle of deflection.
 * 
 * @author 819474 
 * 
 */
public final class PinballTypeA extends PinballObject
{
    /**
     * Constructor for objects of class PinballTypeA
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public PinballTypeA(int xPos, int yPos, int xVel, int yVel, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel,  yVel, objectRadius, theMachine);
        colour = Color.RED;
    }
}
