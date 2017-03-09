package function;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import client.ClientIO;
import domain.notification.MoveNotification;
import model.ClientPlayer;

public class InputAdapter extends KeyAdapter {

	
	private ClientPlayer player;

	public InputAdapter(ClientPlayer player, ClientIO io) {
		this.player = player;
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
		
	}

}
