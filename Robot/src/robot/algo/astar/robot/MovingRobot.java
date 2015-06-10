package robot.algo.astar.robot;


import processing.core.PApplet;
import processing.serial.*;

@SuppressWarnings("serial")
public class MovingRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private static Serial port;
	private static String distance = "000.800";
	
	public static void InitXBeeCom(PApplet parent){
		//Initialize Xbee communication port
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1];  
		println(XBeePort);
		if(XBeePort.equals("ttyUSB0"))
			port = new Serial(parent, XBeePort, 38400);   
	}

	public static void AstarActionsPerforming(String chain){
		try {
			char cmd;
			for (int i = 0; i < chain.length(); i++) {
				cmd = chain.charAt(i);
				switch (cmd) {
					case 'A':
					{
						GOSTRAIGHT();
						Thread.sleep(10000);
						break;
					}
					case 'R':
					{
						GORIGHT();
						Thread.sleep(15000);
						break;
					}
					case 'L':
					{
						GOLEFT();
						Thread.sleep(15000);
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void GOSTRAIGHT(){
		String cmd = setCMD(distance,"000.000");
		System.out.println(cmd);
		sendTrame(cmd);
	}

	public static void GOLEFT(){
		sendTrame(setCMD("000.150","001.570"));
	}

	public static void GORIGHT(){
		sendTrame(setCMD("000.150","-001.570"));
	}
	
	public static String setCMD(String distance, String theta){
		return "d"+distance+"a"+theta+"f";
	}
	
	public static void sendTrame(String trame){
		port.write(trame);
	}
}
