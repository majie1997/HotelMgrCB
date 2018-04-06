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
		
		ok=new JButton("��¼");
		ok.setForeground(Color.black);
		//��¼��ť
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		//		util.initAdmin();
				if(util.isExistAdmin(name.getText(),password.getText())){
					new  MgrMenu(fs,util);
					dispose();
				}else{
					setTitle("�˺Ż���������!!!");
					name.setText("");
					password.setText("");
				}
				
				
			}
		});
		
		

			
		cancel=new JButton("ȡ��");
		cancel.setForeground(Color.black);  //ȡ����ť
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fs.setVisible(true);
				dispose();
						}
		});
		
		cancel.addActionListener(this);//ע�������
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);  //��ť����
		
		JLabel username=new JLabel("�˺�"); //�˺�
		JLabel userpassword=new JLabel("����"); //����
		
		name=new JTextField(10);  //�˺������
		password=new JTextField(10);  //���������
			
		GridBagConstraints constraints=new GridBagConstraints();
		
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		/*�Ӱ�ť�������*/
		this.add(username,constraints,0,0,1,1);
		this.add(name, constraints,1,0,3,1);
		this.add(userpassword,constraints,0,1,1,1);
		this.add(password, constraints, 1,1,3,1);
		this.add(panel,constraints, 0,2,4,1);
		this.setTitle("����Ա��¼");
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
