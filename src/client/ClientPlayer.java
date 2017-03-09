package client;

import domain.User;

public class ClientPlayer extends User {

	private String roomTitle;
	
	public ClientPlayer(String name, int roomID) {
		super(name, roomID);
	}

	public ClientPlayer(String name, String title) {
		super(name, 0);
		this.roomTitle = title;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

}
