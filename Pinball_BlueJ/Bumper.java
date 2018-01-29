import java.awt.*;
import java.util.*;

/**
 * Bumpers are a subclass of the BallObject class, inheriting the position, radius and machine parameters 
 * from the BallObject constructor.
 * 
 * Pinballs that touch a bumper are deflected at the same angle which they came from. Their score is also
 * increased by 2 points.
 * 
 * @author 819474
 */
public class Bumper extends BallObject
{
    /**
     * Constructor for objects of class Bumper
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public Bumper(int xPos, int yPos, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, objectRadius, theMachine);
        colour = Color.GRAY;
        machine.addObj(this);
        machine.draw(this);
    }
}
