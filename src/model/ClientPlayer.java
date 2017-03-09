package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.ClientIO;
import domain.notification.FireNotification;
import domain.notification.MoveNotification;
import screen.GameScreen;

public class ClientPlayer extends Player {

	private ClientIO io;

	public ClientPlayer(int x, int y,String name, ClientIO io) {
		super(x, y, name);
		this.io = io;
	}

	@Override
	public void update() {
		super.update();
		 checkCollisions();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			velocityY = -speed;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			velocityX = -speed;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			velocityY = speed;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			velocityX = speed;
		}
		io.sendToServer(new MoveNotification(getX(), getY()));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			velocityY = 0;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			velocityX = 0;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			velocityY = 0;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			velocityX = 0;
		} else if (key == KeyEvent.VK_SPACE) {
			GameScreen.addBullet(new Bullet(x+36, y));
			FireNotification noti = new FireNotification();
			noti.setX(x);
			noti.setY(y);
			io.sendToServer(noti);
		}
	}

	public void checkCollisions() {
		ArrayList<Unknow> unknows = GameScreen.getUnknowList();

		for (int i = 0; i < unknows.size(); i++) {
			Unknow unknow = unknows.get(i);
			if (getBounds().intersects(unknow.getBounds())) {
				JOptionPane.showMessageDialog(null, "Too bad, You have failed at level " + GameScreen.level + "!");
				System.exit(0);
			}
		}

		ArrayList<Enemy> enemies = GameScreen.getEnemyList();

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (getBounds().intersects(enemy.getBounds())) {
				JOptionPane.showMessageDialog(null, "Too bad, You have failed at level " + GameScreen.level + "!");
				System.exit(0);
			}
		}
	}
}
