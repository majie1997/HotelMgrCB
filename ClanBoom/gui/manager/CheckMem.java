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

//查看会员
public class CheckMem extends JFrame implements ActionListener{
	
	private static final int width=400;
	private static final int height=300;
	
	private String[] title={"账户","用户名","当前余额","生日"};
	private JScrollPane jsp;//设置滚动轴
	private JTable table;
	private JButton backBt=new JButton("返回");
	private List<MemberCB> temp=new ArrayList<MemberCB>();
	
	private AbstractTableModel atm=new AbstractTableModel() {//实现抽象表格类的抽象方法
		@Override
		public Object getValueAt(int row, int column) {//返回表中的数值

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
	
	
//	public static void main(String[] args){

//		new CheckMem(new ManUtil());
//	}
	
	
	
	public CheckMem (FirstMenuCB fs,ManUtil util) {
		
		table=new JTable(atm);

		//将表格加入滚动轴,设置表格格式
		jsp = new JScrollPane(table);  
		table.setToolTipText("显示所有的数据");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置表格调整尺寸模式  
		table.setCellSelectionEnabled(false);// 设置单元格选择方式  
	    table.setShowVerticalLines(true);// 设置是否显示单元格间的分割线  
		table.setShowHorizontalLines(true);  
	    
		 //初始化表格内容
	//	util.initMem();
		temp.clear();
		 for (String key : util.Memmap.keySet()) {  
			    temp.add(util.Memmap.get(key));  
			  }  
		atm.fireTableDataChanged();
		
		//增加按钮面板
		JPanel jp=new JPanel();
		jp.add(backBt);
		//按钮监听
		backBt.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MgrMenu(fs, util);
			}
		});
		add(jp,BorderLayout.SOUTH);
        //设置面板位置
		add(jsp,BorderLayout.CENTER);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//设置窗口格式
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setVisible(true);
        setBackground(Color.pink);
        setTitle("会员信息");
        pack();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	}
