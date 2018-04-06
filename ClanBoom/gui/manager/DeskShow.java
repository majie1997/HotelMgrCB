package ClanBoom.gui.manager;

import java.awt.BorderLayout;
import java.awt.Color;
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

import ClanBoom.entity.DeskCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.member.foodOrderCB;
import ClanBoom.utils.ManUtil;

public class DeskShow extends JFrame implements ActionListener {

	private static final int width=450;
	private static final int height=300;
	private JButton backBt=new JButton("����");
	private String[] title={"������","�������ڴ�","¥��","ʹ�����"};
	private JScrollPane jsp;//���ù�����
	private JTable table;
	private int number1=0;//�洢��������
	private int number2=0;//�洢������
	
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
					if(temp.get(row).getDeskState()==0){
						return (String)"����";
					}else{
						return (String)"����";
					}
				}else if(column==2){
					return (int)desk.getDeskHigh();
				}else if(column==3){
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
	

	

	public  DeskShow(FirstMenuCB fs,ManUtil util){

		table=new JTable(atm);

		//�������������,���ñ���ʽ
		jsp = new JScrollPane(table);  
		table.setToolTipText("��ʾ���е�����");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ  
		table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ  
	    table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���  
		table.setShowHorizontalLines(true);  
	    
		 //��ʼ���������
	//	util.initCompart();
		int i=0;
		while(i<util.DeskList.size()){
			temp.add(util.DeskList.get(i));
			i++;
		}
		atm.fireTableDataChanged();
		//���ӷ��ذ�ť���
		JPanel jp=new JPanel();
		jp.add(backBt);
		//��ť����
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MgrMenu(fs, util);
				dispose();
			}
		});
		 //�������λ��
		add(jp,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);

		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("Ԥ����ϯ");
        // pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
