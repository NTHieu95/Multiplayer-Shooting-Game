package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import screen.GameScreen;

public class Unknow extends Entity {

	int startY;
	int startX;

	int movementSpeed = 2;
	int imageI = 0;

	public Unknow(int x, int y) {
		super(x, y);
		startY = y;
		startX = x;
	}

	public void update() {
		y++;
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getEntityImage(), x, y, null);
	}

	public Image getEntityImage() {
		Image image = null;
		if (imageI % 2 == 0) {
			image = ImageStore.getInstance().getUnknow();
			imageI += 1;
		} else {
			image = ImageStore.getInstance().getUnknow();
			imageI -= 1;
		}
		return image;
	}

	public void checkOffScreen() {
		if (y >= 640) {
			GameScreen.removeUnknow(this);
		}
		if (x >= 590 || x <= 0) {
			GameScreen.removeUnknow(this);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getEntityImage().getWidth(null), getEntityImage().getHeight(null));
	}
}
