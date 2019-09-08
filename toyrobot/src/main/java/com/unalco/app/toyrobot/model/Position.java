package com.unalco.app.toyrobot.model;

/**
 * @author yasinunal
 * This class represents the location of robot on table, a grid of 5x5
 */
public class Position {

	// Represents x index of location of robot in a grid of 5x5 units.
	private int x;
	
	// Represents y index of location of robot in a grid of 5x5 units.
	private int y;
	
	// Represents facing of robot : Possible values NORTH, EAST, SOUTH and WEST
	private Facing facing;

	public Position(int x, int y, Facing facing) {
		super();
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Facing getFacing() {
		return facing;
	}

	public void setFacing(Facing facing) {
		this.facing = facing;
	}
}
