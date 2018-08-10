package com.bleeh.launcher;

import com.bleeh.input.KeybordInput;
import com.bleeh.input.MouseInput;

public class Manager {
	
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Manager(Game game) {
	this.game = game;
	}
	public KeybordInput getKeybordInput()
	{
		return game.getKeybordInput();
	}
	public int getHeight()
	{
		return game.getHeight();
	}
	public MouseInput getMouseInput()
	{
		return game.getMouseInput();
	}
}
