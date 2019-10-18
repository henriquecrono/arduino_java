package br.edu.unicid.view;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.unicid.bean.Bagagem;
import br.edu.unicid.dao.BagagemDAO;

public final class PacketListener implements SerialPortPacketListener {

	public String lastUID;
	
	public void cleanLastUID () {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
			    lastUID = "";
			  }
			}, 0, 10000);
	}

	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public int getPacketSize() {
		return JSerialCommArduino.PACKET_SIZE_IN_BYTES;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		byte[] newData = event.getReceivedData();
		String tagUID = new String(newData).split("\n", 2)[0].replaceAll("\\s+", "");
		int byteSize = 0;
		try {
			byteSize = tagUID.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(PacketListener.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (byteSize == JSerialCommArduino.PACKET_SIZE_IN_BYTES && !tagUID.equals(lastUID)) {
			Bagagem bagagem = new Bagagem();

			try {
				BagagemDAO bagagemDAO = new BagagemDAO();
				bagagem = bagagemDAO.consultarBagagem(tagUID);
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println("\n\nBagagem encontrada!"
					+ "\nBagagem: " + bagagem.getTagUID()
					+ "\nPassageiro: " + bagagem.getNomePassageiro()
					+ "\nPoltrona: " + bagagem.getPoltronaPassageiro()
					+ "\nFavor se dirigir até o setor vermelho.");

			System.out.println("==============================");
			
			lastUID = tagUID;
		}
	}
}
