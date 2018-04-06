package ClanBoom.gui.member;

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

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.DeskCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.WindowCB;
import ClanBoom.utils.ManUtil;

public class FeastOrderCB extends JFrame implements ActionListener {
	
	private static final int width=450;
	private static final int height=300;
	private JLabel tipLabel1=new JLabel("要预定的大厅餐桌数：");
	private JLabel tipLabel2=new JLabel("要预定的包间数：");
	private JButton orderBt=new JButton("预定");
	private JTextField orderText1=new JTextField(3);
	private JTextField orderText2=new JTextField(3);
	private String[] title={"餐桌号","楼层","使用情况"};
	private JScrollPane jsp;//设置滚动轴
	private JTable table;
	private int number1=0;//存储大厅桌数
	private int number2=0;//存储包间数

	
	
	
	public List<DeskCB> temp1=new ArrayList<DeskCB>(); 
	public List<DeskCB> temp2=new ArrayList<DeskCB>(); 
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
					return (int)desk.getDeskHigh();
				}else if(column==2){
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
	

	

	public FeastOrderCB(FirstMenuCB fs,ManUtil util,String userName){

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
		if(util.DeskList.get(i).getDeskState()==0&&util.DeskList.get(i).getDeskUse()==0){
			temp1.add(util.DeskList.get(i));
		}else if(util.DeskList.get(i).getDeskState()==1&&util.DeskList.get(i).getDeskUse()==0){
			temp2.add(util.DeskList.get(i));
		}
		temp.add(util.DeskList.get(i));
		i++;
		}
		atm.fireTableDataChanged();
		//添加面板
		JPanel btPanel =new JPanel();
		JPanel labelPanel=new JPanel();
		//添加标签和按钮
		btPanel.add(orderBt);
		labelPanel.add(tipLabel1);
		labelPanel.add(orderText1);
		labelPanel.add(tipLabel2);
		labelPanel.add(orderText2);
		 //设置面板位置
		add(btPanel,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		add(labelPanel,BorderLayout.NORTH);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//设置监听
		orderBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!orderText1.getText().isEmpty()&&!orderText2.getText().isEmpty()){
					 number1=Integer.parseInt(orderText1.getText());
					 number2=Integer.parseInt(orderText2.getText());
					int flag1=1;
					if(temp1.size()>number1&&temp2.size()>number2){
						flag1=0;
						for(int i=0;i<number1;i++){
							if(util.DeskList.get(i).getDeskUse()!=1){
								//add code herr 1
								MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(i));
								util.DeskList.get(i).setDeskUse(1);
							}else{
								//add code herr 1
								MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(i+1));
								util.DeskList.get(i+1).setDeskUse(1);
							}
						}
						for(int i=0;i<number2;i++){
							if(util.DeskList.get(i).getDeskUse()!=1){
								//add code herr 1
								MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(i+temp1.size()));
								util.DeskList.get(i+temp1.size()).setDeskUse(1);
							}else{
								//add code herr 1
								MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(i+1+temp1.size()));
								util.DeskList.get(i+1+temp1.size()).setDeskUse(1);
							}
						}
						new foodOrderCB(fs,util,userName,number1+number2,0);
						dispose();
					}
						if(flag1==1){
							setTitle("餐桌数不够！");
						}
				}else{
					setTitle("请先输入餐桌数");
				}
			}				
		});

		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("预定酒席");
        // pack();
	}
	
	
//	public static void main(String[] args) {
//		new FeastOrderCB(new ManUtil());
//	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
