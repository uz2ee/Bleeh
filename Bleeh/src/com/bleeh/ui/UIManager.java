package com.bleeh.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.bleeh.launcher.Manager;

public class UIManager {

	private Manager manager;
	private ArrayList<UserInterface> objects;
	public UIManager(Manager manager)
	{
		this.manager=manager;
		objects=new ArrayList<UserInterface>();

		
	}
	public void update() { 
		for(UserInterface o : objects)
			o.update();
		
	}
	public void render(Graphics g)
	{
		for(UserInterface o : objects)
			o.render(g);
	}
	public void onMouseMove(MouseEvent e)
	{
		for(UserInterface o : objects)
			o.onMouseMove(e);
	}
	public void onMouseClick(MouseEvent e)
	{
		for(UserInterface o : objects)
			o.onMouseClick(e);
	}
	public void onMouseRelease(MouseEvent e)
	{
		for(UserInterface o : objects)
			o.onMouseRelease(e);
	}
	public void addObject(UserInterface o)
	{
		objects.add(o);
	}
	public void removeObject(UserInterface o)
	{
		objects.remove(o);
	}
}
