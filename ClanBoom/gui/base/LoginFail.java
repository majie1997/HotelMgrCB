package ClanBoom.gui.base;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClanBoom.gui.member.foodOrderCB;
import ClanBoom.utils.ManUtil;


public class LoginFail extends JFrame {
	private static final int width=200;
	private static final int height=100;
	
	private JLabel tipLabel=new JLabel("��������!");
	
	

	//��¼ʧ����ʾ����
	public LoginFail(FirstMenuCB fs,ManUtil util,String userName,int tableNum,int deskNum){
		//���ڣ��������
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("��ʾ");
		//���ô��ڴ�С
		this.setSize(width,height);
		//���ô���λ��:����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(dms.getWidth()-width)/2,(int)(dms.getHeight()-height)/2);
		//���ñ�ǩ��С
		tipLabel.setSize(new Dimension(200,100));
		//������
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		jp1.add(tipLabel);
		JButton jb=new JButton("����");
		jp2.add(jb);
		this.add(jp1, BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);
		pack();
		//��ť����
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new foodOrderCB(fs, util, userName,tableNum,deskNum);
				dispose();
			}
		});
		
	}
}
