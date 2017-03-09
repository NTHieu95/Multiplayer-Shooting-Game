package client.listener;

import client.ClientIO;
import domain.notification.FireNotification;
import model.Bullet;
import screen.GameScreen;

public class FireListener extends Listener {

	public FireListener(ClientIO io) {
		super(io);
	}

	@Override
	public void controll(Object obj) {
		if (obj instanceof FireNotification) {
			FireNotification noti = (FireNotification) obj;
			GameScreen.addBullet(new Bullet(noti.getX(), noti.getY()));
		}
	}

}
