package com.bleeh.dispaly;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private JFrame frame;
	private Canvas canvas;
	
	
	private String title;
	private int height,width;
	
	public Display(String title,int height ,int width) {
		this.title=title;
		this.height=height;
		this.width=width;
		createScreen();
	}
	
	
	private void createScreen() {
		frame= new JFrame(title);
		frame.setSize(height, width);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas=new Canvas();
		canvas.setPreferredSize(new Dimension(height,width));
		canvas.setMaximumSize(new Dimension(height,width));
		canvas.setMinimumSize(new Dimension(height,width));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}
	public Canvas getCanvas() {return canvas;}
	
	public JFrame getFrame(){
		return frame;
	}}
	

