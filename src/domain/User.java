package domain;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private int roomID;

	public User(String name) {
		this.name = name;
	}

	public User(String name, int roomID) {
		this.name = name;
		this.roomID = roomID;
	}

	public String getName() {
		return name;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

}
