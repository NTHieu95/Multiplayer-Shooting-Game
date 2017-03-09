package domain.notification;

import java.io.Serializable;

public class IncreaseLevel implements Serializable{
	int level;

	public IncreaseLevel(int level) {
		super();
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
