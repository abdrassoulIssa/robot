package JSSCSerial;
import java.util.Arrays;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
 
public class MyCOM {
 
   public static void main(String[] args) {
      // get computer serial ports names
      String[] portNames = SerialPortList.getPortNames();
      for (String port : portNames) {
         System.out.println(port);
      }
 
      System.out.println(Arrays.toString(portNames)+"here");
      // initialization with selecting port for communication
      SerialPort serialPort = new SerialPort("COM3");
 
      
      try {
         // open port for communication
         serialPort.openPort();
         // baundRate, numberOfDataBits, numberOfStopBits, parity
         serialPort.setParams(9600, 8, 1, 0);
         // byte data transfer
         serialPort.writeBytes("text to tranfer".getBytes());
         // String data transfer
         serialPort.writeString("text to tranfer");
         // close port
         serialPort.closePort();
      } catch (SerialPortException ex) {
         System.out.println(ex);
      }
      
   }
}