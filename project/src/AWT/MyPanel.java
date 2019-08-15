package AWT;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.event.NamespaceChangeListener;
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


//import AWT.ChangePanel.TimeThread;

public class MyPanel extends JPanel implements ActionListener{
	private int x,y;
	private String rank;
	
	public int a0 =0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0;
	public ImageIcon openedIcon; 
	public static JLabel timeJLabel;// = new JLabel();
	public javax.swing.Timer t;
	public TimeThread timThread;
	public MusicPlayer musicPlayer;
	
	//TimeThread timeThread  =new TimeThread();
	Block[] blocks;// = new Block[x*y];
	Block blockTemp,blockClean;

 	
 	
	int rand;
	int tip = 0;//点击次数
	int success=0;
	int start = 0;
	String presentImgString;
	String lastImaString;
	
	Boolean timerIsStart = false;
	
	LinkedList<Block> blockList = new LinkedList<Block>();
	LinkedList<String> iconList = new LinkedList<String>();
	
	public MyPanel(String rank, int x,int y,String img[]){
		setLayout(new GridLayout(x,y));
		setVisible(true);
		

	
		
		//t = new javax.swing.Timer(1000, this);
		///t.start();
		timeJLabel = new JLabel();
		timeJLabel.setText("用时");
		
		timThread =new TimeThread();
		timThread.start();
		
		this.rank = rank;
		this.x = x;
		this.y = y;
		blocks = new Block[x*y];
		for (int i = 0; i < x*y; i++) {
			do {
				rand = createRandom(x);
			
			}while(randomNumberIsNotOK(rand,y));
			System.out.println(rand);
			
			
			blocks[i] = new Block(img[rand],i);
			
			
			this.add(blocks[i]);
			
			
		}
		
		
		for(int i=0;i<x*y;i++){
			
			blocks[i].addActionListener(this);
	
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		

		// TODO Auto-generated method stub
	   if(e.getSource() instanceof Block) {
		   
		   
		   
		   tip++;
		   musicPlayer = new MusicPlayer("./Music/ly.mp3");
		   musicPlayer.start();
		   blockTemp = (Block)e.getSource();
		   blockTemp.setOpenedIcon();
		   System.out.println("设置打开图片");
		   
		   presentImgString = blockTemp.getOpenedIcon();
		   if(blockList.size()==0) {
			   
			   //iconList.add(presentImgString);
			   System.out.println(blockTemp.num);
			   blockList.add(blockTemp);
			   success++;
			   
			   for(int i = 0;i<x*y;i++) {
				   //blockClean =blockList.get(i);
				   if(blockTemp!=blocks[i]) {
					   blocks[i].setIcon(null);
					   System.out.println(blocks[i].num+"关闭");
				   }
				   
			   }
			   
		   }else {
			   Block lastBlock = blockList.getLast();
			   
			   
			   //lastImaString = iconList.getLast();
			   //iconList.add(presentImgString);
			   lastImaString = lastBlock.getOpenedIcon();
			   if(presentImgString.equals(lastImaString)&&!blockList.contains(blockTemp)) {
				   blockList.add(blockTemp);
				  //iconList.add(presentImgString);
				   success++;
				   System.out.println(blockTemp.num);
				   if(success==y) {
					   System.out.println("点击次数"+tip);
					   //t.stop();
					   
					   timThread.stop();
					   musicPlayer = new MusicPlayer("./Music/ding.mp3");
					   musicPlayer.start();
					   JOptionPane.showMessageDialog(null, "恭喜您，您已完成游戏!您用时："+start+"秒 点击次数："+tip+"次");
					   
					try {
						new Record(rank, tip,start).fileWrite();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					   
//					   
//					   
				   }
				   
			   }else {
				   System.out.println("关闭111");
				   blockList.clear();
				   success=0;
				   for(int i = 0;i<x*y;i++) {
					   //blockClean =blockList.get(i);
					   if(blockTemp!=blocks[i]) {
						   blocks[i].setIcon(null);
						   System.out.println(blocks[i].num+"关闭");
					   }
					   
				   }
			   }
			
			
		}
		   
	   }
		

	   
	}
	
	public void showTips() throws InterruptedException {
		for(int i=0;i<blocks.length;i++) {
			blocks[i].setOpenedIcon();
		}
		//timeThread.sleep(500);
	
		start+=10;
		java.util.Timer timer=new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){   
			public void run(){   
				for(int i=0;i<blocks.length;i++) {
					if(!blockList.contains(blocks[i])) {
						blocks[i].setIcon(null);
					}
					
					
				}	
				this.cancel();}},800);//0.8秒后执行 
	
	}
	
	private int createRandom(int x) {
		// TODO Auto-generated method stub
		Random r = new Random();
		return r.nextInt(x);
	}
	private boolean randomNumberIsNotOK(int number,int y) {
		
		// TODO Auto-generated method stub
		switch (number) {
		case 0:{
			a0++;
			if(a0>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 1:{
			a1++;
			if(a1>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 2:{
			a2++;
			if(a2>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 3:{
			a3++;
			if(a3>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 4:{
			a4++;
			if(a4>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 5:{
			a5++;
			if(a5>y) {
				return true;
			}else {
				return false;
			}
		
		}
		case 6:{
			a6++;
			if(a6>y) {
				return true;
			}else {
				return false;
			}
		
		}
		default:
			return true;
		}
	}

	
	class TimeThread extends Thread{

		   
        public boolean stopped = true;  
   
        @Override  
        public void run() {  
            while (true) {  
            	
            	start++;
            	timeJLabel.setText("您的用时 " + start + " 秒");
                try {  
                    sleep(1000);  // 1秒更新一次显示
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                    System.exit(1);  
                }  
            }  
        }  
   
     
	} 
	
	class MusicPlayer extends Thread {
		
		//FileInputStream fileau = new FileInputStream("Music/cnwav.aac");
		
		//AudioStream as = new AudioStream(fileau);
		
		Player player;
		File musicfile;
		public MusicPlayer(String path) {
			// TODO Auto-generated constructor stub
			musicfile = new File(path);
		}
		
		public void run() {
			
			BufferedInputStream buffer = null;
			try {
				buffer = new BufferedInputStream(new FileInputStream(musicfile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	         try {
				player = new Player(buffer);
				player.play();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       

		}
	}

}
