# TankGame!
Welcome to TankGame! TankGame is a multiplayer last-tank-standing 
game where each player codes their own tank using the provided classes.

## How To Play
Create a new class in the Tank Package and name it something unique.
Copy the code from `EmptyTank.java` into your new tank class.
Then fill out the code in your tank class where necessary to create your 
tank. In `Main.java`, add your tank to the list of tanks. You must 
provide a starting location and angle for your tank. Run the Main 
class with at least 2 tanks in the list and watch the tanks battle it out!

## Directions
Within the TankGame, you can think of directions as -x, x, -y, and y. In the 
game visual, -x points to the left and x points to the right. -y points up and 
y points down. The unnatural inversion of -y and y is due to how JFrame in java
 handles coordinates.

## Angles
To steer, one must set headings for their tank. This is done with the 
`setNewHeading()` method within `ITank.java`. Headings are handled by `Angle.java` 
and can range from 0.0 up to but not including 360.0 degrees. To stay consistent with 
the coordinate system, 0.0 degrees points to the right, 90.0 degrees points down, 180.0 
degrees points left, and 270.0 degrees points up. Angle values are doubles.

## runTime()
The `runTime()` method is the method that you will have to implement. This method will 
be called once every game-tick. Within this method, you should control when and where your 
tank shoots, drives, and turns. This method accepts as parameters a list of all tanks 
currently alive and a set of all active bullets. This will allow you to access other 
tanks' positions, heading, and movement as well as all bullets' positions and headings.
Never assume there are tanks or bullets within the parameters. This can lead to trying
to access tanks or bullets that are not there. Additionally, the list of tanks will include
your tank. Be sure to check whether a tank in the list is yours or not depending on what
you are trying to do.

You can create other methods within your tank class but only your runTime method will be
called by the game. You should use the methods defined in `ITank.java` to help control
your tank.
## Usable ITank Methods
### For All Tanks
#### getHealth()
This method returns the current health of a tank. Each tank starts with 5 health points and
loses 1 every time it is shot.
#### getPosition()
This method returns an array size 2 of doubles containing the position of a tank. 
`double xPosition = getPosition()[0]`. `double yPosition = getPosition()[1]`.
#### getCurrentHeading()
This method returns a double that describes the direction a tank is currently facing in degrees.
#### getCurrentTurretHeading()
This method returns a double that describes the direction a tank's turret is currently facing in degrees.
#### howMoving()
This method returns a certain value of the driveDirection enum in `ITank.java`. Either Forward, Backward,
or None.
### For Your Tank Only
#### moveForward()
This method makes your tank start moving forward.
#### moveBackward()
This method makes your tank start moving backward. Note that your tank will move in the opposite direction of
your tank's current heading.
#### stopTank()
This method makes your tank stop moving.
#### setNewHeading()
This method accepts a double as a parameter and sets the heading which your tank will begin
turning towards. Note that the game will automatically take the shortest rotation arc length
to the desired heading and your tank will rotate regardless of whether it is moving.
#### setNewTurretHeading()
This method accepts a double as a parameter and sets the heading which your tank's turret will begin
turning towards. Note that the game will automatically take the shortest rotation arc length
to the desired heading.
#### fireBullet()
This method will fire a bullet from your tank's position at the current heading of your tank's turret.
This method must be passed the Set of bullets that was passed into the `runTime()` method.
## Usable Bullet Methods
#### getPosition()
This method returns an array size 2 of doubles containing the position of a bullet.
#### getHeading()
This method returns a double describing the direction/angle the bullet is traveling.
#### getBulletId()
This method returns an int which represents the bullet's unique id.
## Usable Angle Methods
#### getValue()
This method returns the value of an Angle in degrees as a double.
#### compare()
This method returns a boolean describing whether one Angle's value is the same as another.
## Constants
Constants are values that will remain constant during the game. Please note that these values
can be set differently by users before the game. It is best practice access these values using
the variable names rather than the actual number value. All constants are static members.
### GAMEBOARD_WIDTH
The width of the playing field. Defined in `Game.java`.
### GAMEBOARD_HEIGHT
The height of the playing field. Defined in `Game.java`.
### TICK_LENGTH_MILLI
The number of milliseconds between each game-tick. Defined in `Game.java`.
### TANK_WIDTH
The width of each tank. Defined in `ITank.java`.
### TANK_HEIGHT
The height of each tank. Defined in `ITank.java`.
### TANK_SPEED
The speed of each tank. Defined in `ITank.java`.
### TURRET_COOLDOWN_MILLI
The cooldown in milliseconds required after firing your turret before you can fire again.
Defined in `ITank.java`.
### BULLET_SPEED
The speed of each bullet. Defined in `Bullet.java`.
