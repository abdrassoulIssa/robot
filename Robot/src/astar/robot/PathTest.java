package astar.robot;
import astar.AStarPathFinder;
import astar.Path;
import astar.PathFinder;

/**
 * A simple test to show some path finding at unit movement
 * 
 * @author Issa
 */
public class PathTest{
	
	public static void main(String[] argv) {
		/** The map on which the units will move */
		 RobotMap map      = new RobotMap("data/map.dat");
		/** The path finder we'll use to search our map */
		 PathFinder finder = new AStarPathFinder(map, 400, true);
		 
		 Path path = finder.findPath(new Robot(1), 0, 0, 19, 19);
		 map.display();
		 System.out.println("Path length"+path.getLength());
		 System.out.println(path.getY(15));
		 System.out.println("Bonjour");
	}
}