package com.unalco.app.toyrobot.model;

import com.unalco.app.toyrobot.model.CommandType;
import com.unalco.app.toyrobot.model.Facing;

public class ToyRobot {
 
	// This class represents my toy robot
	private Position position;

	public ToyRobot() {
		super();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	// Moves the toy robot one unit forward in the direction it is currently facing.
	public boolean move() {
		
		if(isMovementValid()) {
			
			switch (this.getPosition().getFacing()) {
			
			case NORTH:
				this.getPosition().setY(this.getPosition().getY() + 1);
				break;
				
			case EAST:
				this.getPosition().setX(this.getPosition().getX() + 1);
				break;
				
			case SOUTH:
				this.getPosition().setY(this.getPosition().getY() - 1);
				break;
				
			case WEST:
				this.getPosition().setX(this.getPosition().getX() - 1);
				break;

			default:
				break;
			}
			return true; // Movement done.
		}
		else return false; //Invalid move.
		
		
		
	}
	
	// Rotate the robot 90° clockwise regarding the command type without changing the position of the robot.
	public void rotate(CommandType commandType) {
		
		switch (this.getPosition().getFacing()) {
		
		case NORTH:
			if(commandType.equals(CommandType.LEFT))
				this.getPosition().setFacing(Facing.WEST);
			else
				this.getPosition().setFacing(Facing.EAST);
			break;
			
		case EAST:
			if(commandType.equals(CommandType.LEFT))
				this.getPosition().setFacing(Facing.NORTH);
			else
				this.getPosition().setFacing(Facing.SOUTH);
			break;
			
		case SOUTH:
			if(commandType.equals(CommandType.LEFT))
				this.getPosition().setFacing(Facing.EAST);
			else
				this.getPosition().setFacing(Facing.WEST);
			break;
			
		case WEST:
			if(commandType.equals(CommandType.LEFT))
				this.getPosition().setFacing(Facing.SOUTH);
			else
				this.getPosition().setFacing(Facing.NORTH);
			break;

		default:
			break;
		}
		
	}
	
	// Outputs the X,Y and F of the robot.
	public String report() {
		return String.format("Robot Position : %d, %d, %s", this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getFacing().name());
	}
	
	// Check the result of each command and if it will cause of a falling robot, 
	// then keeps the location and facing of robot and skips the next command.
	public boolean isMovementValid() {
		
		switch (this.getPosition().getFacing()) {
		
		case NORTH:
			if (this.getPosition().getY() < 5) return true;
			break;
			
		case EAST:
			if (this.getPosition().getX() < 5) return true;	
			break;
			
		case SOUTH:
			if (this.getPosition().getY() > 0) return true;
			break;
			
		case WEST:
			if (this.getPosition().getX() > 0) return true;
			break;

		default:
			break;
		}
		
		return false;
	}
		
}
