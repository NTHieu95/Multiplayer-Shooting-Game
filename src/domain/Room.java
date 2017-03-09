package domain;

import java.io.Serializable;

public class Room implements Serializable{
	protected int id;
	protected String title;
	protected int level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		return "ID: " + id + " Title: " + title + " Level: " + level;
	}
}
