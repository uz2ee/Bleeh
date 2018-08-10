package com.bleeh.assets;

import java.awt.image.BufferedImage;

import com.bleeh.gfx.ImageLoader;

public class Asset  {
	public static BufferedImage playership1,playership2,
								sheild1,sheild2,sheild3,
								enamya,enamyb,enamyc,enamyd,enamye,enamyf,enamyg,enamyh,
								life1,life2,
								boss1,boss2,
								bullet1,leaser1,hit1a,hit1b,hit1c,
								bullet2,leaser2,hit2a,hit2b,hit2c,
								bullet3,
								
								leaderboard,gameplay,icon,loading,login,start,upgrade;
	
	public static BufferedImage[] medel=new BufferedImage[5];
	public static BufferedImage[] starts=new BufferedImage[2];
	public static BufferedImage[] leave=new BufferedImage[2];
	public static BufferedImage[] boss=new BufferedImage[2];
								
	
	public static void init() {
		
		playership1= ImageLoader.loadImage("/textures/p1.png");
		playership2= ImageLoader.loadImage("/textures/p2.png");
		life1=ImageLoader.loadImage("/textures/life1.png");
		starts[0]=ImageLoader.loadImage("/textures/icon.png");
		starts[1]=ImageLoader.loadImage("/textures/icon.png");
	leave[0]=ImageLoader.loadImage("/textures/icon1.png");
	   leave[1]=ImageLoader.loadImage("/textures/icon1.png");
		
		boss[0]= ImageLoader.loadImage("/textures/boss1.png");
		boss[1]= ImageLoader.loadImage("/textures/boss2.png");
		
		sheild1= ImageLoader.loadImage("/textures/shield1.png");
		sheild2= ImageLoader.loadImage("/textures/shield2.png");
		sheild3= ImageLoader.loadImage("/textures/shield3.png");

		enamya= ImageLoader.loadImage("/textures/ea.png");
		enamyb= ImageLoader.loadImage("/textures/eb.png");
		enamyc= ImageLoader.loadImage("/textures/ec.png");
		enamyd= ImageLoader.loadImage("/textures/ed.png");
		enamye= ImageLoader.loadImage("/textures/ee.png");
		enamyf= ImageLoader.loadImage("/textures/ef.png");
		enamyg= ImageLoader.loadImage("/textures/eg.png");
		enamyh= ImageLoader.loadImage("/textures/eh.png");
		
		
		bullet1= ImageLoader.loadImage("/textures/fire01.png");
		bullet2= ImageLoader.loadImage("/textures/fire02.png");
		bullet3= ImageLoader.loadImage("/textures/fire03.png");
		
		life1= ImageLoader.loadImage("/textures/life1.png");
		life2= ImageLoader.loadImage("/textures/life2.png");
		
		
		start = ImageLoader.loadImage("/textures/start.jpg");
		upgrade = ImageLoader.loadImage("/textures/upgrade.jpg");
		loading = ImageLoader.loadImage("/textures/loading.jpg");
		login = ImageLoader.loadImage("/textures/login.jpg");
		leaderboard = ImageLoader.loadImage("/textures/leaderBoard.jpg");
		gameplay = ImageLoader.loadImage("/textures/gameplay.jpg");
		icon= ImageLoader.loadImage("/textures/icon.png");
		
		leaser1=ImageLoader.loadImage("/textures/lh.png");
		hit1a=ImageLoader.loadImage("/textures/lh1.png");
		hit1b=ImageLoader.loadImage("/textures/lh2.png");
		hit1c=ImageLoader.loadImage("/textures/lh3.png");
		
		leaser2=ImageLoader.loadImage("/textures/lg.png");
		hit2a=ImageLoader.loadImage("/textures/lg1.png");
		hit2b=ImageLoader.loadImage("/textures/lg2.png");
		hit2c=ImageLoader.loadImage("/textures/lg3.png");
	}
	
	
}
