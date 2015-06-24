package robot.algo.astar.robot;


import java.util.List;

import processing.core.PApplet;
import processing.serial.*;

@SuppressWarnings("serial")
public class MovingRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private  Serial port;
	private static final String DISTANCE = "000.800";
	private String receiveData = "";
	
	public MovingRobot(PApplet parent){
		//Initialize Xbee communication port
		initXbeeCom(parent);
	}
	
	private void initXbeeCom(PApplet parent){
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1]; 
		println("XBeePort : "+XBeePort);
		port = new Serial(parent, XBeePort, 38400); 
	}
	

	public void AstarTrajectoryTracking(List<String> chain){
		println("BEGINING OF PERFORMING TRAGECTORY TRACKING");
		String cmd;
		int i = 0;
		receiveData = "F";
		do {
			cmd = chain.get(i);
			if(receiveData != null && receiveData.equalsIgnoreCase("F")){
				if(cmd.equals("R")){
					TURNRIGHT();
				}
				else if(cmd.equals("L")){
					TURNLEFT();
				}
				else{
					int coef = Integer.valueOf(cmd);
					println("Distance : "+getDistance(DISTANCE, coef));
					GOSTRAIGHT(getDistance(DISTANCE, coef));
				}
				i++;
			}//END IF
			receiveData = port.readString();
			println("performing...");
		}while(i < chain.size());//END WHILE
		println("END OF COMMANDS EXECTION WITH A TOTAL OF "+i+" ACTIONS");
	}
	

	public  void GOSTRAIGHT(String distance){
		String cmd = setCMD(distance,"000.000");
		sendDataToRobot(cmd);
	}

	public  void TURNLEFT(){
		sendDataToRobot(setCMD("000.000","001.570"));
	}

	public  void TURNRIGHT(){
		sendDataToRobot(setCMD("000.000","-001.570"));
	}
	
	public  String setCMD(String distance, String theta){
		return "d"+distance+"a"+theta+"f";
	}
	
	//Writing data to serial port
	public  void sendDataToRobot(String trame){
		port.write(trame);
	}
	
	//Reading data from serial port
	public String readDataFromRobot(){
		String receiveData = "G";
		if(port.available()>0){
			receiveData = port.readString();
			println("Received data "+receiveData);
		}
		return receiveData;
	}
	
	private String getDistance(String distance, int coef){
		float op = Float.valueOf(distance)*coef;
		return nf(op,3,3);
	}
	
	public void waiting(){

	}
	
	public void closePort(){
		port.clear();
		port.stop();
	}
}
