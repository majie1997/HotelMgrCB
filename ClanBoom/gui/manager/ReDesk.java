package ClanBoom.gui.manager;
import javax.naming.ldap.UnsolicitedNotificationListener;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReDesk extends JFrame{
	private JButton ok,cancel;
	private JTextField name;
	public ReDesk(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("确认");
		ok.setForeground(Color.black);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(util.DeskPd(name.getText())==0){
					util.DeskClear(name.getText());
					dispose();
					new ReDeskOk(fs, util);
				}if(util.DeskPd(name.getText())==2){
					setTitle("输入的餐桌号有误");
				}if(util.DeskPd(name.getText())==1){
					setTitle("该餐桌未被占用");
				}
			}
		});
		
		cancel=new JButton("取消");
		cancel.setForeground(Color.black);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					dispose();
					new MgrMenu(fs,util);
			}
		});
		
			
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);
		
		JLabel lead=new JLabel("请输入用餐完毕的餐桌号");
		//util.initFood();
		name=new JTextField(10);

		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		this.add(lead, constraints, 0,0,4,1);
		this.add(name,constraints,0,1,4,1);

	
		this.add(panel,constraints, 0,2,4,1);
		this.setTitle("用餐完毕");
		this.getContentPane().setBackground(Color.yellow);
		this.setBounds(500, 300, 300, 250);
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
//		new DelFood(new  ManUtil());
//	}

}