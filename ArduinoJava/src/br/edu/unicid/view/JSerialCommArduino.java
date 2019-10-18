package br.edu.unicid.view;
import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

public class JSerialCommArduino {


	public static SerialPort arduinoPort = null;
	public static InputStream arduinoStream = null;
	public static int PACKET_SIZE_IN_BYTES = 8;

	public static void main(String[] args) {

		int len = SerialPort.getCommPorts().length;
		SerialPort serialPorts[] = new SerialPort[len];
		serialPorts = SerialPort.getCommPorts();

		for (int i = 0; i < len; i++) {

			String portName = serialPorts[i].getDescriptivePortName();
			System.out.println(serialPorts[i].getSystemPortName() + ": " + portName + ": " + i);

			arduinoPort = serialPorts[i];
			arduinoPort.openPort();
			System.out.println("connected to: " + portName + "[" + i + "]");
			break;

		}

		PacketListener listener = new PacketListener();
		arduinoPort.addDataListener(listener);
		listener.cleanLastUID();
	}

}
