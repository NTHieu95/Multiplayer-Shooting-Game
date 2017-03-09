package model;

import java.awt.Image;

import javax.swing.JOptionPane;

import screen.GameScreen;

public class EnemyBullet extends Bullet {

	public EnemyBullet(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		y += movementSpeed;
		checkCollisions();
	}
	
	@Override
	public void checkCollisions() {

			Player player = GameScreen.getPlayer();
			if (getBounds().intersects(player.getBounds())) {
				JOptionPane.showMessageDialog(null, "Too bad, You have failed at level " + GameScreen.level + "!");
				System.exit(0);
			}
		
	}
	
	@Override
	public Image getEntityImage() {
		return ImageStore.getInstance().getEnemyBullet();
	}
}
