package com.bleeh.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bleeh.input.MouseInput;

public class UIImageButton extends UserInterface{

	private BufferedImage []images;
	private Click clicker;
	public UIImageButton(float x, float y, int width, int height,BufferedImage[] images , Click clicker ) {
		super(x, y, width, height);
		this.images=images;
		this.clicker=clicker;
	}
	public void update() {
		
	}
	public void render(Graphics g) {
		if(hov)
		{
			g.drawImage(images[1],(int)x,(int)y,width,height,null );
		}
		else
			g.drawImage(images[0],(int)x,(int)y,width,height,null );
	}
	public void clickOn() {
		clicker.clickOn();
	}
}
