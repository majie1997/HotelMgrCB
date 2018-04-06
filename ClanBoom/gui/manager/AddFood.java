package ClanBoom.gui.manager;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.FoodCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddFood extends JFrame implements ActionListener{
	private JButton ok,cancel;
	private JTextField name,money;
	public AddFood(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("确认");
		ok.setForeground(Color.black);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int num=util.Foodmap.get(util.Foodmap.size()-1).getNum()+1;
				double m=Integer.parseInt(money.getText());
				String n=name.getText();
				FoodCB foodCB=new FoodCB(num,n,m);
				util.Foodmap.add(foodCB);
				MySQLUtil.addFood_ClanBoom(foodCB);
				dispose();
				new AddOk(fs,util);
				
			}
		});
		//util.initFood();
		
		cancel=new JButton("取消");
		cancel.setForeground(Color.black);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MgrMenu(fs,util);
			}
		});
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);
		
		JLabel lead=new JLabel("请输入新增菜品名：");
		JLabel lead2=new JLabel("请输入新增菜品价格：");
		name=new JTextField(10);
		money=new JTextField(10);
		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		this.add(lead, constraints, 0,0,4,1);
		this.add(name,constraints,0,1,4,1);
		this.add(lead2, constraints,0,2,4,1);
		this.add(money,constraints,0,3,4,1);
	
		this.add(panel,constraints, 0,6,4,1);
		this.setTitle("增加菜品");
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
//		new AddFood(new  ManUtil());
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}