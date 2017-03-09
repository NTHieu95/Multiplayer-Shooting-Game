package model;

import java.awt.Image;
import java.util.Random;

import screen.GameScreen;

public class Enemy extends Unknow {

	int fire;

	public Enemy(int x, int y) {
		super(x, y);
		startY = y;
		startX = x;
	}

	@Override
	public void update() {
		y += 2;
		checkOffScreen();
		fireBullet();
	}

	@Override
	public Image getEntityImage() {
		return ImageStore.getInstance().getEnemy();
	}

	public void fireBullet() {
		if (fire % 150 == 0) {
			GameScreen.addEnemyBullet(new EnemyBullet(x+25, y+30));
			fire = 1;
		} else {
			fire++;
		}
	}
}
