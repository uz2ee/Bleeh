package com.bleeh.entity.Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Enamy {

	
	
	private int x,y=0;
	private int health=10;
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	
	

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



	protected BufferedImage enamy;
	
	public Enamy (BufferedImage enamy,int x,int health) {
		this.enamy=enamy;
		this.health=health;
		this.x=x;
	}
	
	public void update()
	{
		y+=1;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(enamy,x,y,50,50,null);
	}
	

}
