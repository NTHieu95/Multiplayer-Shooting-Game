package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import screen.GameScreen;

public class Bullet extends Entity {

	int movementSpeed = 4;

	public Bullet(int x, int y) {
		super(x, y);
	}

	public void update() {
		y -= movementSpeed;
		checkCollisions();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getEntityImage(), x, y, null);
	}

	public Image getEntityImage() {
		return ImageStore.getInstance().getBullet();
	}

	public void checkCollisions() {

		for (int i = 0; i < GameScreen.getUnknowList().size(); i++) {
			Unknow unknow = GameScreen.getUnknowList().get(i);
			if (getBounds().intersects(unknow.getBounds())) {
				GameScreen.removeBullet(this);
			}
		}

		for (int i = 0; i < GameScreen.getEnemyList().size(); i++) {
			Enemy enemy = GameScreen.getEnemyList().get(i);
			if (getBounds().intersects(enemy.getBounds())) {
				GameScreen.removeEnemy(enemy);
				GameScreen.removeBullet(this);
				GameScreen.addPoint(500);
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getEntityImage().getWidth(null), getEntityImage().getHeight(null));
	}
}
