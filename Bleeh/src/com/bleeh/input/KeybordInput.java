package com.bleeh.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeybordInput implements KeyListener{
	
	private boolean keys[];
	public boolean left,right,firebullet,fireleser,exit,restart,reset,leaderBord,option,pause,resume;
	
	public KeybordInput()
	{
		keys=new boolean[256];
	}
	public void update()
	{
		left=keys[KeyEvent.VK_LEFT];
		right=keys[KeyEvent.VK_RIGHT];
		firebullet=keys[KeyEvent.VK_SPACE];
		fireleser=keys[KeyEvent.VK_DOWN];
		exit=keys[KeyEvent.VK_E];
		restart=keys[KeyEvent.VK_R];
		reset=keys[KeyEvent.VK_T];
		leaderBord=keys[KeyEvent.VK_L];
		option=keys[KeyEvent.VK_O];
		restart=keys[KeyEvent.VK_S];
		pause=keys[KeyEvent.VK_P];
		resume=keys[KeyEvent.VK_Q];
		
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
		System.out.println(e.getKeyCode());	
	}
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

}
