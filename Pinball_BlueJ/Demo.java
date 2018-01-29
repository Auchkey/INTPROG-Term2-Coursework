import java.awt.*;

/**
 * Class to demonstrate functionality of the Pinball machine
 * 
 * @author 819474
 * 
 */
public class Demo
{
    private Machine machine;

    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
        machine = new Machine(); // machine object
    }

    /**
     * Sample demo method - Creates pinballs, holes and bumpers and runs until 
     * one falls through the gap.
     */
    public void defaultDemo()
    {
        // default demo
        machine.resetMachine();
        
        // type a pinballs
        PinballTypeA PA1 = new PinballTypeA(200, 240, 4, 6, 20, machine);
        PinballTypeA PA2 = new PinballTypeA(300, 280, 2, 4, 30, machine);
        PinballTypeA PA3 = new PinballTypeA(450, 50, 2, 1, 15, machine);
        
        // type b pinballs
        PinballTypeB PB1 = new PinballTypeB(100, 400, -6, 4, 30, machine);
        PinballTypeB PB2 = new PinballTypeB(200, 100, -2, -3, 25, machine);
        PinballTypeB PB3 = new PinballTypeB(40, 120, -3, -3, 15, machine);
        
        // bumpers
        Bumper B1 = new Bumper(170, 200, 20, machine);
        Bumper B2 = new Bumper(350, 325, 20, machine);
        
        // holes
        Hole H1 = new Hole(450, 100, 15, machine);
        Hole H2 = new Hole(120, 400, 15, machine);
        
        while(machine.getIsSimulationRunning())
        {
            machine.pauseMachine();           // small delay
            
            PA1.move();
            PA2.move();
            PA3.move();
            
            PB1.move();
            PB2.move();
            PB3.move();

            machine.draw(B1);
            machine.draw(B2);
            
            machine.draw(H1);
            machine.draw(H2);
            
        } 
        
        machine.displayScore();
    }
    
    /**
     * Demonstrates the functionality of type a pinballs.
     */
    public void pinBallADemo()
    {
        // default demo
        machine.resetMachine();
        
        // type a pinballs
        PinballTypeA PA1 = new PinballTypeA(200, 240, 4, 6, 20, machine);
        PinballTypeA PA2 = new PinballTypeA(300, 280, 2, 4, 30, machine);
        PinballTypeA PA3 = new PinballTypeA(450, 50, 2, 1, 25, machine);
        PinballTypeA PA4 = new PinballTypeA(150, 60, -2, -6, 35, machine);
        
        while(machine.getIsSimulationRunning())
        {
            machine.pauseMachine();           // small delay
            
            PA1.move();
            PA2.move();
            PA3.move();
            PA4.move();
        } 
        
        machine.displayScore();
    }
    
    /**
     * Demonstrates the functionality of type b pinballs.
     */
    public void pinBallBDemo()
    {
        // default demo
        machine.resetMachine();
        
        // type b pinballs
        PinballTypeB PB1 = new PinballTypeB(200, 240, 4, 6, 20, machine);
        PinballTypeB PB2 = new PinballTypeB(300, 280, 2, 4, 30, machine);
        PinballTypeB PB3 = new PinballTypeB(450, 50, 2, 1, 25, machine);
        PinballTypeB PB4 = new PinballTypeB(150, 60, -2, -6, 35, machine);
        
        while(machine.getIsSimulationRunning())
        {
            machine.pauseMachine();           // small delay
            
            PB1.move();
            PB2.move();
            PB3.move();
            PB4.move();
        } 
        
        machine.displayScore();
    }
    
    /**
     * Demonstrates the functionality of bumpers.
     */
    public void bumperDemo()
    {
        // default demo
        machine.resetMachine();
        
        // type b pinballs
        PinballTypeB PB1 = new PinballTypeB(70, 200, 5, 0, 20, machine);
        PinballTypeB PB2 = new PinballTypeB(70, 100, 3, 3, 30, machine);
        PinballTypeB PB3 = new PinballTypeB(370, 50, 0, 7, 15, machine);
        
        // bumpers
        Bumper B1 = new Bumper(170, 200, 20, machine);
        Bumper B2 = new Bumper(370, 325, 20, machine);
        
        while(machine.getIsSimulationRunning())
        {
            machine.pauseMachine();           // small delay
            
            PB1.move();
            PB2.move();
            PB3.move();
            
            machine.draw(B1);
            machine.draw(B2);
        } 
        
        machine.displayScore();
    }
    
    /**
     * Demonstrates the functionality of holes.
     */
    public void holeDemo()
    {
        // default demo
        machine.resetMachine();
        
        // type b pinballs
        PinballTypeB PB1 = new PinballTypeB(275, 40, 0, 3, 20, machine);
        PinballTypeB PB2 = new PinballTypeB(70, 100, -6, -6, 30, machine);
        PinballTypeB PB3 = new PinballTypeB(370, 50, 0, -4, 15, machine);
        
        // holes
        Hole H1 = new Hole(170, 200, 15, machine);
        Hole H2 = new Hole(370, 325, 15, machine);
        
        while(machine.getIsSimulationRunning())
        {
            machine.pauseMachine(); // small delay
            
            PB1.move();
            PB2.move();
            PB3.move();
            
            machine.draw(H1);
            machine.draw(H2);
        } 
        
        machine.displayScore();
    }
}
