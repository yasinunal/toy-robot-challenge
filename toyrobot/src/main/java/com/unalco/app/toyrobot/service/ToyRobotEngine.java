package com.unalco.app.toyrobot.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unalco.app.data.CommandProvider;
import com.unalco.app.model.Facing;
import com.unalco.app.model.Position;
import com.unalco.app.model.ToyRobot;
import com.unalco.app.model.CommandType;


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
		
		List<String> commandList = commandProvider.generate();
		
		boolean isPlaceCommandFound = false;
		
		for (int i = 0; i < commandList.size(); i++) {
			
			String command = commandList.get(i);
			
			if(command.contains(CommandType.PLACE.name())) {
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
				}
				else if (command.contains(CommandType.LEFT.name())){
					myRobot.rotate(CommandType.LEFT);
				}
				else if (command.contains(CommandType.RIGHT.name())){
					myRobot.rotate(CommandType.RIGHT);
				}
				else { // REPORT command
					myRobot.report();
				}
			}
			else {
				// Ignoring the other commands if any PLACE command isn't presented yet.
				continue;
			}
		}

	}

	
}
