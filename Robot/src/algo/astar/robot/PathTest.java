package algo.astar.robot;

import java.io.IOException;

import algo.astar.AStarPathFinder;
import algo.astar.Path;
import algo.astar.PathFinder;

/**
 * A simple test to show some path finding at unit movement
 * 
 * @author Issa
 */
public class PathTest{
	
	public static void main(String[] argv) throws IOException {
		/** The map on which the units will move */
		 RobotMap map      = new RobotMap("data/map3.dat");
		/** The path finder we'll use to search our map */
		 PathFinder finder = new AStarPathFinder(map, 400, true);
		 
		 Path path = finder.findPath(new Robot(1), 0, 0, 19, 19);
		 map.display();
		 
		 System.out.println("Path length"+path.getLength());
		 for (int i = 0; i < path.getLength(); i++) {
			 System.out.println("("+path.getX(i)+","+path.getY(i)+")");
		 }
	}
}