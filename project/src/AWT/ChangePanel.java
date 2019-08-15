package AWT;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;





//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class ChangePanel extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu recordJMenu;
	JMenuItem easylevel;
	JMenuItem normallevel;	
	JMenuItem hardlevel;
	
	JMenuItem recordTable;
	
	JMenuItem normalStyle;
	JMenuItem childrenStyle;
	
	MyPanel mypanel = null;
	
	//JTextField tx;
	Button tipButton = new Button("提示");
	JPanel headJPanel = new JPanel();
	JPanel footJPanel = new JPanel();
	JPanel tableJLabel = new JPanel();
	JLabel tipJLabel;
	JLabel timerJLabel;
	JScrollPane scrollPane;
	//JLabel tableJLabel;
	
	public Long timeStart;
	
	public String[] imagePath = new String[7];
	public String[] norStrings = { "./image/a0.jpg", "./image/a1.jpg", "./image/a2.jpg", "./image/a3.jpg",
			"./image/a4.jpg","./image/a5.jpg", "./image/a6.jpg"};
	public String[] fruit = {
 			"./image/菠萝.png","./image/草莓.png","./image/橙子.png","./image/芒果.png","./image/苹果.png","./image/香蕉.png","./image/樱桃.png"
 	};
	
	private JTable table;
	private String[][] objTable = new String[30][4];
	private String[] asstant = new String[4];
	private String[] columnNames = { "游戏等级", "点击数", "用时", "得分"};
	
	LinkedList<String[]> recList = new LinkedList<String[]>(); 
	//JPanel p;
	//JPanel p2;
	//JPanel p3;
	int x,y;
	
	public ChangePanel(){
		JMenuBar bar=new JMenuBar();
		//玩家记录菜单
		recordJMenu = new JMenu("玩家记录");
		recordTable = new JMenuItem("记录表");
		recordJMenu.add(recordTable);
		
		bar.add(recordJMenu);
		recordTable.addActionListener(this);
		//更换主题菜单
		JMenu styleJMenu = new JMenu("更换主题");
		normalStyle = new JMenuItem("一般");
		childrenStyle = new JMenuItem("儿童");
		styleJMenu.add(normalStyle);
		styleJMenu.add(childrenStyle);
		bar.add(styleJMenu);
		normalStyle.addActionListener(this);
		childrenStyle.addActionListener(this);
	
		
		//选择难度菜单
		JMenu file=new JMenu("记忆测试");
		easylevel=new JMenuItem("初级");
		normallevel=new JMenuItem("中级");		
		hardlevel=new JMenuItem("高级");
		file.add(easylevel);
		file.add(normallevel);
		file.add(hardlevel);
		bar.add(file);
		easylevel.addActionListener(this);
		normallevel.addActionListener(this);
		hardlevel.addActionListener(this);
		
		imagePath = null;
		timerJLabel = new JLabel();
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
	     //Rectangle bounds = new Rectangle(screenSize); 
	     //jframe.setBounds(bounds); 
	     //jframe.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		tipButton.addActionListener(this);
		headJPanel.add(bar,BorderLayout.WEST);
		headJPanel.add(tipButton,BorderLayout.EAST);
		this.add(headJPanel,BorderLayout.NORTH);	
		
		this.setBounds(0,0,600,500);
		//设置窗口位于屏幕正中
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int width = (int)dim.getWidth();
		int height = (int)dim.getHeight();
		this.setLocation((width-600)/2, (height-500)/2);
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//=new JTextField("test");
		//tx.setEditable(false);
		//tx.setForeground(Color.red);
		this.add(footJPanel,BorderLayout.SOUTH);
		
		validate();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChangePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==recordTable) {
			if(mypanel!=null) {
				//mypanel.timThread.stop();
				System.out.println("执行第150放行");
				super.remove(mypanel);
				this.remove(mypanel);
			}

			if(tipJLabel!=null) {
				this.remove(footJPanel);
				footJPanel.remove(tipJLabel);
			}

			if(timerJLabel!=null) {
				this.remove(footJPanel);
				footJPanel.remove(timerJLabel);
			}
			
			System.out.println("11123");
			initArray();
			try {
				recList = new Record().fileRead();
				
				for(int i=0;i<recList.size();i++) {
					asstant = recList.get(i);
					objTable[i][0] = asstant[0];
					objTable[i][1] = asstant[1];
					objTable[i][2] = asstant[2];
					objTable[i][3] = asstant[3];
					System.out.println("2");
					System.out.println(asstant);
					System.out.println(objTable[i][3]);
					
				}
				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			table = new JTable(objTable, columnNames);
			
			table.setBounds(1, 40, 580, 400);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			scrollPane = new JScrollPane();
			scrollPane.setBounds(1, 44, 582, 407);
			scrollPane.setViewportView(table);
			//panel_0.add(scrollPane);
			
			
//			table.setVisible(true);
//			tableJLabel.add(table);
//			tableJLabel.setVisible(true);
			this.add(scrollPane,BorderLayout.CENTER);
		}
		
		
		if(e.getSource()==normalStyle){
			imagePath = norStrings;
		
		}
		if(e.getSource()==childrenStyle){
			imagePath = fruit;
		}
		
		
		if(e.getSource()==easylevel){
			
			if(scrollPane!=null) {
				this.remove(scrollPane);
			}
			if(imagePath==null) {
				JOptionPane.showMessageDialog(null, "请您先选择主题");	
			}else {
				System.out.println("easy level");
				timeStart = System.currentTimeMillis();  

				//TimeThread timThread =new TimeThread();
				//timThread.start();
			
				
				x=5;y=6;
				changePanel("easy level",x,y);
				
				changeLabel(1);
				this.revalidate();
			}
			
		}
		else if (e.getSource()==normallevel){
		
			if(scrollPane!=null) {
				this.remove(scrollPane);
			}
			if(imagePath==null) {
				JOptionPane.showMessageDialog(null, "请您先选择主题");	
			}else {
				System.out.println("normal level");
				timeStart = System.currentTimeMillis();  
				//TimeThread timThread =new TimeThread();
				//timThread.start();
				x=6;y=7;
				changePanel("normal level",x,y);
			
				changeLabel(2);
				this.revalidate();	
			}
				
		}
		else if (e.getSource()==hardlevel){
			if(scrollPane!=null) {
				this.remove(scrollPane);
			}
			if(imagePath==null) {
				JOptionPane.showMessageDialog(null, "请您先选择主题");	
			}else {
				System.out.println("hard level");
				timeStart = System.currentTimeMillis();  
				//TimeThread timThread =new TimeThread();
				//timThread.start();
				x=7;y=8;
				changePanel("hard level",x,y);
				//mypanel = new MyPanel(x, y) ;
				//initPanel(p1,x,y);
				//this.add(mypanel,BorderLayout.CENTER);
				changeLabel(3);
				this.revalidate();			
			}
			
				
		}
		
		
		if(e.getSource()==tipButton) {
			if(mypanel==null) {
				JOptionPane.showMessageDialog(null, "请您先选择难度类型");
			}else {
				try {
					mypanel.showTips();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		}
		
	}
	
//	public void setMyLocation() {
//		this.setSize(Constant.WIDTH, Constant.HEIIGHT);
//		Container c = this.getContentPane();
//		c.setBackground(new Color(240,255,255));//设置顶层容器的颜色
//		Toolkit tool = Toolkit.getDefaultToolkit();
//		Dimension dim = tool.getScreenSize();
//		int width = (int)dim.getWidth();
//		int height = (int)dim.getHeight();
//		this.setLocation((width-Constant.WIDTH)/2, (height-Constant.HEIIGHT)/2);
//	}//捕获屏幕大小，将界面置于屏幕正中
	
	
	
	/* 将jtable数组重新置空，这个方法有待优化 */
	public void initArray() {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 30; i++)
				objTable[i][j] = "";
		}
	}
	
	
