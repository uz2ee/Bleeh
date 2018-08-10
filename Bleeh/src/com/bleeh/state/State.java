package com.bleeh.state;

import java.awt.Graphics;

import com.bleeh.launcher.Game;
import com.bleeh.launcher.Manager;

public abstract class State {
	protected Manager manager ;
	
	public State(Manager manager)
	{
		this.manager =manager;
	}
	public static State currentState = null;
	
	public static State getCurrentState() {
		return currentState;
	}
	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	
}
