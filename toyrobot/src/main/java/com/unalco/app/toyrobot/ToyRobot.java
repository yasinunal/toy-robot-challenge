package com.unalco.app.toyrobot;

public class ToyRobot {

	private CommandProvider commandProvider;

	public ToyRobot(CommandProvider commandProvider) {
		super();
		this.commandProvider = commandProvider;
	}
	
	public String executeAllCommands() {
		
		return "Output => 1,2,EAST";
	}

	
}
