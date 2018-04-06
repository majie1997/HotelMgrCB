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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import ClanBoom.entity.BusinessCB;
import ClanBoom.entity.MemberCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

public class BusinessShowCB extends JFrame implements ActionListener {

	private static final int width=400;
	private static final int height=300;
	
	private String[] title={"�˻�","Ԥ������","��Ʒ�ܼ�"};
	private JScrollPane jsp;//���ù�����
	private JTable table;
	private JButton backBt=new JButton("����");
	private List<BusinessCB> temp=new ArrayList<BusinessCB>();
	
	private AbstractTableModel atm=new AbstractTableModel() {//ʵ�ֳ�������ĳ��󷽷�
		@Override
		public Object getValueAt(int row, int column) {//���ر��е���ֵ

			if (!temp.isEmpty())  {
				BusinessCB business=new BusinessCB();
				business=temp.get(row);
				if(column==0){
					return (String)business.getName();
				}else if(column==1){
					return (String)business.getDesk();
				}else if(column==2){
					return (double)business.getMoney();
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

// BusinessShowCB(new ManUtil());
//	}
	
	
	
	public BusinessShowCB(FirstMenuCB fs,ManUtil util) {
		
		table=new JTable(atm);

		//�������������,���ñ���ʽ
		jsp = new JScrollPane(table);  
		table.setToolTipText("��ʾ���е�����");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ  
		table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ  
	    table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���  
		table.setShowHorizontalLines(true);  
	    
		 //��ʼ���������
//		util.initMem();
		int i=0;
		while(i<util.BusinessList.size()){
			temp.add(util.BusinessList.get(i));
			i++;
		}
		atm.fireTableDataChanged();
		
		//���Ӱ�ť���
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
		add(jsp,BorderLayout.CENTER);
		add(jp,BorderLayout.SOUTH);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//���ô��ڸ�ʽ
		 setTitle("Ӫҵ���");
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setVisible(true);
        setBackground(Color.pink);
        pack();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
