package ClanBoom.gui.member;

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
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.BusinessCB;
import ClanBoom.entity.DeskCB;
import ClanBoom.entity.FoodCB;
import ClanBoom.entity.MenuCB;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.LoginFail;
import ClanBoom.gui.manager.BusinessShowCB;
import ClanBoom.utils.ManUtil;

public class foodOrderCB extends JFrame implements ActionListener {

	
	private static final int width=600;
	private static final int height=300;
	
	private JLabel tipLabel1=new JLabel("输入菜品编号：");
	private JLabel tipLabel2=new JLabel("份数：");
	private JButton chooseBt=new JButton("确定");
	private JButton orderBt=new JButton("预定");
	private JButton backBt=new JButton("返回");
	private JTextField orderText1=new JTextField(5);
	private JTextField orderText2=new JTextField(5);
	private JTextArea jta=new JTextArea(20,10);//输出菜单
	private String[] title={"编号","菜名","价格"};
	private JScrollPane jsp;//设置滚动轴
	private JTable table;
	private List<FoodCB> temp=new ArrayList<FoodCB>();
	private List<MenuCB> ord=new ArrayList<MenuCB>();//存储点餐内容
	private List<String> dish=new ArrayList<String>();//存储份数
	private double Money=0;//存储总价
	//private int bnum=0;//存储营业编号
	
	
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
	

	

	public foodOrderCB(FirstMenuCB fs,ManUtil util,String userName,int tableNum,int deskNum){
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
		btPanel.add(orderBt);
		btPanel.add(backBt);
		labelPanel.add(tipLabel1);
		labelPanel.add(orderText1);
		labelPanel.add(tipLabel2);
		labelPanel.add(orderText2);
		labelPanel.add(chooseBt);
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
		chooseBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isExit=false;
				int num1=Integer.parseInt(orderText1.getText());//菜品编号
				int num2=Integer.parseInt(orderText2.getText());//份数
				for(int i=0;i<util.Foodmap.size();i++){
					if(num1==util.Foodmap.get(i).getNum()){
						isExit=true;
						break;
					}
				}
				if(isExit){
					double money=0;
				//	String Food="";
					MenuCB menuCB=new MenuCB(num1-1,util.Foodmap.get(num1-1).getFood(),util.Foodmap.get(num1-1).getPrice(),num2);
				//	Food=Food+util.Foodmap.get(num1).getFood()+"  "+num2+"份";
					ord.add(menuCB);
					money+=num2*menuCB.getPrice();
					Money+=money*tableNum;
					
					if(util.Memmap.get(userName).getRestMoney()>=Money*0.75){
						dish.add(orderText2.getText());
						jta.append(menuCB.getFood()+num2+"份\t"+"共"+money+"元\t");
						jta.setLineWrap(true);
						orderText1.setText("");
						orderText2.setText("");
					}else{//余额不足
						Money=0;
						new LoginFail(fs, util, userName, tableNum,deskNum);
						dispose();
					}
				}else{
					setTitle("菜品号输入有误");
					orderText1.setText("");
					orderText2.setText("");
				}
			}
				
		});
		orderBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Money!=0.0){
					double money=util.Memmap.get(userName).getRestMoney()-Money*0.75;
					util.Memmap.get(userName).setRestMoney(money);
					// add code here 1
					MySQLUtil.changeMember_ClanBoom(3, util.Memmap.get(userName));
					
					BusinessCB bs=new BusinessCB();
					bs.setName(userName);
					bs.setMoney(Money*0.75);
					bs.setDesk(String.valueOf(tableNum));
					util.BusinessList.add(bs);
					bs.show();
					// add code here 1
					MySQLUtil.addBusiness_ClanBoom(bs);
					
					jta.append("\n您是会员享受七五折，合计："+String.valueOf(Money*0.75)+"\n");
					new dishShowCB(fs,util,userName,jta);
					dispose();
				}else{
					setTitle("请先点菜！");
				}
			}
		});
		
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Money!=0){
					new MemberMenu(fs, util,userName);
					dispose();
				}else{
					// add code here 1
					MySQLUtil.isDeskUsed_ClanBoom(util.DeskList.get(deskNum));
					util.DeskList.get(deskNum).setDeskUse(0);
					new MemberMenu(fs, util,userName);
					dispose();
				}
			}
		});

		//设置窗口格式
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("点餐");
        // pack();
	}



  //   	public static void main(String[] args) {
   //  		new foodOrderCB(new FirstMenuCB(), new ManUtil(),"");
   //  	}
         
     	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub

    	}
}
	
