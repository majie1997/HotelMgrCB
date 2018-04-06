package ClanBoom.gui.manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import ClanBoom.entity.FoodCB;
import ClanBoom.entity.MemberCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.*;

//�鿴��Ա
public class CheckMem extends JFrame implements ActionListener{
	
	private static final int width=400;
	private static final int height=300;
	
	private String[] title={"�˻�","�û���","��ǰ���","����"};
	private JScrollPane jsp;//���ù�����
	private JTable table;
	private JButton backBt=new JButton("����");
	private List<MemberCB> temp=new ArrayList<MemberCB>();
	
	private AbstractTableModel atm=new AbstractTableModel() {//ʵ�ֳ�������ĳ��󷽷�
		@Override
		public Object getValueAt(int row, int column) {//���ر��е���ֵ

			if (!temp.isEmpty())  {
				MemberCB mem=new MemberCB();
				mem=temp.get(row);
				if(column==0){
					return (String)mem.getManName();
				}else if(column==1){
					return (String)mem.getNum();
				}else if(column==2){
					return (double)mem.getRestMoney();
				}else if(column==3){
					return (String)mem.getBirth();
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
	
	
//	public static void main(String[] args){

//		new CheckMem(new ManUtil());
//	}
	
	
	
	public CheckMem (FirstMenuCB fs,ManUtil util) {
		
		table=new JTable(atm);

		//�������������,���ñ���ʽ
		jsp = new JScrollPane(table);  
		table.setToolTipText("��ʾ���е�����");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ  
		table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ  
	    table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���  
		table.setShowHorizontalLines(true);  
	    
		 //��ʼ���������
	//	util.initMem();
		temp.clear();
		 for (String key : util.Memmap.keySet()) {  
			    temp.add(util.Memmap.get(key));  
			  }  
		atm.fireTableDataChanged();
		
		//���Ӱ�ť���
		JPanel jp=new JPanel();
		jp.add(backBt);
		//��ť����
		backBt.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MgrMenu(fs, util);
			}
		});
		add(jp,BorderLayout.SOUTH);
        //�������λ��
		add(jsp,BorderLayout.CENTER);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//���ô��ڸ�ʽ
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setVisible(true);
        setBackground(Color.pink);
        setTitle("��Ա��Ϣ");
        pack();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	}
