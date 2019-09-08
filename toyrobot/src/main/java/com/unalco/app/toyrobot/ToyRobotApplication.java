package com.unalco.app.toyrobot;

import com.unalco.app.toyrobot.data.CommandProvider;
import com.unalco.app.toyrobot.model.ToyRobot;
import com.unalco.app.toyrobot.service.ToyRobotEngine;

public class ToyRobotApplication {

	public static void main(String[] args) {
		
		CommandProvider commandProvider = new CommandProvider();
		ToyRobot myRobot = new ToyRobot();
		ToyRobotEngine myEngine = new ToyRobotEngine(commandProvider, myRobot);
		myEngine.executeAllCommands();

	}

}
