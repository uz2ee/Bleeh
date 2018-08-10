package com.bleeh.entity.Objects;

import java.awt.Color;
import java.awt.Graphics;

import com.bleeh.assets.Asset;

public class Bullet extends Object{

	private float x,y,speed=10;
	public Bullet(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	public void update()
	{
		y-=speed;
	}
	public int getY()
	{
		return (int) y;
	}
	public int getX()
	{
		return (int) x;
	}
	public void render(Graphics g)
	{
		
		g.drawImage(Asset.bullet1,(int) x,(int)y,null);
		
	}
	
}
