package robot.algo.astar.robot;

import robot.algo.astar.Mover;

/**
 * A mover to represent one of a ID based unit in our 
 * robot map
 *
 * @author Issa
 */
public class Robot implements Mover {
	/** The unit ID moving */
	private int type;
	
	/**
	 * Create a new mover to be used while path finder
	 * 
	 * @param type The ID of the unit moving
	 */
	public Robot(int type) {
		this.type = type;
	}
	
	/**
	 * Get the ID of the unit moving
	 * 
	 * @return The ID of the unit moving
	 */
	public int getType() {
		return type;
	}
}