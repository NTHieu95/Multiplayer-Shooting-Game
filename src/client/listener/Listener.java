package client.listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import client.ClientIO;
import common.Constant;
import common.Serializer;

public abstract class Listener implements Runnable {
	protected ClientIO io;
	protected boolean running = true;

	public Listener(ClientIO io) {
		this.io = io;
	}

	@Override
	public void run() {
		byte[] buf = new byte[1024];
		DatagramPacket packet;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(Constant.CLIENT_HOST), Constant.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		while (running) {
			packet = io.getPacket();
			byte[] data = packet.getData();
			try {
				Object obj = Serializer.deserialize(data);
				controll(obj);
			} catch (ClassNotFoundException | IOException e) {
				// String mess = new String(data);
				// control(mess.trim());
			}
		}
	}

	public abstract void controll(Object obj);
}
