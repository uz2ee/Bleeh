package com.bleeh.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.bleeh.ui.UIManager;

public class MouseInput implements MouseListener,MouseMotionListener,ActionListener {

	private int mx,my;
	private UIManager uiManager;
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	public MouseInput() {
	}
	public int getMx() {
		return mx;
	}
	public int getMy() {
		return my;
	}
	public void mouseClicked(MouseEvent e) {	
		System.out.println("Pressed");
		
	}
	public void mouseEntered(MouseEvent e) {	
	}

	public void mouseExited(MouseEvent e) {	
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		if(uiManager!=null)
		{
			uiManager.onMouseRelease(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
	
	}


	public void mouseMoved(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
		if(uiManager!=null)
		{
			uiManager.onMouseMove(e);
		}
	}

	


}
