import java.awt.*;
import java.util.*;

/**
 * A subclass of BallObject, it is an object that exists in the pinball machine. The object is 
 * capable of moving around in the machine and bounces off the walls.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * When an object is created, it is added to the object list in the Machine class. This allows it to
 * check for collisions with other pinballs and bounce off them when doing so.
 * 
 * The class also determines the behaviour of the pinball in collisions depending on its type.
 * 
 * @author 819474 
 * 
 */

public class PinballObject extends BallObject
{
    
    private int speedXTravel;
    private int speedYTravel;
    private final int leftWallPosition;
    private final int rightWallPosition;
    private final int topWallPosition;
    private final int bottomWallPosition;
    private final int lengthToGap;
    private int speedLimit; // the maximum x and y velocity a pinball can go
    

    /**
     * Constructor for objects of class PinballObject
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public PinballObject(int xPos, int yPos, int xVel, int yVel, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, objectRadius, theMachine);
        speedXTravel = xVel;
        speedYTravel = yVel;
        leftWallPosition = machine.getLeftWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTopWall();
        bottomWallPosition = machine.getBottomWall();
        lengthToGap = machine.getlengthToGap();
        machine.addObj(this); // add to list of objects in the machine
        speedLimit = 7;
        score = 0;
    }

    /**
     * Moves this object according to its position and speed and redraw.
     * Performs collision detection at each step.
     **/
    public void move()
    {
        // remove from universe at the current position
        machine.erase(this); //this = current object
        if (!isRemoved)
        {

            // compute new position
            currentYLocation += speedYTravel;
            currentXLocation += speedXTravel;
            
            // collision checking
            wallCollision();
            machine.objectCollisionCheck(this);
            
            // speed limiter
            // x Speed
            if (speedXTravel > speedLimit)
            {
                speedXTravel = speedLimit;
            }
            else if (speedXTravel < -speedLimit)
            {
                speedXTravel = -speedLimit;
            }
            // y Speed
            if (speedYTravel > speedLimit)
            {
                speedYTravel = speedLimit;
            }
            else if (speedYTravel < -speedLimit)
            {
                speedYTravel = -speedLimit;
            }
            
            
            // draw again at new position
            machine.draw(this);
        }
    }
    
    /**
     * Check if object has hit a wall - rebound and activate appropriate pinball behaviour if so.
     */
    
    public void wallCollision()
    {
        // if hit left wall
        if(currentXLocation <= (leftWallPosition + radius)) 
        {
            currentXLocation = leftWallPosition + radius;
            invertXVelocity();
            wallCollisionAction();
        }
        // if hit right wall
        else if(currentXLocation >= (rightWallPosition - radius))
            {
              currentXLocation = rightWallPosition - radius;
              invertXVelocity(); 
              wallCollisionAction();
            }
        // if hit top wall
        else if(currentYLocation <= (topWallPosition + radius)) 
            {
                currentYLocation = topWallPosition + radius;
                invertYVelocity();
                wallCollisionAction();
            }
            
        // if hit bottom wall left of gap
        else if((currentYLocation >= (bottomWallPosition - radius)) && (currentXLocation <= lengthToGap))
            {
              currentYLocation = bottomWallPosition - radius;
              invertYVelocity();
              wallCollisionAction();
            }
            
        // if hit bottom wall right of gap
        else if((currentYLocation >= (bottomWallPosition - radius)) && (currentXLocation >= (rightWallPosition - lengthToGap)))
            {
              currentYLocation = bottomWallPosition - radius;
              invertYVelocity();
              wallCollisionAction();
            }
            
        // if hit edge of left bottom wall
        else if((currentYLocation >= (bottomWallPosition - radius)) && (currentXLocation <= (lengthToGap + radius)))
            {
              currentXLocation = lengthToGap + radius;
              invertXVelocity();
              wallCollisionAction();
            }
            
        // if hit edge of right bottom wall
        else if((currentYLocation >= (bottomWallPosition - radius)) && (currentXLocation >= (rightWallPosition - lengthToGap - radius)))
            {
              currentXLocation = ((rightWallPosition - lengthToGap) - radius);
              invertXVelocity();
              wallCollisionAction();
            }
            
        // if it goes through the gap
        else if(currentYLocation >= (bottomWallPosition + radius))
            {
                machine.stopSimulation();
            }
    } 
    
    /**
     * Defines what behaviour to activate when hitting a wall dependent on pinball type.
     */
    public void wallCollisionAction()
    {
        score += 1;
        if (this instanceof PinballTypeA)
        {
            ChangeColour();
        }
        
        else if (this instanceof PinballTypeB)
        {
            isFlashing = !isFlashing; // flashing toggle
            flashColour();
        }
    }
    
