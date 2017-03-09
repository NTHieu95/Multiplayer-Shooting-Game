package server;

import java.util.ArrayList;

import domain.NetworkIO;
import domain.Room;

public class RoomManager {

	private static ArrayList<ServerRoom> rooms;

	public RoomManager() {
		rooms = new ArrayList<ServerRoom>();
	}

	public int createRoom(String roomTitle, ServerPlayer p, NetworkIO io) {
		ServerRoom r = new ServerRoom(io);
		r.setLevel(1);
		r.setTitle(roomTitle);
		p.setRoomID(r.getId());
		r.addPlayer(p);
		rooms.add(r);
		return r.getId();
	}

	public void joinRoom(int room, ServerPlayer p) {
		int index = findRoom(room);
		ServerRoom r = rooms.get(index);
		r.addPlayer(p);
		rooms.set(index, r);
	}

	public int findRoom(int roomID) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getId() == roomID)
				return i;
		}
		return -1;
	}

	public ArrayList<ServerPlayer> getListPlayers(int roomID) {
		return rooms.get(findRoom(roomID)).getPlayers();
	}

	public ServerPlayer findPlayer(int port) {
		ServerPlayer p;
		for (ServerRoom r : rooms) {
			p = r.findPlayer(port);
			if (p != null)
				return p;
		}
		return null;
	}

	public ArrayList<ServerRoom> getRooms() {
		return rooms;
	}

	public void outGame(ServerPlayer player) {
		int index = findRoom(player.getRoomID());
		ServerRoom r = rooms.get(index);
		r.removePlayer(player.getPort());
		if (r.getPlayers().size() == 0) {
			rooms.remove(r);
		} else {
			rooms.set(index, r);
		}
	}

	public void updateRoomLevel(int level, int port) {
		ServerPlayer player = findPlayer(port);
		int index = findRoom(player.getRoomID());
		ServerRoom r = rooms.get(index);
		r.setLevel(level);
		rooms.set(index, r);
		System.out.println("level up");
	}
}
