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
	
	private JLabel tipLabel=new JLabel("�������ﻶӭ�����Ի����¥�������");
	private JButton memberButton=new JButton("��Ա��¼");
	private JButton managerButton=new JButton("����Ա��¼");
	private JButton exitButton=new JButton("�˳�ϵͳ");

	//���캯������ʼ������
	public FirstMenuCB(){
		super("�˵�����",Color.PINK);
		//��ʼ������
		MySQLUtil.addAll_ClanBoom(util);
		/*util.initAdmin();
		util.initCompart();
		util.initFood();
		util.initMem();*/
		//��ť��ɫ
		memberButton.setBackground(Color.white);
		managerButton.setBackground(Color.white);
		exitButton.setBackground(Color.white);
		//������
		addComponent(tipLabel, gbc, 0, 0, 10, 1);
		addComponent(memberButton, gbc, 7, 1, 3, 1);
		addComponent(managerButton, gbc, 7, 2, 3, 1);
		addComponent(exitButton, gbc, 7, 3, 3, 1);
		//��ť����
		memberButton.addActionListener(this);
		managerButton.addActionListener(this);
		exitButton.addActionListener(this);
		setVisible(true);
	}
	
	//��ť����
	public void actionPerformed(ActionEvent e) {
		String source=e.getActionCommand();
		if(source.equals("��Ա��¼")){
			new MemLoginHandleCB(this,util);
			setVisible(false);
		}else if(source.equals("����Ա��¼")){
			new MgrLogin(this,util);
			setVisible(false);
		}else if(source.equals("�˳�ϵͳ")){
			dispose();
		}
	};
	
	public static void main(String[] args) {
		InitGlobalFont(new Font("��Բ", Font.PLAIN, 17));  //ͳһ��������
		new FirstMenuCB();
	}

	

	
	
		
	/** 
	* ͳһ�������壬����������֮�������ɸ����������ӽ��涼����Ҫ�ٴ��������� 
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
	
