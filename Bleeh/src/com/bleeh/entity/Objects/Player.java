package com.bleeh.entity.Objects;

import java.awt.Graphics;

import com.bleeh.assets.Asset;
import com.bleeh.launcher.Manager;
import com.bleeh.state.GameState;

public class Player extends Objects {

	private Manager manager;
	private int playerSpeed;
	private double d=0;
	int fps=70;
	double tpu=1000000000/fps;
	long now;
	long last ;
	private int health=3;
	/**
	 * 
	 */
	private int power=10;
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public Player(Manager manager,float x, float y) {
		super(x, y);
		this.manager=manager;
		last= System.nanoTime();	
	}
	
	public void update() {
		if(manager.getKeybordInput().left && x>0)
		{
			x-=playerSpeed;;
		}
		if(manager.getKeybordInput().right && x<manager.getHeight()-100 )
		{
			x+=playerSpeed;;
		}
		if(manager.getKeybordInput().firebullet  )
		{
			
			
			
			now=System.nanoTime();
			d+= (now-last)/tpu;
			last=now;
			if(d>power) {
				//GameState.bullet.add(new Bullet(x+25,y-5));
				GameState.bullet.add(new Bullet(x+42,y-5));
				//GameState.bullet.add(new Bullet(x+55,y-5));
				d=0;
			}
			
		}
	}
	public int getX()
	{
		return (int) x;
	}
	public int getY()
	{
		return (int) y;
	}
	
	public void render(Graphics g) {
		g.drawImage(Asset.gameplay, 0, 0,null);
		g.drawImage(Asset.sheild1,(int) x-18,(int) y-22, null);
		
		g.drawImage(Asset.playership1,(int) x,(int) y, null);
	}

}
