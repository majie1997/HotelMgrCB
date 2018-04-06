package ClanBoom.gui.base;


import javax.swing.plaf.FontUIResource;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.gui.manager.MgrLogin;
import ClanBoom.gui.member.MemLoginHandleCB;
import ClanBoom.utils.ManUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;


import java.awt.Font;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class FirstMenuCB extends WindowCB implements ActionListener {
	private ManUtil util=new ManUtil();
	private static final int width=450;
	private static final int height=300;
	
	private JLabel tipLabel=new JLabel("★☆★☆★☆★欢迎来到吃货大酒楼☆★☆★☆★");
	private JButton memberButton=new JButton("会员登录");
	private JButton managerButton=new JButton("管理员登录");
	private JButton exitButton=new JButton("退出系统");

	//构造函数，初始化界面
	public FirstMenuCB(){
		super("菜单界面",Color.PINK);
		//初始化参数
		MySQLUtil.addAll_ClanBoom(util);
		/*util.initAdmin();
		util.initCompart();
		util.initFood();
		util.initMem();*/
		//按钮颜色
		memberButton.setBackground(Color.white);
		managerButton.setBackground(Color.white);
		exitButton.setBackground(Color.white);
		//添加组件
		addComponent(tipLabel, gbc, 0, 0, 10, 1);
		addComponent(memberButton, gbc, 7, 1, 3, 1);
		addComponent(managerButton, gbc, 7, 2, 3, 1);
		addComponent(exitButton, gbc, 7, 3, 3, 1);
		//按钮监听
		memberButton.addActionListener(this);
		managerButton.addActionListener(this);
		exitButton.addActionListener(this);
		setVisible(true);
	}
	
	//按钮监听
	public void actionPerformed(ActionEvent e) {
		String source=e.getActionCommand();
		if(source.equals("会员登录")){
			new MemLoginHandleCB(this,util);
			setVisible(false);
		}else if(source.equals("管理员登录")){
			new MgrLogin(this,util);
			setVisible(false);
		}else if(source.equals("退出系统")){
			dispose();
		}
	};
	
	public static void main(String[] args) {
		InitGlobalFont(new Font("幼圆", Font.PLAIN, 17));  //统一设置字体
		new FirstMenuCB();
	}

	

	
	
		
	/** 
	* 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体 
	*/
	private static void InitGlobalFont(Font font) {  
		  FontUIResource fontRes = new FontUIResource(font);  
		  for (Enumeration<Object> keys = UIManager.getDefaults().keys();  
		  keys.hasMoreElements(); ) {  
			  Object key = keys.nextElement();  
			  Object value = UIManager.get(key);  
			  if (value instanceof FontUIResource) {  
				  UIManager.put(key, fontRes);  
			  }  
		  } 
	}
}
	
