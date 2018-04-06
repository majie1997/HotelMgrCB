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
	private JButton backBt=new JButton("返回");
	private String[] title={"餐桌号","餐桌所在处","楼层","使用情况"};
	private JScrollPane jsp;//设置滚动轴
	private JTable table;
	private int number1=0;//存储大厅桌数
	private int number2=0;//存储包间数
	
	public List<DeskCB> temp=new ArrayList<DeskCB>(); 
	
	
	private AbstractTableModel atm=new AbstractTableModel() {//实现抽象表格类的抽象方法
		@Override
		public Object getValueAt(int row, int column) {//返回表中的数值

			if (!temp.isEmpty())  {
				DeskCB desk=new DeskCB();
				desk=temp.get(row);
				if(column==0){
					return (String)desk.getDeskRecord();
				}else if(column==1){
					if(temp.get(row).getDeskState()==0){
						return (String)"大厅";
					}else{
						return (String)"包间";
					}
				}else if(column==2){
					return (int)desk.getDeskHigh();
				}else if(column==3){
					 if(desk.getDeskUse()==1){
						 return (String)"占用";
					 }else{
						 return (String)"空闲";
					 }
				}
			}  
            else  
                return null;
			return column;
		}
		
		@Override
		public int getRowCount() {
			 return temp.size();  //得到行数
		}
		
		@Override
		public int getColumnCount() {
			return title.length;  //得到列数
		}
		
		public String getColumnName(int column) {  
	            return title[column];// 设置表格列名  
	        }  
		 
		public void setValueAt(Object value, int row, int column) {  
		}  
	  
	    public Class<? extends Object> getColumnClass(int c) {  
	            return getValueAt(0, c).getClass();  // 取得所属对象类  
	        }
        public boolean isCellEditable(int row, int column) {  
        	  
            return false;  
        }// 设置单元格不可编辑  
	};
	

	

	public  DeskShow(FirstMenuCB fs,ManUtil util){

		table=new JTable(atm);

		//将表格加入滚动轴,设置表格格式
		jsp = new JScrollPane(table);  
		table.setToolTipText("显示所有的数据");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置表格调整尺寸模式  
		table.setCellSelectionEnabled(false);// 设置单元格选择方式  
	    table.setShowVerticalLines(true);// 设置是否显示单元格间的分割线  
		table.setShowHorizontalLines(true);  
	    
		 //初始化表格内容
	//	util.initCompart();
		int i=0;
		while(i<util.DeskList.size()){
			temp.add(util.DeskList.get(i));
			i++;
		}
		atm.fireTableDataChanged();
		//增加返回按钮面板
		JPanel jp=new JPanel();
		jp.add(backBt);
		//按钮监听
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MgrMenu(fs, util);
				dispose();
			}
		});
		 //设置面板位置
		add(jp,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);

		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("预定酒席");
        // pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
