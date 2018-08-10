package com.bleeh.entity.Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boss extends Objects {

	
	private int health=110;




	

	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}



	protected BufferedImage boss;
	
	public Boss (BufferedImage boss,float x,float y,int health) {
		super(x,y);
		this.boss=boss;
		this.health=health;
		this.x=x;
	}
	int xa = 1;
	int ya = 1;
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public void update()
	{
		
		
		if (x + xa < 0)
			xa = speed;
		if (x + xa > 560 )
			xa = -speed;
		if (y + ya < 0)
			ya = speed;
		if (y + ya >230)
			ya = -speed;
		
		x = x + xa;
		y = y + ya;
		
	}
	
	public int getX()
	{
		return (int) x;
	}
	public int getY()
	{
		return (int) y;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(boss,(int)x,(int)y,200,200,null);
	}
}
