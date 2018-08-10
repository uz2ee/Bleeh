package com.bleeh.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bleeh.assets.Asset;
import com.bleeh.launcher.Manager;
import com.bleeh.ui.Click;
import com.bleeh.ui.UIImageButton;
import com.bleeh.ui.UIManager;

public class ScoreBoardState extends State {
	private UIManager  uiManager;
	public ScoreBoardState(Manager manager)
	{
		super(manager);
		
		
		uiManager=new UIManager(manager);
		manager.getMouseInput().setUiManager(uiManager);
		
		uiManager.addObject(new UIImageButton(620,175,90,90,Asset.starts,new Click() {
			public void clickOn() {
				manager.getMouseInput().setUiManager(null);
				System.out.println("Clicker in menu");
			State.setCurrentState(manager.getGame().gameState);
			}}));
		uiManager.addObject(new UIImageButton(620,275,90,90,Asset.leave,new Click() {
			public void clickOn() {		
				manager.getMouseInput().setUiManager(null);
			    System.exit(0);
			}}));
	}
	public void update() {
		uiManager.update();
	}
	public void render(Graphics g) {
		
		
		Font font= new Font("Papyrus", Font.BOLD,13);
		
		// options 
		int posx=100;
		
		g.drawImage(Asset.start,0,0,null);
		g.setFont(font);
		g.setColor(new Color(0,204,204));
		g.drawImage(Asset.starts[0],620,175,90,90,null);
		g.drawString("Start", 643, 255);
		
		g.setColor(new Color(240,67,24));
		g.drawImage(Asset.leave[0],620,275,90,90,null);
		g.drawString("Quit", 647, 290);
		
		
	//	while()
	//	uiManager.render(g);
		
	}

}
