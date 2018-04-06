package ClanBoom.gui.manager;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DelFood extends JFrame{
	private JButton ok,cancel;
	private JTextField name,money;
	public DelFood(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("确认");
		ok.setForeground(Color.black);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isEx=false;
				int n=Integer.parseInt(name.getText());
				for(int i=0;i<util.Foodmap.size();i++){
					if(util.Foodmap.get(i).getNum()==n){
						// add code here 1
						MySQLUtil.deleteFood_ClanBoom(util.Foodmap.get(i));
						util.Foodmap.remove(i);
						for(int q=i;q<util.Foodmap.size();q++)
						{
							util.Foodmap.get(q).setNum(util.Foodmap.get(q).getNum()-1);
						}
						isEx=true;
					}
				}
				if(isEx){
					dispose();
					new DelOk(fs,util);
				}else{
					setTitle("输入信息有误！");
					name.setText("");
				}
				
			}
		});
		
		cancel=new JButton("取消");
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
		
		JLabel lead=new JLabel("请输入删除菜品编号：");
		//util.initFood();
		name=new JTextField(10);

		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		this.add(lead, constraints, 0,0,4,1);
		this.add(name,constraints,0,1,4,1);

	
		this.add(panel,constraints, 0,2,4,1);
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
//		new DelFood(new  ManUtil());
//	}

}