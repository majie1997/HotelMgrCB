package ClanBoom.gui.manager;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddOk extends JFrame{
	private JButton cancel;
	private JTextField name,password;
	public AddOk(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		
  
		
		cancel=new JButton("返回");
		cancel.setForeground(Color.black);  //取消按钮
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MgrMenu(fs,util);
				
			}
		});

		JPanel panel=new JPanel();
		panel.add(cancel);

		
		JLabel lead=new JLabel("添加成功！");
		
		
		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		/*加按钮和输入框*/
		this.add(lead, constraints, 0,0,4,1);
		this.add(panel,constraints, 0,1,4,1);
		
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
//		new AddOk();
//	}

}