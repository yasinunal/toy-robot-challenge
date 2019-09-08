# Toy Robot Application

This application controls a toy robot on a table top, a grid of 5 x 5 units, there are no obstructions. 
Robot is initialized on a place on table with a PLACE command then roams arounda the table top with other commands.
Any movement that would result in the robot falling from the table is prevented, 
however further valid movement commands is still allowed.

## Prerequisities & Libraries

Eclipse IDE

Java 1.8

Maven 3.6.0

JUnit 5

Mockito 2.28.2

## Usage

- Import the project into Eclipse IDE.
- You have to provide a commands.txt file as a property value in the file src/main/resources/application.properties. 
- commands.txt should have only one command on its each line.

## Sample for commands.txt
place 0,0,NORTH

move

report

place 0, 0, NORTH

left

report

place 1,2,EAST

move

move

left

move

report

## History

I wrote a dummy CommandProvider class first.

Then implemented ToyRobotEngine.java and ToyRobotMockTest.java classes

Run the mock tests with Mock data in ToyRobotMockTest.java class.

At last, I implemented CommandProvider.java and ToyRobotApplication.java

    ### src/main/java ###
    com.unalco.app.toyrobot
      - ToyRobotApplication.java (Main Application)

    com.unalco.app.toyrobot.data
      - CommandProvider.java (Reads from the file the commands that robot needs to execute)

    com.unalco.app.toyrobot.model
      - CommandType.java (Enum - Represents all possible command types)
      - Facing.java (Enum - Represents all possible facings for robot)
      - ToyRobot.java (Robot itself :) )
      - Position.java (Represents the position of robot)

    com.unalco.app.toyrobot.service
      - ToyRobotEngine.java ( Engine that executes all the commands from file, and make the robot roam on the table top.)

    ### src/test/java ###
    com.unalco.app.test.toyrobot
      -ToyRobotMockTest.java ( Mock test class for the ToyRobotEngine ) 
      
    ### src/main/resources ###
    application.properties ( Filepath for the file that includes commands should be provided in this properties file )
    toy.robot.commands.file=/home/.../..../commands.txt

## Credits

Developed by Yasin Ünal, 2019
