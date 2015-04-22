package robot.cmd.ctrl;

import jssc.SerialPort;
import jssc.SerialPortException;

public class JSSC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    	String XBeePort = "/dev/ttyUSB0";
        SerialPort serialPort = new SerialPort(XBeePort);
        System.out.println(XBeePort);

        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Hello World!!!".getBytes()));
            System.out.println("Port closed: " + serialPort.closePort());
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }
    }
}