package robot.algo.otsu;

public interface OTSUConstant {
	
	//Image resolution 640*480
	public static final int MWIDTH    = 640;
	public static final int MHEIGHT   = 480;
	
	//The navigation map description
	public static final int CELLSIZE  = 40;
	public static final int MROWS     = MHEIGHT/CELLSIZE;
	public static final int MCOLS     = MWIDTH/CELLSIZE;
	
	//A star algorithm parameters
	public static final int maxSearchDistance = 1000;
	public static final boolean diagMovment = true;

}
