package client.listener;

import client.ClientIO;
import domain.notification.UnknowNotification;
import domain.notification.EnemyNotification;
import model.Unknow;
import model.Enemy;
import screen.GameScreen;

public class EnemyListener extends Listener {

	public EnemyListener(ClientIO io) {
		super(io);
	}

	@Override
	public void controll(Object obj) {
		if (obj instanceof EnemyNotification) {
			EnemyNotification noti = (EnemyNotification) obj;
			GameScreen.addEnemy(new Enemy(noti.getX(), noti.getY()));
		}
	}

}
