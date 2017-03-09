package client.listener;

import client.ClientIO;
import domain.notification.UnknowNotification;
import model.Unknow;
import screen.GameScreen;

public class UnknowListener extends Listener {

	public UnknowListener(ClientIO io) {
		super(io);
	}

	@Override
	public void controll(Object obj) {
		if (obj instanceof UnknowNotification) {
//			System.out.println("get object");
			UnknowNotification noti = (UnknowNotification) obj;
			GameScreen.addUnknow(new Unknow(noti.getX(), noti.getY()));
		}
	}

}