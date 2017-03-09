package server.sender;

import java.io.IOError;
import java.net.DatagramPacket;
import java.util.Random;

import common.Serializer;
import domain.notification.UnknowNotification;
import model.Unknow;
import server.RoomManager;
import server.ServerIO;
import server.ServerPlayer;
import server.ServerRoom;

public class UnknowSender extends Sender {

	private RoomManager manager;
	private int roomID;
	private Random rand;

	public UnknowSender(ServerIO io, RoomManager manager, int roomID) {
		super(io);
		this.roomID = roomID;
		this.manager = manager;
		rand = new Random();
	}

	public void sendToAllPlayerInRoom(Object obj, int roomID) {
		for (ServerPlayer item : manager.getListPlayers(roomID)) {
			byte[] buf = Serializer.serialize(obj);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, item.getAddress(), item.getPort());
			super.io.sendPacket(packet);
		}
	}

	@Override
	public void running() {
		ServerRoom r = manager.getRooms().get(manager.findRoom(roomID));
		UnknowNotification noti = new UnknowNotification(rand.nextInt(430), -rand.nextInt(590));
		for (int i = 0; i < 4; i++) {
			sendToAllPlayerInRoom(noti, roomID);
		}
		try {
			Thread.sleep(r.getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
