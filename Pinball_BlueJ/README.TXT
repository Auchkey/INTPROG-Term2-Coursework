
PROJECT TITLE: Pinball Coursework
PURPOSE OF PROJECT: INTPROG Coursework
VERSION or DATE: 24/03 Release Candidate 3 (Final)
AUTHORS: 819474
USER INSTRUCTIONS: Right click on the "Demo" class, select "new Demo()" and press "ok". Then right
                   click on the red "demo1: Demo" box in the corner and select "void sampleDemo()".





----Changelog (reverse-chronological)----

6/03

-Added right wall collision.


10/03 

-Added top and bottom wall collision.


13/03 

-Adjusted bottom wall collision, now rebounding properly for inner edges of the gap.

-Placed wall collision detection into its own method pinball.wallCollision(). Adjusted pinball.move()
 to accommodate this.


16/03

-Added an ArrayList to PinballObject class which stores the current pinball object.

-Adjusted PinballObject constructor to place the object in the above ArrayList when created.

-Added object collision checking method to PinballObject class.


17/03

-Moved the ArrayList which stored pinball objects to the machine class.

-Moved object collision detection to the machine class.

-Adjusted collision detection so that a collision doesn't occur if distance is 0 (as the object 
 "colliding" with itself).

-Added invertXVelocity() and invertYVelocity() methods to PinballObject which reverse the x and y 
 velocities of an object, respectively. This is a temporary way of making pinballs rebound off each other
 until I implement a more realistic way.

-Adjusted wallCollision() to make use of the above methods.

-Pinball objects that collide now usually have their velocities reversed.

-Fixed a bug which sometimes allowed objects bouncing on the edge of the bottom right wall to 
 ignore the wall.


18/03

-Tidied up the object collision checking code in Machine class which ensured the checked object
 is not the current object.

-Moved static methods and variables to a new class called BallObject, which PinballObject is a subclass of.

-Created PinballTypeA as a subclass of PinballObject

-Forced PinballTypeA to start as coloured red.

-Created PinballTypeB as a subclass of PinballObject.

-Forced PinballTypeB to start as coloured magenta.

-Adjusted the demo to account for the new classes.

19/03

-Added the Bumper class as a subclass of BallObject.

-The machine class now adds bumpers to the BallObject list, draws them when required and checks for 
 collisions involving bumpers and pinballs.

-Adjusted the demo to account for the Bumper class. It now implements 2 bumpers and 3 pinballs of both types.

-Added javadoc text to the new classes and tidied up existing ones.

-Implemented method wallCollisionAction() to class PinballObject to activate specific behaviour for a pinball
 hitting a wall.
 
-Adjusted wallCollision() to account for the above change.

-Added method ChangeColour() which allows an object to change to one of three colours. This allows any pinball
 of type A to now alternate colour when touching a wall.

 
 20/03
 
 -Fixed random number generation for PinballTypeB's speed change.
 
 -Added flashing to PinballTypeB.
 
 21/03
 
 -Implemented holes so that they are now present in the machine.
 
 -Allowed holes to be redrawn at each step, as otherwise pinballs touching them would delete them.
 
 -Adjusted the demo to now include two holes.
 
 -Added basic game over functonality for when a pinball falls through the gap.
 
 23/03
 
 -Attempted to adjust pinballObject's changeDirection() method to work better with objects moving with no x or
  no y velocity, but figured it is better to just ensure the simulation never allows a pinball of 0 speed.
  
 -Added score text field for each pinball.
 
 -Fixed resetting of machine. Demo can now be reset more easily.
 
 -Added hole functionality.
 
 -Changed speed change of pinballs to be more obvious for the demonstration by increasing the range of speeds it
  can increase or decrease by. Also made the amount to change by random.
  
 -Pinballs smaller than holes are now properly removed.
 
 -Finished adding score feature.
 
 
 24/03 
 
 -Added multiple demos to display the functionality of each object.
 -Feature complete.
