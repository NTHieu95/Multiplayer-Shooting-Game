package server;

import java.io.Serializable;
import java.util.ArrayList;

import common.Constant;
import domain.NetworkIO;
import domain.User;
import domain.Room;

public class ServerRoom extends Room implements Serializable {

	private static int AI = 0;
	private ArrayList<ServerPlayer> players;
	private int level_max = 10;

	public ServerRoom(NetworkIO io) {
		AI++;
		super.id = AI;
		players = new ArrayList<ServerPlayer>();
	}

	public int getId() {
		return id;
	}

	public void addPlayer(User p) {
		if (!players.contains(p)) {
			players.add((ServerPlayer) p);
		}
	}

	public ArrayList<ServerPlayer> getPlayers() {
		return players;
	}

	public ServerPlayer findPlayer(int port) {
		for (ServerPlayer p : players) {
			if (p.getPort() == port)
				return p;
		}
		return null;
	}

	public void removePlayer(int port) {
		ServerPlayer p = findPlayer(port);
		if (p != null) {
			players.remove(p);
		}
	}

	public int getSleepTime() {
		return 5500 - (level - 1) * 500;
	}
}
