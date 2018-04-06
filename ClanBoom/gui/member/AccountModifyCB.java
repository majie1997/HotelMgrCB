package ClanBoom.gui.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementPermission;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.WindowCB;
import ClanBoom.utils.ManUtil;

public class AccountModifyCB extends JFrame implements ActionListener {

	private static final int width=450;
	private static final int height=300;
	
	private JTextField pwdText=new JTextField(10);//修改密码
	private JLabel pwdLabel=new JLabel("请输入你新的密码：");
	private JTextField birthText=new JTextField(10);//修改生日
	private JLabel birthLabel=new JLabel("请输入您的生日：(MM-DD)");
	
//	public static void main(String[] args) {
//		new AccountModifyCB(new FirstMenuCB(),new ManUtil(),"111111");
//	}
	
	public AccountModifyCB(FirstMenuCB fs,ManUtil util,String userName){
	//	util.initMem();
		//添加修改功能的组件
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		jp1.add(pwdLabel);
		jp1.add(pwdText);
		jp2.add(birthLabel);
		jp2.add(birthText);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.CENTER);
		//添加按钮
		JButton modifyBt=new JButton("确认修改");
		JButton backBt=new JButton("返回主菜单");
		JPanel jp3=new JPanel();
		jp3.add(modifyBt);
		jp3.add(backBt);
		this.add(jp3, BorderLayout.SOUTH);
		//按钮监听
		//修改按钮
		modifyBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Word1=pwdText.getText();
				Word1.trim();
				util.Memmap.get(userName).setPassword(Word1);
				String Date=birthText.getText();
				util.Memmap.get(userName).setBirth(Date);
				//  add code here 2
				MySQLUtil.changeMember_ClanBoom(1, util.Memmap.get(userName));
				MySQLUtil.changeMember_ClanBoom(2, util.Memmap.get(userName));
				setTitle("修改成功！");
				new MemLoginHandleCB(fs, util);
				dispose();
			}
		});
		//返回按钮
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberMenu(fs, util, userName);
				dispose();
			}
		});
		
		//设置窗口参数
		setTitle("账户信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setSize(width,height);
		pack();
		setVisible(true);
		setResizable(false);
		this.getContentPane().setBackground(Color.pink);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