//	/* 初始化Jtable的值 */
//	public void initTable(List adList) {
//		initArray();// 先把table数组置空
//		int i = 0;
//		adList.temp = adList.head;
//		adList.temp = adList.temp.nextGoods;
//		while (adList.temp != null) {
//			objTable[i][0] = adList.temp.numString;
//			objTable[i][1] = adList.temp.nameString;
//			objTable[i][2] = adList.temp.featuerString;
//			objTable[i][3] = adList.temp.timeString;
//			
//			adList.temp = adList.temp.nextGoods;
//			i++;
//		}
//	}

	
	
	void changePanel(String r,int x,int y) {
		if(mypanel!=null) {
			mypanel.timThread.stop();
			this.remove(mypanel);
		}
		mypanel = new MyPanel(r,x, y,imagePath);
		this.add(mypanel,BorderLayout.CENTER);
	}
	void changeLabel(int rank) {
		
		
		
		if(tipJLabel!=null) {
			footJPanel.remove(tipJLabel);
		}

		if(timerJLabel!=null) {
			footJPanel.remove(timerJLabel);
		}
		timerJLabel = mypanel.timeJLabel;
		footJPanel.add(timerJLabel,BorderLayout.NORTH);
		switch (rank) {
		case 1:
			tipJLabel = new JLabel("初级:您需要连续找出6个相同图标的方块");
			break;

		case 2:
			tipJLabel = new JLabel("中级:您需要连续找出7个相同图标的方块");
			break;
			
		case 3:
			tipJLabel = new JLabel("高级:您需要连续找出8个相同图标的方块");
			break;	
		default:
			//Dialog dialog =new Dialog(this);
			JOptionPane.showMessageDialog(null, "选择提示框出错");
			break;
		}
		tipJLabel.setForeground(Color.red);
	
		//this.add(tipJLabel,BorderLayout.NORTH);
		footJPanel.add(tipJLabel,BorderLayout.SOUTH);
	}

	


}

	





