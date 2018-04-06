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
	
	private JLabel tipLabel=new JLabel("�����Ա��¼���� ����");
	private JButton backButton=new JButton("����");
	private JLabel userLabel=new JLabel("�û���");
	private JLabel pswLabel=new JLabel("����");
	private JTextField userText=new JTextField(10);
	private JPasswordField pswText=new JPasswordField(10);	
	private JButton submitButton=new JButton("��¼");
	private JButton resetButton=new JButton("����");
	
	/**
	 * ������
	 * @param args
	 */
//	public static void main(String[] args){
//		new MemLoginHandleCB(new FirstMenuCB(), new ManUtil());
//	}
	
	
	public MemLoginHandleCB(FirstMenuCB fm,ManUtil util){
		super("��¼",Color.PINK);
		//��ť��ɫ
		backButton.setBackground(Color.white);
		//������������
		pswText.setEchoChar('*');
		//������
		addComponent(tipLabel, gbc, 0, 0, 10, 1);
		addComponent(userLabel, gbc, 0, 1, 1, 1);
		addComponent(pswLabel, gbc, 0, 2, 1, 1);
		addComponent(userText, gbc, 1, 1, 0, 1);
		addComponent(pswText, gbc, 1, 2, 0, 1 );
		addComponent(submitButton, gbc, 0, 3, 1, 1);
		addComponent(resetButton, gbc, 3, 3, 1, 1);
		addComponent(backButton, gbc, 4, 4, 1, 1);
		//��ť����
		//���ذ�ť
		backButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
							dispose();
							fm.setVisible(true);
						
					}
				} );
		//��¼��ť
		submitButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()== submitButton){
							String userName=userText.getText();//�õ��û���
							String passWord=pswText.getText();//�õ�����
							boolean right=util.isExistCard(userName,passWord);//�ж��û����������Ƿ�ƥ��
							if(right){
								dispose();
								new MemberMenu(fm,util,userName);
							}else{
								tipLabel.setText("�û������������");
							}
						}
					}
				});
		//���ð�ť
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

	//��ť����
	/*private class backButtonActionListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}*/
}
