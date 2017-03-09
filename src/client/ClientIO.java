package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import common.Constant;
import common.Serializer;
import domain.NetworkIO;

public class ClientIO extends NetworkIO {

	public ClientIO(int port) {
		super(port);
		try {
			super.serverSocket = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendToServer(Object obj) {
		byte[] buf = Serializer.serialize(obj);
		DatagramPacket packet;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(Constant.CLIENT_HOST), Constant.PORT);
			sendPacket(packet);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
