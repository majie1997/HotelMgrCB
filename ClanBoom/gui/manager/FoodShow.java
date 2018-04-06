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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ClanBoom.entity.FoodCB;
import ClanBoom.entity.MenuCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.LoginFail;
import ClanBoom.gui.member.dishShowCB;
import ClanBoom.utils.ManUtil;

public class FoodShow extends JFrame implements ActionListener {

	
	private static final int width=400;
	private static final int height=300;
	
	
	private JButton backBt=new JButton("����");
	private JTextField orderText1=new JTextField(5);
	private JTextField orderText2=new JTextField(5);
	private JTextArea jta=new JTextArea(20,10);//����˵�
	private String[] title={"���","����","�۸�"};
	private JScrollPane jsp;//���ù�����
	private JTable table;
	private List<FoodCB> temp=new ArrayList<FoodCB>();
	private double Money=0;//�洢�ܼ�
	
	
	private AbstractTableModel atm=new AbstractTableModel() {//ʵ�ֳ�������ĳ��󷽷�
		@Override
		public Object getValueAt(int row, int column) {//���ر��е���ֵ

			if (!temp.isEmpty())  {
				FoodCB food=new FoodCB();
				food=temp.get(row);
				if(column==0){
					return (int)food.getNum();
				}else if(column==1){
					return (String)food.getFood();
				}else if(column==2){
					return (double)food.getPrice();
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
	

	

	public FoodShow(FirstMenuCB fs,ManUtil util){
		table=new JTable(atm);

		//�������������,���ñ���ʽ
		jsp = new JScrollPane(table);  
		table.setToolTipText("��ʾ���е�����");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ  
		table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ  
	    table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���  
		table.setShowHorizontalLines(true);  
	    
		 //��ʼ���������
	//	util.initFood();
		int i=0;
		while(i<util.Foodmap.size()){
			temp.add(util.Foodmap.get(i));
			i++;
		}
		atm.fireTableDataChanged();
		//������
		JPanel btPanel =new JPanel();
		JPanel labelPanel=new JPanel();
		//��ӱ�ǩ�Ͱ�ť
		btPanel.add(backBt);
		
		 //�������λ��
		add(btPanel,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		add(labelPanel,BorderLayout.NORTH);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//���õ�������
		jta.setText("���Ĳ˵�Ϊ��\n");
		
		//���ü���
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MgrMenu(fs, util);
				dispose();
			}
		});

		//���ô��ڸ�ʽ
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("���в�Ʒ");
        // pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