    /**
     * Randomly changes colour of the pinball, but always to a different colour than before.
     */
    public void ChangeColour()
    {
        Color currentColour = this.getColor();
            while (currentColour == this.getColor()) // while colour is still the same
            {
                int randomColour = random.nextInt(3) + 1; // random number between 1 and 3 inclusive
                if (randomColour == 1)
                {
                    colour = Color.RED;
                }
                else if (randomColour == 2)
                {
                    colour = Color.BLUE;
                }
                else
                {
                    colour = Color.GREEN;
                }
            }
    }
    
    /**
     * Activates pinball colour flashing if not already flashing, or deactivates it if it already is.
     */
    public void flashColour()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask()
        {
            @Override // overrides run() method in timer with flashing implementation below
            public void run()
            {
                if (isFlashing)
                {
                    if (colour == Color.WHITE)
                    {
                       colour = storedColour; 
                    }
                    else
                    {
                        colour = Color.WHITE;
                    }
                }
                else
                {
                    colour = storedColour;
                }
            }
        }, 0, 100); // 100ms
    }
    
    /**
     * Defines what behaviour to activate when a pinball hits another, depending on pinball type.
     */
    public void pinballCollisionAction()
    {
        score += 5;
        if (this instanceof PinballTypeA)
        {
            changeDirection();
        }
        
        else if (this instanceof PinballTypeB)
        {
            invertXVelocity();
            invertYVelocity();
            changeSpeed();
        }
    }
    
    /**
     * Changes the angle at which the pinball is deflected in a collision with another pinball.
     */
    public void changeDirection()
    {
        if (random.nextBoolean())
        {
            invertXVelocity();
        }
        else
        {
            invertYVelocity();
        }
    }
    
    /**
     * increase or decrease x and y speeds of the pinball, between a minimum and maximum
     */
    public void changeSpeed()
    {
        // if positive x speed, randomly increase or decrease to another positive value
        if (speedXTravel >= 1) 
        {
            speedXTravel += getRandomSpeed(6);
            // if becomes negative or zero, set to 1
            if (speedXTravel <= 0)
            {
                speedXTravel = 1;
            }
        }
        // if negative x speed, randomly increase or decrease to another negative value
        else {
            speedXTravel += getRandomSpeed(6);
            // if becomes positive or zero, set to -1
            if (speedXTravel >= 0)
            {
                speedXTravel = -1;
            }
        }
       
        // if positive y speed, randomly increase or decrease to another positive value
         if (speedYTravel >= 1) 
        {
            speedYTravel += getRandomSpeed(6);
            // if becomes negative or zero, set to 1
            if (speedYTravel <= 0)
            {
                speedYTravel = 1;
            }
        }
        // if negative y speed, randomly increase or decrease to another negative value
        else {
            speedYTravel += getRandomSpeed(6);
            // if becomes positive or zero, set to -1
            if (speedYTravel >= 0)
            {
                speedYTravel = -1;
            }
        }
    }
    
    /**
     * randomly increases or decreases the speed of an object
     */
    public int getRandomSpeed(int rangeToChangeBy)
    {
        int randomSpeed;
        if(random.nextBoolean())
            {
                randomSpeed = random.nextInt(rangeToChangeBy); // random increase
            }
            else
            {
                randomSpeed = -(random.nextInt(rangeToChangeBy)); // random decrease
            }
        return randomSpeed;
    }
    
    /**
     * Defines behaviour of the object upon colliding with a bumper.
     */
    public void bumperCollisionAction()
    {
        invertXVelocity();
        invertYVelocity();
        score += 2;
    }
    
    /**
     * Resets the score of the current object to 0.
     */
    public void resetScore()
    {
        score = 0;
    }
    
    /**
     * Reverses the X velocity of this object
     */
    public void invertXVelocity()
    {
        speedXTravel = -speedXTravel; 
    }
    
    /**
    * Reverses the Y velocity of this object
    */
    public void invertYVelocity()
    {
        speedYTravel = -speedYTravel; 
    }
   
    /**
     * return X Velocity of this object
     */
    public int getXVelocity()
    {
        return speedXTravel;
    }
    
    /**
     * return Y Velocity of this object
     */
    public int getYVelocity()
    {
        return speedYTravel;
    }
    
    /**
     * return the score of this object
     */
    public int getScore()
    {
        return score;
    }
}
