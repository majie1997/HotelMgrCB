package ClanBoom.gui.manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.*;

public class MgrLogin extends JFrame implements ActionListener{
	private JButton ok,cancel;
	private JTextField name,password;
	public MgrLogin(FirstMenuCB fs,ManUtil util){
		
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("登录");
		ok.setForeground(Color.black);
		//登录按钮
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		//		util.initAdmin();
				if(util.isExistAdmin(name.getText(),password.getText())){
					new  MgrMenu(fs,util);
					dispose();
				}else{
					setTitle("账号或密码有误!!!");
					name.setText("");
					password.setText("");
				}
				
				
			}
		});
		
		

			
		cancel=new JButton("取消");
		cancel.setForeground(Color.black);  //取消按钮
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fs.setVisible(true);
				dispose();
						}
		});
		
		cancel.addActionListener(this);//注册监听器
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);  //按钮界面
		
		JLabel username=new JLabel("账号"); //账号
		JLabel userpassword=new JLabel("密码"); //密码
		
		name=new JTextField(10);  //账号输入框
		password=new JTextField(10);  //密码输入框
			
		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		/*加按钮和输入框*/
		this.add(username,constraints,0,0,1,1);
		this.add(name, constraints,1,0,3,1);
		this.add(userpassword,constraints,0,1,1,1);
		this.add(password, constraints, 1,1,3,1);
		this.add(panel,constraints, 0,2,4,1);
		this.setTitle("管理员登录");
		this.getContentPane().setBackground(Color.yellow);
		this.setBounds(500, 300, 300, 150);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	
	
	
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=w;
		constraints.gridheight=h;
		add(c,constraints);
	}
//	public static void main(String[] args){
//		new MgrLogin(new ManUtil());
//	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
