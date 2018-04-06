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
	
	private JTextField pwdText=new JTextField(10);//�޸�����
	private JLabel pwdLabel=new JLabel("���������µ����룺");
	private JTextField birthText=new JTextField(10);//�޸�����
	private JLabel birthLabel=new JLabel("�������������գ�(MM-DD)");
	
//	public static void main(String[] args) {
//		new AccountModifyCB(new FirstMenuCB(),new ManUtil(),"111111");
//	}
	
	public AccountModifyCB(FirstMenuCB fs,ManUtil util,String userName){
	//	util.initMem();
		//����޸Ĺ��ܵ����
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		jp1.add(pwdLabel);
		jp1.add(pwdText);
		jp2.add(birthLabel);
		jp2.add(birthText);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.CENTER);
		//��Ӱ�ť
		JButton modifyBt=new JButton("ȷ���޸�");
		JButton backBt=new JButton("�������˵�");
		JPanel jp3=new JPanel();
		jp3.add(modifyBt);
		jp3.add(backBt);
		this.add(jp3, BorderLayout.SOUTH);
		//��ť����
		//�޸İ�ť
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
				setTitle("�޸ĳɹ���");
				new MemLoginHandleCB(fs, util);
				dispose();
			}
		});
		//���ذ�ť
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberMenu(fs, util, userName);
				dispose();
			}
		});
		
		//���ô��ڲ���
		setTitle("�˻���Ϣ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setSize(width,height);
		pack();
		setVisible(true);
		setResizable(false);
		this.getContentPane().setBackground(Color.pink);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
