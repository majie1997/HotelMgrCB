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
	
	private JLabel tipLabel1=new JLabel("�����Ʒ��ţ�");
	private JLabel tipLabel2=new JLabel("������");
	private JButton chooseBt=new JButton("ȷ��");
	private JButton orderBt=new JButton("Ԥ��");
	private JButton backBt=new JButton("����");
	private JTextField orderText1=new JTextField(5);
	private JTextField orderText2=new JTextField(5);
	private JTextArea jta=new JTextArea(20,10);//����˵�
	private String[] title={"���","����","�۸�"};
	private JScrollPane jsp;//���ù�����
	private JTable table;
	private List<FoodCB> temp=new ArrayList<FoodCB>();
	private List<MenuCB> ord=new ArrayList<MenuCB>();//�洢�������
	private List<String> dish=new ArrayList<String>();//�洢����
	private double Money=0;//�洢�ܼ�
	//private int bnum=0;//�洢Ӫҵ���
	
	
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
	

	

	public foodOrderCB(FirstMenuCB fs,ManUtil util,String userName,int tableNum,int deskNum){
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
		btPanel.add(orderBt);
		btPanel.add(backBt);
		labelPanel.add(tipLabel1);
		labelPanel.add(orderText1);
		labelPanel.add(tipLabel2);
		labelPanel.add(orderText2);
		labelPanel.add(chooseBt);
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
		chooseBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isExit=false;
				int num1=Integer.parseInt(orderText1.getText());//��Ʒ���
				int num2=Integer.parseInt(orderText2.getText());//����
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
				//	Food=Food+util.Foodmap.get(num1).getFood()+"  "+num2+"��";
					ord.add(menuCB);
					money+=num2*menuCB.getPrice();
					Money+=money*tableNum;
					
					if(util.Memmap.get(userName).getRestMoney()>=Money*0.75){
						dish.add(orderText2.getText());
						jta.append(menuCB.getFood()+num2+"��\t"+"��"+money+"Ԫ\t");
						jta.setLineWrap(true);
						orderText1.setText("");
						orderText2.setText("");
					}else{//����
						Money=0;
						new LoginFail(fs, util, userName, tableNum,deskNum);
						dispose();
					}
				}else{
					setTitle("��Ʒ����������");
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
					
					jta.append("\n���ǻ�Ա���������ۣ��ϼƣ�"+String.valueOf(Money*0.75)+"\n");
					new dishShowCB(fs,util,userName,jta);
					dispose();
				}else{
					setTitle("���ȵ�ˣ�");
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

		//���ô��ڸ�ʽ
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(width,height);
         setVisible(true);
         setBackground(Color.pink);
         setTitle("���");
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
	
