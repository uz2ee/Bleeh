package com.bleeh.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UserInterface {

	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean hov=false;
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void clickOn();
	
	public UserInterface(float x,float y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		bounds=new Rectangle((int)x,(int) y,width,height);
	}
	
	public void onMouseMove(MouseEvent e)
	{
		if(bounds.contains(e.getX(), e.getY()))
		{
			hov=true;
		}
		else
			hov=false;
	}
	public void onMouseRelease(MouseEvent e)
	{
		if(hov)
			clickOn();
	}
	
	public void onMouseClick(MouseEvent e) {

		if(hov)
		{
			clickOn();
		}
		
	}
	
	
	
	/// Set Get 
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isHov() {
		return hov;
	}
	public void setHov(boolean hov) {
		this.hov = hov;
	}
	
}
