import java.awt.*;
import java.util.*;

/**
 * Write a description of class PinballTypeB here. They are coloured magenta, which is stored in 
 * the variable storedColour. This allows the pinball to "flash" upon contact with a wall 
 * by effectively changing between its stored colour and the colour white and stop flashing when
 * making contact again. The pinball also randomly changes speed when striking another pinball.
 * 
 * @author 819474
 * 
 */
public final class PinballTypeB extends PinballObject
{
    /**
     * Constructor for objects of class PinballTypeB
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public PinballTypeB(int xPos, int yPos, int xVel, int yVel, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel,  yVel, objectRadius, theMachine);
        colour = Color.MAGENTA;
        storedColour = Color.MAGENTA;
    }
}
