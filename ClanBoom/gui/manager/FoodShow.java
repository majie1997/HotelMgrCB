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
	
	
	private JButton backBt=new JButton("返回");
	private JTextField orderText1=new JTextField(5);
	private JTextField orderText2=new JTextField(5);
	private JTextArea jta=new JTextArea(20,10);//输出菜单
	private String[] title={"编号","菜名","价格"};
	private JScrollPane jsp;//设置滚动轴
	private JTable table;
	private List<FoodCB> temp=new ArrayList<FoodCB>();
	private double Money=0;//存储总价
	
	
	private AbstractTableModel atm=new AbstractTableModel() {//实现抽象表格类的抽象方法
		@Override
		public Object getValueAt(int row, int column) {//返回表中的数值

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
	

	

	public FoodShow(FirstMenuCB fs,ManUtil util){
		table=new JTable(atm);

		//将表格加入滚动轴,设置表格格式
		jsp = new JScrollPane(table);  
		table.setToolTipText("显示所有的数据");  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置表格调整尺寸模式  
		table.setCellSelectionEnabled(false);// 设置单元格选择方式  
	    table.setShowVerticalLines(true);// 设置是否显示单元格间的分割线  
		table.setShowHorizontalLines(true);  
	    
		 //初始化表格内容
	//	util.initFood();
		int i=0;
		while(i<util.Foodmap.size()){
			temp.add(util.Foodmap.get(i));
			i++;
		}
		atm.fireTableDataChanged();
		//添加面板
		JPanel btPanel =new JPanel();
		JPanel labelPanel=new JPanel();
		//添加标签和按钮
		btPanel.add(backBt);
		
		 //设置面板位置
		add(btPanel,BorderLayout.SOUTH);
		add(jsp,BorderLayout.CENTER);
		add(labelPanel,BorderLayout.NORTH);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//设置弹出窗口
		jta.setText("您的菜单为：\n");
		
		//设置监听
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MgrMenu(fs, util);
				dispose();
			}
		});

		//设置窗口格式
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("所有菜品");
        // pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
