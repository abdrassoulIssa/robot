package robot.algo.otsu;

public interface OTSUConstant {
	
	//Image resolution 640*480
	public static final int WIDTH    = 640;
	public static final int HEIGHT   = 480;
	
	//The navigation map description
	public static final int CELLSIZE = 30;
	public static final int ROWS     = HEIGHT/CELLSIZE;
	public static final int COLS     = WIDTH/CELLSIZE;
	
	//A star algorithm parameters
	public static final int maxSearchDistance = 1000;
	public static final boolean diagMovment = false;

}
