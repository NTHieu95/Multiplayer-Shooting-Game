package client.listener;

import client.ClientIO;
import domain.NetworkIO;
import domain.notification.MoveNotification;
import model.OtherPlayer;

public class MoveListener extends Listener {

	OtherPlayer player;

	public MoveListener(ClientIO io, OtherPlayer player) {
		super(io);
		this.player = player;
	}

	@Override
	public void controll(Object obj) {
		if (obj instanceof MoveNotification) {
			MoveNotification move = (MoveNotification) obj;
			player.setX(move.getX());
			player.setY(move.getY());
		}
	}

}
