package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import client.ClientPlayer;
import common.Constant;
import common.Serializer;
import domain.User;
import domain.notification.FireNotification;
import domain.notification.IncreaseLevel;
import domain.notification.MoveNotification;
import server.sender.UnknowSender;
import server.sender.EnemySender;

public class Server {
	private ServerIO io;
	private RoomManager manager;

	public Server(int port) {
		io = new ServerIO(port);
		manager = new RoomManager();
	}

	public void start() throws UnknownHostException, SocketException {
		Object obj;
		byte[] data;
		DatagramPacket packet;
		while (true) {
			System.out.println("Waiting for packet...");
			packet = io.getPacket();
			data = packet.getData();
			System.out.println(packet.getPort());
			try {
				obj = Serializer.deserialize(data);
				if (obj instanceof User) {
					ClientPlayer player = (ClientPlayer) obj;
					playerControl(player, packet.getAddress(), packet.getPort());
				} else if (obj instanceof String) {
					messageControl((String) obj, packet.getAddress(), packet.getPort());
				} else if (obj instanceof MoveNotification) {
					MoveNotification move = (MoveNotification) obj;
					moveControl(packet.getPort(), move);
				} else if (obj instanceof FireNotification) {
					FireNotification fr = (FireNotification) obj;
					fireControl(packet.getPort(), fr);
				} else if (obj instanceof IncreaseLevel) {
					IncreaseLevel level = (IncreaseLevel) obj;
					levelControl(level, packet.getPort());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void messageControl(String message, InetAddress address, int port) {
		if (message.equals(Constant.JOIN_MESS)) {
			sendListRooms(address, port);
		} else if (message.equals(Constant.QUIT_MESS)) {
			ServerPlayer p = manager.findPlayer(port);
			sendToOtherPlayerInRoom(message, p.getRoomID(), p.getPort());
			manager.outGame(p);
		}
	}

	public void playerControl(ClientPlayer player, InetAddress address, int port) {
		ServerPlayer p;
		if (player.getRoomID() == 0) {
			p = new ServerPlayer(player.getName());
			p.setPort(port);
			p.setAddress(address);
			int id = manager.createRoom(player.getRoomTitle(), p, io);
			System.out.println(p.getName().trim() + " connected");

			UnknowSender unknowSender = new UnknowSender(io, manager, id);
			Thread th = new Thread(unknowSender);
			th.start();

			EnemySender enemySender = new EnemySender(io, manager, id);
			Thread th1 = new Thread(enemySender);
			th1.start();
		} else {
			p = new ServerPlayer(player.getName());
			p.setRoomID(player.getRoomID());
			p.setPort(port);
			p.setAddress(address);
			manager.joinRoom(p.getRoomID(), p);
			sendToAllPlayerInRoom(Constant.JOIN_MESS, p.getRoomID());
		}
	}

	public void moveControl(int port, MoveNotification move) {
		ServerPlayer p = manager.findPlayer(port);
		sendToOtherPlayerInRoom(move, p.getRoomID(), p.getPort());
	}

	public void fireControl(int port, FireNotification noti) {
		ServerPlayer p = manager.findPlayer(port);
		for (int i = 0; i < 6; i++)
			sendToOtherPlayerInRoom(noti, p.getRoomID(), p.getPort());
	}

	public void sendListRooms(InetAddress address, int port) {
		for (ServerRoom r : manager.getRooms()) {
			byte[] buf = Serializer.serialize(r);
			DatagramPacket packet3 = new DatagramPacket(buf, buf.length, address, port);
			io.sendPacket(packet3);
		}
	}

	public void sendToOtherPlayerInRoom(Object obj, int roomID, int playerPort) {
		for (ServerPlayer item : manager.getListPlayers(roomID)) {
			if (item.getPort() != playerPort) {
				byte[] buf = Serializer.serialize(obj);
				DatagramPacket packet2 = new DatagramPacket(buf, buf.length, item.getAddress(), item.getPort());
				io.sendPacket(packet2);
			}
		}
	}

	public void sendToAllPlayerInRoom(Object obj, int roomID) {
		for (ServerPlayer item : manager.getListPlayers(roomID)) {
			byte[] buf = Serializer.serialize(obj);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, item.getAddress(), item.getPort());
			io.sendPacket(packet);
		}
	}

	public void levelControl(IncreaseLevel level, int port) {
		manager.updateRoomLevel(level.getLevel(), port);
	}

	public static void main(String agrs[]) throws IOException {
		Server server = new Server(Constant.PORT);
		server.start();
	}
}
