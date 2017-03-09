package model;

import java.awt.Graphics2D;

public class Entity {

	int x, y;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {

	}

	public void draw(Graphics2D g2d) {

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
