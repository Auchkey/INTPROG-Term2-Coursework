import java.awt.*;
import java.util.*;


/**
 * A pinball machine. It defines the size of the machine in pixels and draws walls at the edges
 * It also stores a list of all objects currently in the pinball machine, drawing and erasing them when necessary, 
 * and performing collision checking methods on them.
 * 
 * @author 819474 
 * 
 */
public class Machine
{
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    private int bottomEdge;
    private int rightEdge;
    private int lengthToGap;        // the distance between the edge of the machine and the start of the gap
    private int gapWidth = 50;
    private ArrayList <BallObject> objList;
    private boolean isSimulationRunning;
    private int totalScore;

    /**
     * Create a machine with default name and size.
    */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
        objList = new ArrayList<BallObject>();
        totalScore = 0;
    }
    
    /**
     *  Create a machine with given name and size.
     *  
     *  @param name The name to give the machine.
     *  @param rightEdge The maximum x coordinate.
     *  @param bottomEdge The maximum y coordinate.
     */
     public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
    }
    
    /**
     * Adds object to the list of objects in the machine.
     * 
     * @param BallObj  the object to add to the list.
     */
    public void addObj(BallObject ballObj)
    {
        objList.add(ballObj);
    }
    
     /**
     * Check if a pinball collides with object.
     * 
     * @param currentPinball  The current object to check.
     */
    public void objectCollisionCheck(PinballObject currentPinball)
    {
        Iterator<BallObject> iter = objList.iterator();
        while (iter.hasNext()) // step through objects in ball object list
        {
            BallObject otherObject = iter.next();
            if (!(otherObject.checkIfRemoved()))
            {
                if (otherObject != currentPinball)
                {
                   float distance = Pythagoras(currentPinball.getXPosition(), otherObject.getXPosition(),currentPinball.getYPosition(), otherObject.getYPosition());
                   
                   // if hit a non-hole object (+5 distance margin for smoother collisions)
                   if (distance <= (5 + currentPinball.getRadius() + otherObject.getRadius()) && !(otherObject instanceof Hole)) // if less than radii + not a hole
                   {
                        
                        // if collision involved two pinballs
                        if (currentPinball instanceof PinballObject && otherObject instanceof PinballObject) 
                        {
                            currentPinball.pinballCollisionAction();
                        }
                        // if collision involved a bumper
                        else if (otherObject instanceof Bumper)
                        {
                            currentPinball.bumperCollisionAction();
                        }
                   }
                   
                   // if touching a hole
                   else if(otherObject instanceof Hole && distance <= (currentPinball.getRadius() + otherObject.getRadius())) 
                   {
                        currentPinball.resetScore();
                        
                        // if smaller than hole
                        if (currentPinball.getRadius() <= otherObject.getRadius())
                        {
                            erase(currentPinball);
                            //remove pinball
                            currentPinball.removeObject(otherObject.getXPosition(), otherObject.getYPosition());
                        }
                    }
                }
            }
        }
    }
    
    /**
     *  Calculate and return the overall distance between two objects.
     *  
     *  @param xPos1  The x coordinate of the current object.
     *  @param xPos2  The x coordinate of the other object.
     *  @param yPos1  The y coordinate of the current object.
     *  @param yPos2  The y coordinate of the other object.
     */
    public float Pythagoras(int xPos1, int xPos2, int yPos1, int yPos2)
    {
        int xDistance = Math.abs(xPos1 - xPos2);
        int yDistance = Math.abs(yPos1 - yPos2);
        float hypotenuse = (float) Math.sqrt(Math.pow((float) xDistance,2) + Math.pow((float) yDistance,2));
        return hypotenuse;
    }
    
    /**
     * Erase a BallObject from the view of the pinball machine. If its a pinball, erase the score as well.
     * 
     * @param ballObj The ball object to be erased.
     */
    public void erase(BallObject ballObj)
    {
        machine.eraseCircle(ballObj.getXPosition() - ballObj.getRadius(), ballObj.getYPosition()- ballObj.getRadius(), ballObj.getDiameter());
        if (ballObj instanceof PinballObject) // if a pinball, erase the score
        {
            PinballObject pinballObj = (PinballObject) ballObj; // downcasting to PinballObject
            String scoreString = String.valueOf(pinballObj.getScore());
            machine.eraseString(scoreString, (pinballObj.getXPosition()-3), (pinballObj.getYPosition() + 1));
        }
    }
    
    /**
    * Draw a BallObject at its current position onto the view of the pinball machine.
    * 
    * @param ballObj The object to be drawn.
    */
    public void draw(BallObject ballObj)
    {
        machine.setForegroundColor(ballObj.getColor());
        machine.fillCircle(ballObj.getXPosition() - ballObj.getRadius(), ballObj.getYPosition() - ballObj.getRadius(), ballObj.getDiameter());
        if (ballObj instanceof PinballObject) // if a pinball, draw the score
        {
            machine.setForegroundColor(Color.BLACK);
            PinballObject pinballObj = (PinballObject) ballObj; // downcasting to PinballObject
            String scoreString = String.valueOf(pinballObj.getScore());
            machine.drawString(scoreString, (pinballObj.getXPosition() - 3), (pinballObj.getYPosition() + 1));
        }
    }
    
    /**
    * Draw the edge of the pinball machine .
    */
    public void drawMachine()
    {
        machine.setForegroundColor(Color.DARK_GRAY);
        
        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }
    
    /**
    * erase the edge of the pinball machine .
    */
    public void eraseMachine()
    {
        machine.eraseRectangle(0, 0, rightEdge, 10);  // top edge
        machine.eraseRectangle(0, 0, 10, bottomEdge); // left edge
        machine.eraseRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.eraseRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.eraseRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }
        
    /**
     * Return the edge of the left-hand wall.
     */
    public int getLeftWall()
    {
        return leftEdge + 10; //thickness of wall is 10
    }
    
    /**
     * Return the edge of the right-hand wall.
     */
    public int getRightWall()
    {
        return rightEdge - 10;
    }
    
    /**
     * Return the edge of the top wall.
     */
    public int getTopWall()
    {
        return topEdge + 10;
    }
    
    /**
     * Return the edge of the bottom wall.
     */
    public int getBottomWall()
    {
        return bottomEdge - 10;
    }
    
    public int getlengthToGap()
    {
        return lengthToGap;
    }
    
    /**
     * Introduces a small delay in ball movement, for smooth running, and refreshes the machine borders
     * at each delay.
     */
    
    public void pauseMachine()
    {
        machine.wait(50); // 50 milliseconds delay
        drawMachine(); // refresh machine
    }
    
    /**
     * Resets the machine back to initial view, with no ball objects.
     */
    public void resetMachine()
    {
        machine.erase();
        // empty the object list
        if (!objList.isEmpty())
        {
            objList.clear();
        }
        drawMachine();
        isSimulationRunning = true;
    }
    
    /**
     * Returns whether the simulation is still running or not.
     */
    public boolean getIsSimulationRunning()
    {
        return isSimulationRunning;
    }
    
    /**
     * Ends the simulation and calls the score to be displayed.
     */
    public void stopSimulation()
    {
        isSimulationRunning = false;
    }
    
    /**
     * displays the total score of the simulation when it ends.
     */
    public void displayScore()
    {
       for (BallObject ballObj : objList) // step through objects in ball object list
       {
           if (ballObj instanceof PinballObject)
           {
               PinballObject pinballObj = (PinballObject) ballObj;
               totalScore += pinballObj.getScore();
           }
       }
       resetMachine();
       machine.setForegroundColor(Color.BLACK);
       String totalScoreString = String.valueOf(totalScore);
       machine.drawString("A pinball went through the gap.", 210, 200);
       machine.drawString(("Total Score: " + totalScoreString), 250, 250);
    }
}