package ClanBoom.gui.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

public class dishShowCB extends JFrame implements ActionListener {

	private static final int width=400;
	private static final int height=300;
	
	public dishShowCB(FirstMenuCB fs,ManUtil util,String userName,JTextArea jta){
		//显示菜单
		JScrollPane jsp=new JScrollPane(jta);
		add(jsp,BorderLayout.CENTER);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//添加返回按钮
		JButton jb=new JButton("返回会员菜单");
		JPanel jp=new JPanel();
		jp.add(jb);
		this.add(jp, BorderLayout.SOUTH);
		//按钮监听
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberMenu(fs, util, userName);
				dispose();
			}
		});
		
	    setTitle("预订成功！");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(width,height);
	    setVisible(true);
	    setBackground(Color.pink);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
