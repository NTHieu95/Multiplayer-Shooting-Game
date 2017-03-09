package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageStore {
	private Image player;
	private Image unknow;
	
	private Image bullet;
	private Image enemy;
	private Image enemyBullet;
	private Image explosion;

	private static ImageStore instance = null;

	public static synchronized ImageStore getInstance() {
		if (instance == null)
			instance = new ImageStore();
		return instance;
	}

	private ImageStore() {
		player = new ImageIcon("assets/img/player.png").getImage();
		unknow = new ImageIcon("assets/img/obj.gif").getImage();
		
		bullet = new ImageIcon("assets/img/missile.png").getImage();
		enemy = new ImageIcon("assets/img/enemy.png").getImage();
		enemyBullet = new ImageIcon("assets/img/missile.png").getImage();
		explosion = new ImageIcon("assets/img/explosion2.png").getImage();
	}

	public Image getPlayer() {
		return player;
	}

	public void setPlayer(Image player) {
		this.player = player;
	}

	public Image getUnknow() {
		return unknow;
	}

	public void setUnknow(Image unknow) {
		this.unknow = unknow;
	}


	public Image getBullet() {
		return bullet;
	}

	public void setBullet(Image bullet) {
		this.bullet = bullet;
	}

	public Image getEnemy() {
		return enemy;
	}

	public void setEnemy(Image enemy) {
		this.enemy = enemy;
	}

	public Image getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(Image enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public Image getExplosion() {
		return explosion;
	}

	public void setExplosion(Image explosion) {
		this.explosion = explosion;
	}

}
