package com.bleeh.state;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

import com.bleeh.assets.Asset;
import com.bleeh.database.Database;
import com.bleeh.entity.Objects.Boss;
import com.bleeh.entity.Objects.Bullet;
import com.bleeh.entity.Objects.Enamy;
import com.bleeh.entity.Objects.Player;
import com.bleeh.launcher.Manager;
import com.bleeh.ui.Click;
import com.bleeh.ui.UIImageButton;
import com.bleeh.ui.UIManager;

public class GameState extends State{

	Player player;
	public static ArrayList<Bullet> bullet;
	public ArrayList<Enamy> enamy;
	Boss boss,boss1;
	
	
	private double d=0;
	int fps=70;
	double tpu=1000000000/fps;
	long now;
	long last ;
	int freq=100;
	private int score=0;
	private boolean normal=true,bos=false;
	
	//private boolean normal=false,bos=true;
	
	
	private int home=3;
	private int lb=2;
	private int ext=1;
	private int gm=0;
	private int con=4;
	private int paus=5;
	private int count=1;
	private Database db;
	private int end= home;
	public JButton exitButton;
	private int level=1;
	public GameState(Manager manager) {
		super(manager);
		player = new Player(manager,100,325);
		player.setPlayerSpeed(4);	
		bullet= new ArrayList<Bullet>();
		enamy= new ArrayList<Enamy>();
		boss=new Boss(Asset.boss[1],200,0,200);
		boss1=new Boss(Asset.boss[1],400,2,200);
		db= new Database();
	}
	public void update() {	
		if(end==gm)
		{
		player.update();
		
		
		for(int i=0;i<bullet.size();i++)
		{
			bullet.get(i).update();
		}
		
		
		if(normal)
		{
			for(int i=0;i<level;i++)
			{
		
				now=System.nanoTime();
				d+= (now-last)/tpu;
				last=now;
				if(d>freq) 
				{
				Random rand = new Random();
				int ranX=  rand.nextInt(700);
				enamy.add(new Enamy(Asset.enamyc,ranX,2));
				ranX=  rand.nextInt(700);
				enamy.add(new Enamy(Asset.enamyf,ranX,4));
				ranX=  rand.nextInt(700);
				enamy.add(new Enamy(Asset.enamyh,ranX,12));
				d=0;
			}
			}
			if(score>500 && count==1)
			{
				bos=true;
				normal=false;
				count =3;
				boss.setHealth(200);
			}
			
			if(score>800 && count==3)
			{
				bos=true;
				normal=false;
				count=4;
				boss.setHealth(400);
				boss.setSpeed(2);
				}
		}
		if(bos)
		{
			
			boss.update();
			if(count==2)
			{
				boss1.update();
			}
			
			if(boss.getHealth()<3)
			{
				bos=false;
				normal=true;
			}
			
			
		}
		
		
		
		}
		
		
		
		if(score>300 && score < 500)
		{
		player.setPower(8);
		freq=50;
		}
		if(score>500 && score < 1000)
		{
		player.setPower(5);
		}
		if(score>1000 )
		{
			freq=40;
		player.setPower(4);
		}
		if(score>1300 )
		{
			freq=35;
		player.setPower(3);
		}
		
		
		
		// Enamies
		
		for(int i=0;i<enamy.size();i++)
		{
			enamy.get(i).update();
		}
		System.out.println(manager.getMouseInput().getMx()+" "+ manager.getMouseInput().getMy());
	}
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		if(end==0) {
		
		player.render(g);
		
		
		if(bos) {
			if(count==2)
			{
				boss1.render(g);
				
				
				
				int ex= boss1.getX();
				int ey= boss1.getY();
				
				int px= player.getX();
				int py=player.getY();
				
				if(px < ex + 150 && px + 85 > ex && py < ey+ 150 && py+90 > ey )
				{
					
					
					g.drawImage(Asset.sheild3,px-22,py -22 ,null);
					//g.drawImage(Asset.sheild3,px,py,null);
					if(player.getHealth()>0 )
					player.setHealth(player.getHealth()-1);
					else
					{
						end=ext;
					}
				}
				
			}
		boss.render(g);
		
		
		int ex= boss.getX();
		int ey= boss.getY();
		
		int px= player.getX();
		int py=player.getY();
		
		if(px < ex + 150 && px + 85 > ex && py < ey+ 150 && py+90 > ey )
		{
			
			
			g.drawImage(Asset.sheild3,px-22,py -22 ,null);
			//g.drawImage(Asset.sheild3,px,py,null);
			if(player.getHealth()>0 )
			player.setHealth(player.getHealth()-1);
			else
			{
				end=ext;
			}
		}
		
		}
		
		
		for(int i=0;i<bullet.size();i++)
		{
			bullet.get(i).render(g);
		}
		for(int i=0;i<enamy.size();i++)
		{
			if(enamy.get(i).getX()>0 && enamy.get(i).getY()<420  && enamy.get(i).getX()<720)
			enamy.get(i).render(g);
		}
		
		for(int i=0;i<bullet.size();i++)
		{
			if(bullet.get(i).getY()<20)
			{
				bullet.remove(i);
				i--;
			}
		}
		
		for(int i=0;i<enamy.size();i++)
		{
			
			if(enamy.get(i).getY()>410)
			{
				score-=10;
				enamy.remove(i);
				i--;
			}
		}
		
		
		
		if(bos) {
			
Font font1= new Font("Papyrus", Font.ITALIC,25);
			
			g.setColor(Color.RED);
			
			g.setFont(font1);
			g.drawString("Score "+score, 30, 40);
			g.drawString("Boss "+boss.getHealth(), 630, 50);
			
			if(player.getHealth()==3)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				g.drawImage(Asset.life1,85,60,null);
				g.drawImage(Asset.life1,105,60,null);
				
			}
			if(player.getHealth()==2)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				g.drawImage(Asset.life1,85,60,null);
			}
			if(player.getHealth()==1)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				
				
			}
			if(player.getHealth()==0)
			{
				g.drawImage(Asset.life1,45,60,null);
			
			} 
			
			
		for(int j=0;j<bullet.size();j++)
		{
			int bx=bullet.get(j).getX();
			int by=bullet.get(j).getY();
			
			int ex= boss.getX();
			int ey= boss.getY();
			int ex1= boss1.getX();
			int ey1= boss1.getY();
			
			if(ex<bx+10  && ex+180> bx && ey < by + 10 && ey + 180 > by )
			{
				if(boss.getHealth()>10)
				{
					score+=1;
					g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
					boss.setHealth(boss.getHealth()-3);
					bullet.remove(j);
					j--;
				}
				else
				{
					score+=1;
					g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
						bos=false;
						normal=true;
						
				}
			}
			
			if(ex1<bx+10  && ex1+180> bx && ey1 < by + 10 && ey1 + 180 > by  && count ==2)
			{
				if(boss1.getHealth()>10)
				{
					score+=1;
					g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
					boss1.setHealth(boss1.getHealth()-3);
					bullet.remove(j);
					j--;
				}
				else
				{
					score+=1;
					g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
						bos=false;
						normal=true;
				}
			}
			
		}
		}
		
		
		for(int i=0;i<enamy.size();i++)
		{
			int ex= enamy.get(i).getX();
			int ey= enamy.get(i).getY();
			
			int px= player.getX();
			int py=player.getY();
			
			if(px < ex + 50 && px + 100 > ex && py < ey+ 50 && py+100 > ey )
			{
				
				enamy.remove(i);
				g.drawImage(Asset.sheild3,px-22,py -22 ,null);
				//g.drawImage(Asset.sheild3,px,py,null);
				i--;
				if(player.getHealth()>0 )
				player.setHealth(player.getHealth()-1);
				else
				{
					db.insert(score);
					end=ext;
				}
			}
			
			
				
			
			for(int j=0;j<bullet.size();j++)
			{
				int bx=bullet.get(j).getX();
				int by=bullet.get(j).getY();
				
				if(ex<bx+10  && ex+50 > bx && ey < by + 10 && ey + 50 > by )
				{
					if(enamy.get(i).getHealth()>1)
					{
						score+=10;
						g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
						enamy.get(i).setHealth(enamy.get(i).getHealth()-3);
						bullet.remove(j);
						j--;
					}
					else
					{
						score+=10;
						g.drawImage(Asset.hit1b,bullet.get(j).getX(),bullet.get(j).getY(),null);
							enamy.remove(i);
							i--;
							bullet.remove(j);
							j--;
					}
				}
				
			}
			
			
			Font font1= new Font("Papyrus", Font.ITALIC,25);
			
			g.setColor(Color.GREEN);
			
			g.setFont(font1);
			g.drawString("Score "+score, 30, 40);
			if(player.getHealth()==3)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				g.drawImage(Asset.life1,85,60,null);
				g.drawImage(Asset.life1,105,60,null);
				
			}
			if(player.getHealth()==2)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				g.drawImage(Asset.life1,85,60,null);
			}
			if(player.getHealth()==1)
			{
				g.drawImage(Asset.life1,45,60,null);
				g.drawImage(Asset.life1,65,60,null);
				
				
			}
			if(player.getHealth()==0)
			{
				g.drawImage(Asset.life1,45,60,null);
			
			}
			
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
				
				
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
			if(manager.getKeybordInput().pause)
			{
				end=paus;
			}
			
			
		}
		}
		else if(end==ext)
		{
			g.drawImage(Asset.loading,0,0,null);
			
			
			
			Font font1= new Font("Papyrus", Font.ITALIC,27);
			
			g.setColor(Color.green);
			
			g.setFont(font1);
			g.drawString("Your score : "+ score, 300, 280);
			g.drawString("Press S to play again !! ",300,320);
			g.setColor(Color.red);
			g.drawString("Press E to exit mission  ... :( ",100,360);
			
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
				
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				count=1;
				freq=100;
			}
			
			
		}
		else if(end ==lb )
		{
			g.drawImage(Asset.leaderboard,0,0,null);

			g.drawImage(Asset.upgrade,0,0,null);
			g.setColor(Color.white);
			Font font1= new Font("Papyrus", Font.ITALIC,29);
			
			g.setFont(font1);
			
			ResultSet rs = db.scores();
			
			int yy=100;
			
			try {
				
				while(rs.next() && yy<350)
				{
					g.drawString("Date"+rs.getString("Date")+ "   Score  "+rs.getString("Score"), 170,yy+=30);
				}
			} catch (SQLException e) {
				end=con;
			}
			
			db.con();
			
			
			g.setColor(Color.RED);
			
			
			g.drawString("Leaderboard", 300, 50);
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
		}
		else if( end ==home )
		{
			g.drawImage(Asset.loading, 0, 0, null);
			
			// Story of space 
			
			g.drawImage(Asset.upgrade,0,0,null);
			Font font1= new Font("Papyrus", Font.ITALIC,27);
			
			// options 
			int posx=100;
			int pos=270;
			g.setColor(Color.BLACK);
			boss.setY(30);
			
			g.setFont(font1);
			g.drawString("Press S to start your journey!", 180, 40);
			g.setColor(Color.YELLOW); 
			g.drawString("CONTROLS", 270,90);
			
			
			
			g.drawString("R         Restart ", pos,posx+=30);
			g.drawString("P         Pause  ", pos,posx+=30);
			g.drawString("Q         Resume ", pos, posx+=30);
			g.drawString("E         Quit ", pos, posx+=30);
			g.drawString("T         Reset ", pos, posx+=30);
			g.drawString("L         Leaderbord ",pos, posx+=30);
			g.drawString("O         Controls ", pos, posx+=30);
			g.drawString(">         Left ", pos, posx+=30);
			g.drawString("<         Right ", pos, posx+=30);
			g.drawString("Space     Fire ",pos, posx+=30);
			
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{end=gm;
			score=0;
			enamy.removeAll(enamy);
			bullet.removeAll(bullet);
			player.setHealth(3);
			boss.setY(0);
			normal=true;
			bos=false;
			count=1;
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
			
		
		}
		else if(end==con)
		{
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
				count=1;
			}
			
			
			g.drawImage(Asset.loading, 0, 0, null);
			
			
			g.drawImage(Asset.upgrade,0,0,null);
			Font font1= new Font("Papyrus", Font.ITALIC,27);
			
		
			int posx=60;
			int pos=270;
			g.setColor(Color.RED);
			
			g.setFont(font1);
		
			g.setColor(Color.white); 
			g.drawString("CONTROLS", 270,50);
			
			
			g.drawString("S         Start ", pos, posx+=30);
			g.drawString("R         Restart ", pos,posx+=30);
			g.drawString("P         Pause  ", pos,posx+=30);
			g.drawString("Q         Resume  ", pos,posx+=30);
			
			g.drawString("E         Quit ", pos, posx+=30);
			g.drawString("T         Reset ", pos, posx+=30);
			g.drawString("L         Leaderbord ",pos, posx+=30);
			g.drawString("O         Controls ", pos, posx+=30);
			

			g.drawString(">         Left ", pos, posx+=30);
			g.drawString("<         Right ", pos, posx+=30);
			g.drawString("Space     Fire ",pos, posx+=30);
		}
		else if(end==paus)
		{
			g.drawImage(Asset.login,0,0,null);
			
			Font font1= new Font("Papyrus", Font.ITALIC,37);
			g.setFont(font1);
			g.setColor(Color.RED);
			g.drawString("Paused ! ", 350, 200);
			g.drawString("In auto pilot mode ... risky !", 320, 220);
			
			g.drawString("Press Q to get back.", 320, 240);
			
			if(manager.getKeybordInput().leaderBord)
			{
				end=lb;
			}
			if(manager.getKeybordInput().exit)
			{
				System.exit(0);
			}
			if(manager.getKeybordInput().option)
			{
				end=con;
			}
			if(manager.getKeybordInput().reset)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
			}
			if(manager.getKeybordInput().restart)
			{
				end=gm;
				score=0;
				enamy.removeAll(enamy);
				bullet.removeAll(bullet);
				player.setHealth(3);
				boss.setY(0);
				normal=true;
				bos=false;
				freq=100;
			}
			if(manager.getKeybordInput().resume)
			{
				end=gm;
			}
		}
	}

}
