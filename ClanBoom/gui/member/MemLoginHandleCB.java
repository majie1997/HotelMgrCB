package ClanBoom.gui.member;


import java.awt.Color;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

  
import javax.swing.JButton;

import javax.swing.JLabel;  
import javax.swing.JPasswordField;  
import javax.swing.JTextField;


import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.WindowCB;
import ClanBoom.utils.ManUtil;


public class MemLoginHandleCB extends WindowCB implements ActionListener {

	private static final int width=450;
	private static final int height=300;
	
	private JLabel tipLabel=new JLabel("◇○●会员登录界面 ●○◇");
	private JButton backButton=new JButton("返回");
	private JLabel userLabel=new JLabel("用户名");
	private JLabel pswLabel=new JLabel("密码");
	private JTextField userText=new JTextField(10);
	private JPasswordField pswText=new JPasswordField(10);	
	private JButton submitButton=new JButton("登录");
	private JButton resetButton=new JButton("重置");
	
	/**
	 * 主函数
	 * @param args
	 */
//	public static void main(String[] args){
//		new MemLoginHandleCB(new FirstMenuCB(), new ManUtil());
//	}
	
	
	public MemLoginHandleCB(FirstMenuCB fm,ManUtil util){
		super("登录",Color.PINK);
		//按钮颜色
		backButton.setBackground(Color.white);
		//设置密码隐藏
		pswText.setEchoChar('*');
		//添加组件
		addComponent(tipLabel, gbc, 0, 0, 10, 1);
		addComponent(userLabel, gbc, 0, 1, 1, 1);
		addComponent(pswLabel, gbc, 0, 2, 1, 1);
		addComponent(userText, gbc, 1, 1, 0, 1);
		addComponent(pswText, gbc, 1, 2, 0, 1 );
		addComponent(submitButton, gbc, 0, 3, 1, 1);
		addComponent(resetButton, gbc, 3, 3, 1, 1);
		addComponent(backButton, gbc, 4, 4, 1, 1);
		//按钮监听
		//返回按钮
		backButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
							dispose();
							fm.setVisible(true);
						
					}
				} );
		//登录按钮
		submitButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()== submitButton){
							String userName=userText.getText();//得到用户名
							String passWord=pswText.getText();//得到密码
							boolean right=util.isExistCard(userName,passWord);//判断用户名和密码是否匹配
							if(right){
								dispose();
								new MemberMenu(fm,util,userName);
							}else{
								tipLabel.setText("用户名或密码错误！");
							}
						}
					}
				});
		//重置按钮
		resetButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
							userText.setText("");
							pswText.setText("");
					}
				});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	//按钮监听
	/*private class backButtonActionListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}*/
}
