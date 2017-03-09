package screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import client.ClientIO;
import client.listener.UnknowListener;
import client.listener.EnemyListener;
import client.listener.FireListener;
import client.listener.MoveListener;
import domain.notification.IncreaseLevel;
import function.InputAdapter;

import menu.MainMenu;
import model.Bullet;
import model.Unknow;
import model.ClientPlayer;
import model.Enemy;
import model.EnemyBullet;
import model.OtherPlayer;
import model.Player;

public class GameScreen extends JPanel implements ActionListener {

	/**
	 * 
	 */
	
	int enemyCount = 2;
	
	private static final long serialVersionUID = 1L;

	Timer mainTimer;
	
	

	static ClientPlayer player;
	static OtherPlayer player2;
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();

	static ArrayList<Unknow> unknows = new ArrayList<Unknow>();
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	static int score = 0;
	
	int yBack=0;

	Random rand = new Random();
	ImageIcon background = new ImageIcon();

	public static int level = 1;
	private int tmp = 0;
	private ClientIO io;

	public GameScreen(ClientIO io) {
		this.io = io;
		setFocusable(true);
		
		

		player = new ClientPlayer(200, 520, MainMenu.name, io);
		addKeyListener(new InputAdapter(player, io));

		player2 = new OtherPlayer(0, 520,MainMenu.name);

		getBackgroundImage();

		mainTimer = new Timer(10, this);
		mainTimer.start();

		MoveListener moveListener = new MoveListener(io, player2);
		Thread th = new Thread(moveListener);
		th.start();

		FireListener fireListener = new FireListener(io);
		Thread th1 = new Thread(fireListener);
		th1.start();

		UnknowListener unknowListener = new UnknowListener(io);
		Thread th2 = new Thread(unknowListener);
		th2.start();

		EnemyListener enemyListener = new EnemyListener(io);
		Thread th3 = new Thread(enemyListener);
		th3.start();
		
		startGame();

	}

	private void getBackgroundImage() {
		background = new ImageIcon("assets/img/background.png");
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(background.getImage(), 0, yBack, null);
		if(yBack>0){
			g2d.drawImage(background.getImage(),0,yBack-600,null);
		}
		g2d.drawString("Level: " + level, 10, 20);
		
		g2d.drawString("Score: " + score, 100, 20);
		player.draw(g2d);
		player2.draw(g2d);

//		for (int i = 0; i < unknows.size(); i++) {
//			unknows.get(i).draw(g2d);
//		}
		
		for(int i =0; i < unknows.size(); i++){
			Unknow tempUnknow = unknows.get(i);
			tempUnknow.draw(g2d);
		}

		for(int i =0; i < enemies.size(); i++){
			Enemy tempEnemy= enemies.get(i);
			tempEnemy.draw(g2d);
		}
		for(int i =0; i < bullets.size(); i++){
			Bullet tempBullet = bullets.get(i);
			tempBullet.draw(g2d);
		}
		for(int i =0; i < enemyBullets.size(); i++){
			EnemyBullet tempEnemyBullet = enemyBullets.get(i);
			tempEnemyBullet.draw(g2d);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		player.update();
		player2.update();
		
		yBack++;
		if(yBack == 600) yBack = 0;

		for (int i = 0; i < unknows.size(); i++) {
			Unknow unknow = unknows.get(i);
			unknow.update();
		}

		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.update();
		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.update();
		}

		for (int i = 0; i < enemyBullets.size(); i++) {
			EnemyBullet bullet = enemyBullets.get(i);
			bullet.update();
		}
		
		checkEnd();

		repaint();
		
	}

	public static void addUnknow(Unknow unknow) {
		unknows.add(unknow);
	}

	public static void removeUnknow(Unknow unknow) {
		unknows.remove(unknow);
	}

	public static ArrayList<Unknow> getUnknowList() {
		return unknows;
	}

	public static void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public static void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}

	public static ArrayList<Enemy> getEnemyList() {
		return enemies;
	}

	public static void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public static void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public static ArrayList<Bullet> getBulletList() {
		return bullets;
	}

	public static void addEnemyBullet(EnemyBullet bullet) {
		enemyBullets.add(bullet);
	}

	public static void removeEnemyBullet(EnemyBullet bullet) {
		enemyBullets.remove(bullet);
	}

	public static ArrayList<EnemyBullet> getEnemyBulletList() {
		return enemyBullets;
	}

	public static Player getPlayer() {
		return player;
	}

	public void startGame() {
		
		enemyCount = level*2;
		
		for (int i=0; i < enemyCount; i++){
			addEnemy(new Enemy(rand.nextInt(440), -100 -rand.nextInt(600)));
		}
		
		for (int i=0; i < enemyCount; i++){
			addUnknow(new Unknow(rand.nextInt(440), -100 -rand.nextInt(600)));
		}
		
	}

	

	public static void addPoint(int amount) {
		score += amount;
	}

	public void checkEnd() {
		if (score%2000==0&&score>0&&tmp!=score) {
			level++;
			tmp = score;
			bullets.clear();
			enemies.clear();
			unknows.clear();
			enemyBullets.clear();
			io.sendToServer(new IncreaseLevel(level));
			
			//JOptionPane.showMessageDialog(null, "Good, you have completed level "+(level-1)+". Let's move on to stage "+level+"!!!");
			startGame();
		}
	}
}
