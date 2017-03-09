package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import common.Constant;
import common.Serializer;
import domain.NetworkIO;
import server.ServerPlayer;
import server.ServerRoom;

public class ServerIO extends NetworkIO {

	public ServerIO(int port) {
		super(port);
		
		try {
			super.serverSocket = new DatagramSocket(Constant.PORT, InetAddress.getByName(Constant.SERVER_HOST));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void sendToClient(Object obj, InetAddress address, int port) {
		byte[] buf = Serializer.serialize(obj);
		DatagramPacket packet;
		packet = new DatagramPacket(buf, buf.length, address, port);
		sendPacket(packet);
	}

}
