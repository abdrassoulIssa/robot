package robot.algo.astar.robot;


import java.util.Arrays;
import java.util.List;

import processing.core.PApplet;
import processing.serial.*;

@SuppressWarnings("serial")
public class MovingRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private  Serial port;
	private static final String DISTANCE = "000.800";
	
	public MovingRobot(PApplet parent){
		//Initialize Xbee communication port
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1]; 
		println(Arrays.toString(Serial.list()));
		println("XBeePort "+XBeePort);
		port = new Serial(parent, XBeePort, 38400);  
	}

	public void AstarActionsPerforming(List<String> chain){
		String cmd;
		for (int i = 0; i < chain.size(); i++) {
			cmd = chain.get(i);
			if(port.available() > 0){
				switch (cmd) {
					case "R":
					{
						TURNRIGHT();
						//Thread.sleep(15000);
						break;
					}
					case "L":
					{
						TURNLEFT();
						//Thread.sleep(15000);
						break;
					}
					default :{
						int coef = Integer.valueOf(new String(cmd));
						println("Distance "+getDistance(DISTANCE, coef));
						GOSTRAIGHT(getDistance(DISTANCE, coef));
						/*
						if(coef == 1)
							Thread.sleep(10000);
						else
							Thread.sleep(coef*3000+10000);
						*/
						break;
					}
				}//END SWITCH
			}//END IF
		}//END FOR
	}
	public  void GOSTRAIGHT(String distance){
		String cmd = setCMD(distance,"000.000");
		sendDataToRobot(cmd);
	}

	public  void TURNLEFT(){
		sendDataToRobot(setCMD("000.150","001.570"));
	}

	public  void TURNRIGHT(){
		sendDataToRobot(setCMD("000.150","-001.570"));
	}
	
	public  String setCMD(String distance, String theta){
		return "d"+distance+"a"+theta+"f";
	}
	
	//Writing data to serial port
	public  void sendDataToRobot(String trame){
		port.write(trame);
	}
	
	//Reading data from serial port
	public boolean readDataFromRobot(){
		boolean signal = false;
		int receiveData;
		if(port.available()>0){
			receiveData = port.read();
			signal = true;
		}
		return signal;
	}
	
	private String getDistance(String distance, int coef){
		float op = Float.valueOf(distance)*coef;
		return nf(op,3,3);
	}
}
