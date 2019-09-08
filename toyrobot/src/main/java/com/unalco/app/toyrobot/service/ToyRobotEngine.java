package com.unalco.app.toyrobot.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unalco.app.toyrobot.data.CommandProvider;
import com.unalco.app.toyrobot.model.CommandType;
import com.unalco.app.toyrobot.model.Facing;
import com.unalco.app.toyrobot.model.Position;
import com.unalco.app.toyrobot.model.ToyRobot;

 
/**
 * @author yasinunal
 * This class represents the engine of the robot.
 * It has command provider object to retrieve all the commands from a file.
 * Commands are generated from file via CommandProvider object.
 */
public class ToyRobotEngine {

	private CommandProvider commandProvider;
	private ToyRobot myRobot;

	public ToyRobotEngine(CommandProvider commandProvider, ToyRobot myRobot) {
		super();
		this.commandProvider = commandProvider;
		this.myRobot = myRobot;
	}
	
	public ToyRobot getMyRobot() {
		return myRobot;
	}

	// Executes all commands which is provided by command provider
	public void executeAllCommands() {
		
		// Commands are stored in a list.
		List<String> commandList = commandProvider.generate();
		
		// If the command is PLACE command, then other commands can be considered in later executions.
		// This boolean flags whether at least one PLACE command is exist or not.
		boolean isPlaceCommandFound = false;
		
		// Loops for all commands in command list.
		for (int i = 0; i < commandList.size(); i++) {
			
			String command = commandList.get(i);
			
			if(command.contains(CommandType.PLACE.name())) {
				
				System.out.println(command);
				
				// Place command will be executed, mark the flag.
				isPlaceCommandFound = true;
	
				// Parse x,y coordinates and facing to place the robot in table
				String regex = "(\\d+)";
				
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(command);
				// End of regex parse. 
	
				// X-coordinate for position of robot.
				String x = "0";
				
				// Y-coordinate for position of robot.
				String y = "0";
				
				
				int count = 0;
				
				while (matcher.find()) {
					// Assume that there are at least 2 numeric values following PLACE command.
					if (count >= 2) break;
					
					// If place command includes more than 2 coordinate values, then first 2 values will be accepted.
					if (count == 0) x = matcher.group(0);
					else y = matcher.group(0);
					
					count++;					    
				}
				
				// According to PLACE command, set the initial position of robot.
				if (command.contains(Facing.EAST.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.EAST));
				else if (command.contains(Facing.SOUTH.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.SOUTH));
				else if (command.contains(Facing.WEST.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.WEST));
				else
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.NORTH));
				// Initial position is set.
				
				continue;
			}
			
			// If at least 1 place command is executed, then engine can execute other commands.
			if(isPlaceCommandFound) {
				
				// Executes MOVE command.
				if (command.contains(CommandType.MOVE.name())){					
					myRobot.move();
					System.out.println(command);
				}
				// Executes LEFT command
				else if (command.contains(CommandType.LEFT.name())){
					myRobot.rotate(CommandType.LEFT);
					System.out.println(command);
				}
				// Executes RIGHT command
				else if (command.contains(CommandType.RIGHT.name())){
					myRobot.rotate(CommandType.RIGHT);
					System.out.println(command);
				}
				else { // Executes REPORT command
					System.out.println(myRobot.report());
				}
			}
			else {
				// Ignoring the other commands if any PLACE command isn't presented yet.
				continue;
			}
		}

	}

	
}
