package robot.algo.astar.robot;

import java.util.ArrayList;
import java.util.List;
import robot.algo.astar.Path;

public class RobotActionsPlanning{

	/**
	 * The robot receives commands as characters to move from one box to another.
	 * N to move upwards.
	 * S to move down.
	 * E to move to the right..
	 * W to move to the left..
	 * @param path
	 * @return chain of travels
	 */
	
	public static String AStarPathFollowing(Path path){
		StringBuilder chain = new StringBuilder();
		for (int i = 0; i < path.getLength()-1; i++) {
			 int xC = path.getX(i), xF = path.getX(i+1);
			 int yC = path.getY(i), yF = path.getY(i+1);
			 
			 if(xC > xF && yC == yF){
				 chain.append("N");
			 }
			 else if(xC < xF && yC == yF){
				 chain.append("S");
			 }
			 else if(yC < yF && xC == xF){
				 chain.append("E");
			 }
			 else if(yC > yF && xC == xF){
				 chain.append("W");
			 }
		}
		return chain.toString();
	}

	public static String AstarGenerateTrajectory(String chain){
		StringBuilder actions = new StringBuilder();
		for (int i = 0; i < chain.length() - 1; i++) {
			char currentAction = chain.charAt(i);
			char followingAction = chain.charAt(i+1);
			
			if(currentAction== followingAction){
				actions.append("A");
			}
			else if((currentAction== 'N' && followingAction == 'E') ||
					(currentAction== 'E' && followingAction == 'S') ||
					(currentAction== 'S' && followingAction == 'W') ||
					(currentAction== 'W' && followingAction == 'N')){
					actions.append("RA");
			}
			else if((currentAction== 'N' && followingAction == 'W') ||
					(currentAction== 'W' && followingAction == 'S') ||
					(currentAction== 'S' && followingAction == 'E') ||
					(currentAction== 'E' && followingAction == 'N'))
			{
				actions.append("LA");
			}
			
		}
		return actions.toString();
	}
	
	public static List<String> AstarTrajectoryTracking(String chain){
		int count = 0; 
		List<String> actions = new ArrayList<String>();
		for (int i = 0; i < chain.length(); i++) {
			String cmd = String.valueOf(chain.charAt(i));
			
			if( cmd.equalsIgnoreCase("A")){
				count++;
			}
			else{
				if(count == 0) actions.add(cmd);
				else {
					actions.add(String.valueOf(count)); 
					actions.add(cmd); 
					count = 0;
				}
			}
		}
		if(count != 0) actions.add(String.valueOf(count));
		return actions;
	}
	
	public static void main(String[] args) {
		String cmd = "ESSSEESSEENNNE";
		cmd = AstarGenerateTrajectory(cmd);
		System.out.println(cmd);
		List<String> chain = AstarTrajectoryTracking(cmd);
		System.out.println(chain);	
	}
}
