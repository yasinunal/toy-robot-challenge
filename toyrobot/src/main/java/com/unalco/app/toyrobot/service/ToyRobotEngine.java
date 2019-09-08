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

	public void executeAllCommands() {
		
		// Commands are generated from file via CommandProvider object.
		List<String> commandList = commandProvider.generate();
		
		// If the command is PLACE command, then other commands can be considered in later executions.
		// This boolean flags whether at least one PLACE command is exist or not.
		boolean isPlaceCommandFound = false;
		
		for (int i = 0; i < commandList.size(); i++) {
			
			String command = commandList.get(i);
			
			if(command.contains(CommandType.PLACE.name())) {
				
				System.out.println(command);
				
				isPlaceCommandFound = true;
				// Parse x,y coordinates and facing to place the robot in table
				String regex = "(\\d+)";
				
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(command);
				
				String x = "0";
				String y = "0";
				int count = 0;
				
				while (matcher.find()) {
					if (count >= 2) break;
					if (count == 0) x = matcher.group(0);
					else y = matcher.group(0);
					count++;					    
				}
				
				if (command.contains(Facing.EAST.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.EAST));
				else if (command.contains(Facing.SOUTH.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.SOUTH));
				else if (command.contains(Facing.WEST.name()))
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.WEST));
				else
					myRobot.setPosition(new Position(Integer.valueOf(x), Integer.valueOf(y), Facing.NORTH));
				
				continue;
			}
			
			if(isPlaceCommandFound) {
				
				if (command.contains(CommandType.MOVE.name())){					
					myRobot.move();
					System.out.println(command);
				}
				else if (command.contains(CommandType.LEFT.name())){
					myRobot.rotate(CommandType.LEFT);
					System.out.println(command);
				}
				else if (command.contains(CommandType.RIGHT.name())){
					myRobot.rotate(CommandType.RIGHT);
					System.out.println(command);
				}
				else { // REPORT command
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
