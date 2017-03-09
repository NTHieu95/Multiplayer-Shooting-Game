package game;

import client.ClientPlayer;
import domain.User;

public class JoinGame extends ShootingPlane {
	public JoinGame(String playerName, int roomID) {
		super();
		User p = new ClientPlayer(playerName, roomID);
		io.sendToServer(p);
	}
}
