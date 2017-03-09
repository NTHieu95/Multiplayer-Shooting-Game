package domain.notification;

import java.io.Serializable;

public class MoveNotification implements Serializable {
	private int x;
	private int y;

	public MoveNotification(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
