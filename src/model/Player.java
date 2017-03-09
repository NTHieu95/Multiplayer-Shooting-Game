package model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player extends Entity {
	
	int velocityX = 0, velocityY = 0;
	
	private String label = "Player";
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	int minX =0, minY =0;
	int maxX =430, maxY =555;
	int tempX, tempY;

	int velX = 0;
	int speed = 5;
	String name;
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public void setx(int x){
		this.x = x;
	}
	public void sety(int y){
		this.y = y;
	}
	
	public Player (int x, int y, String name){
		super(x,y);
		this.name = name;
		
	}
	public void update(){
		
		tempX = x + velocityX;
		tempY = y + velocityY;
		if (tempX>=minX&&tempX<=maxX){
			x = tempX;
		}
		if (tempY>=minY&&tempY<=maxY){
			y = tempY;
		}
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getPlayerImg(),x,y,null);
		g2d.drawString(name, x, y);
		//g2d.draw(getBounds());
	}
	
	public Image getPlayerImg(){
		ImageIcon ic = new ImageIcon("assets/img/player.png");
		return ic.getImage();
	}
	
	
	
	
	
	
	
	public Rectangle getBounds(){
		return new Rectangle(x,y, getPlayerImg().getWidth(null), getPlayerImg().getHeight(null));
		
	}

}
