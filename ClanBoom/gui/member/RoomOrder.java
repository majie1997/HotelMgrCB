package ClanBoom.gui.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;




import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import javax.swing.table.AbstractTableModel;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.DeskCB;
import ClanBoom.gui.base.FirstMenuCB;

import ClanBoom.utils.ManUtil;

public class RoomOrder extends JFrame implements ActionListener {

	private static final int width=400;
	private static final int height=300;
	private JLabel tipLabel=new JLabel("ҪԤ���İ���ţ�");
	private JButton orderBt=new JButton("Ԥ��");
	private JButton backBt=new JButton("����");
	private JTextField orderText=new JTextField(5);
	private String[] title={"�����","¥��","ʹ�����"};
	//private JLabel titleLabel=new JLabel("");
	private JScrollPane jsp;//���ù�����
	//private JFrame J=new JFrame();
	private JTable table;
	public List<DeskCB> temp=new ArrayList<DeskCB>(); 
	
	
	private AbstractTableModel atm=new AbstractTableModel() {//ʵ�ֳ�������ĳ��󷽷�
		@Override
		public Object getValueAt(int row, int column) {//���ر��е���ֵ

			if (!temp.isEmpty())  {
				DeskCB desk=new DeskCB();
				desk=temp.get(row);
				if(column==0){
					return (String)desk.getDeskRecord();
				}else if(column==1){
					return (int)desk.getDeskHigh();
				}else if(column==2){
					 if(desk.getDeskUse()==1){
						 return (String)"ռ��";
					 }else{
						 return (String)"����";
					 }
				}
			}  
            else  
                return null;
			return column;
		}
		
		@Override
		public int getRowCount() {
			 return temp.size();  //�õ�����
		}
		
		@Override
		public int getColumnCount() {
			return title.length;  //�õ�����
		}
		
		public String getColumnName(int column) {  
	            return title[column];// ���ñ������  
	        }  
		 
		public void setValueAt(Object value, int row, int column) {  
		}  
	  
	    public Class<? extends Object> getColumnClass(int c) {  
	            return getValueAt(0, c).getClass();  // ȡ������������  
	        }
        public boolean isCellEditable(int row, int column) {  
        	  
            return false;  
        }// ���õ�Ԫ�񲻿ɱ༭  
	};
	

	

	public RoomOrder(FirstMenuCB fs,ManUtil util,String userName){
		//super("Ԥ������",Color.PINK);
		//����ʼ��
	//	atm.addTableModelListener(this);
		table=new JTable(atm);

		//�������������,���ñ���ʽ
		jsp = new JScrollPane(table);  
		table.setToolTipText("��ʾ���е�����");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ  
		table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ  
	    table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���  
		table.setShowHorizontalLines(true);  
	    
		 //��ʼ���������
		//util.initCompart();
		int i=0;
		while(i<util.DeskList.size()){
		if(util.DeskList.get(i).getDeskState()==1){
			temp.add(util.DeskList.get(i));
		}
		i++;
		}
		atm.fireTableDataChanged();
		//������
		JPanel btPanel =new JPanel();
		JPanel labelPanel=new JPanel();
		//��ӱ�ǩ�Ͱ�ť
		btPanel.add(orderBt);
		btPanel.add(backBt);
		labelPanel.add(tipLabel);
		labelPanel.add(orderText);
		 //�������λ��
		add(btPanel,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		add(labelPanel,BorderLayout.NORTH);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//���ü���
		orderBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String number=orderText.getText();	
				int flag1=1;
				for(int i=0;i<util.DeskList.size();i++){
					if(util.DeskList.get(i).getDeskRecord().equals(number)){
						flag1=0;
						if(util.DeskList.get(i).getDeskUse()==1){
								tipLabel.setText("�ð����ѱ�ռ�ã�");
							}else{
								tipLabel.setText("���ɹ�Ԥ���ð��䣡");
								MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(i));
								util.DeskList.get(i).setDeskUse(1);
								new foodOrderCB(fs,util,userName,1,i);
								dispose();
							}
						}
					}
					if(flag1==1){
						tipLabel.setText("���������Ϣ����");
					}
			}				
		});
		

		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberMenu(fs, util,userName);
				dispose();
			}
		});

		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("Ԥ������");
        // pack();
	}
	
	
//	public static void main(String[] args) {
	//	new RoomOrder(new ManUtil());
//	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




}
