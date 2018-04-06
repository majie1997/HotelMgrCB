package ClanBoom.gui.manager;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.MemberCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddMem extends JFrame implements ActionListener{
	private JButton ok,cancel;
	private JTextField name,password,birth,money;
	public AddMem(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("确认");
		ok.setForeground(Color.black);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String n=name.getText();
				String p=password.getText();
				int m=Integer.parseInt(money.getText());
				String b=birth.getText();
				String num=util.createNumber();
				
				MemberCB memberCB=new MemberCB(n,num,p,m,b);
				util.Memmap.put(num, memberCB);
				//add code here 1
				MySQLUtil.addMember_ClanBoom(memberCB);
				dispose();
				new AddOk(fs,util);
				
			}
		});
		
		cancel=new JButton("返回");
		cancel.setForeground(Color.black);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MgrMenu(fs,util);
				
			}
		});
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);
		
		JLabel lead=new JLabel("请输入要增加的会员信息：");
		JLabel username=new JLabel("账号");
		JLabel userpassword=new JLabel("密码");
		JLabel userbirth=new JLabel("生日");
		JLabel usermoney=new JLabel("充值金额");
		name=new JTextField(10);
		password=new JTextField(10);
		birth=new JTextField(10);
		money=new JTextField(10);
		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		this.add(lead, constraints, 0,0,4,1);
		this.add(username,constraints,0,1,1,1);
		this.add(name, constraints,1,1,4,1);
		this.add(userpassword,constraints,0,2,1,1);
		this.add(password, constraints, 1,2,4,1);
		this.add(userbirth, constraints, 0, 3, 1, 1);
		this.add(birth, constraints, 1, 3, 4, 1);
		this.add(usermoney, constraints, 0, 4, 1, 1);
		this.add(money, constraints, 1, 4,4 , 1);
		this.add(panel,constraints, 0,5,4,1);
		this.setTitle("增加会员");
		this.getContentPane().setBackground(Color.yellow);
		this.setBounds(400, 200, 400, 350);
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
//		new AddMem(new  ManUtil());
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}