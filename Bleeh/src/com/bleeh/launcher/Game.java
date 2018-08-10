package com.bleeh.launcher;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bleeh.assets.Asset;
import com.bleeh.dispaly.Display;
import com.bleeh.input.KeybordInput;
import com.bleeh.input.MouseInput;
import com.bleeh.state.GameState;
import com.bleeh.state.ScoreBoardState;
import com.bleeh.state.State;


public class Game implements Runnable{
	public Display display;
	private int height;
	
	protected int width;
	private String title;
	private boolean running =false;
	public Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private KeybordInput keybordInput;
	private MouseInput mouseInput;
	public State gameState,scoreState,menuState;
	private Manager manager;
	
	
	double x=0,y=350;

	
	public Graphics getGraphic()
	{
		return g;
	}
	public Game(String title,int height,int width) {
		
		this.height=height;
		this.width=width;
		this.title=title;
		keybordInput=new KeybordInput();
		mouseInput=new MouseInput();
	}

	

	private void init() {
		display=new Display(title,height,width);
		display.getFrame().addKeyListener(keybordInput);
		display.getFrame().addMouseListener(mouseInput);
		display.getFrame().addMouseMotionListener(mouseInput);
		display.getCanvas().addMouseListener(mouseInput);
		display.getCanvas().addMouseMotionListener(mouseInput);
		Asset.init();
		manager = new Manager(this);
		
		gameState=new GameState(manager);
		scoreState=new ScoreBoardState(manager);
		State.setCurrentState(scoreState);
	}
	public void run() {
		
		init();
		
		int fps=70;
		double tpu=1000000000/fps;
		double d=0;
		long now;
		long last = System.nanoTime();	
		while(running)
		{
			now=System.nanoTime();
			d+= (now-last)/tpu;
			last=now;
			if(d>=1) {
				update();
				render();
				d=0;
			}	
		}
		stop();
	}
	double xa=1,ya=1;
	private void update() {
		
		keybordInput.update();
		if(State.getCurrentState()!=null)
		{
			State.getCurrentState().update();
		}
	}
	private void render() {
		bs =display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
		return; }
		g=bs.getDrawGraphics();
		g.clearRect(0,0,height,width);
		
		
		
		//Drawing starts
		
		
		if(State.getCurrentState()!=null)
		{
			State.getCurrentState().render(g);
		}
		
		///draw end
		bs.show();
		g.dispose();
		
	}	
	
	

	public synchronized void start()
	{
		if(running)
			return;
		running =true;
		thread=new Thread(this);
		thread.start();
	}
	public synchronized void stop()
	{
		if(!running)
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	public KeybordInput getKeybordInput() {
		return keybordInput;
	}
	public MouseInput getMouseInput() {
		return mouseInput;
	}
}
