package game;

import client.ClientIO;
import client.ClientPlayer;
import common.Constant;
import domain.User;

public class NewGame extends ShootingPlane {

	public NewGame(String playerName, String roomTitle) {
		super();
		User p = new ClientPlayer(playerName, roomTitle);
		io.sendToServer(p);
	}
}
