package ClanBoom.gui.manager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NetworkChannel;

import javax.swing.*;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

public class MgrMenu extends JFrame implements ActionListener{
	
//    public static void main(String[] args) {
//      new MgrMenu();
 //   }
	private static final int width=400;
	private static final int height=300;
    
    public MgrMenu(FirstMenuCB fs,ManUtil util){
    	  //创建框架
        setTitle("管理员菜单");
        setLocation(width,height);//第1参数表示离左屏幕边框距离，第2参数表示离屏幕上边框距离
        setSize(300, 300);
        setResizable(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //创建按钮
        JButton Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Button0;
        Button1=new JButton("查看会员");
        Button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckMem(fs,util);
				dispose();
			}
			
		});
        Button2=new JButton("增加会员");
        Button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddMem(fs,util);
			}
		});
        
        Button3=new JButton("删除会员");
        Button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DelMem(fs,util);
			}
		});
        Button4=new JButton("查看菜品");
        Button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FoodShow(fs,util);
			}
		});
        Button5=new JButton("增加菜品");
        Button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AddFood(fs,util);
			}
		});
        Button6=new JButton("删除菜品");
        Button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new DelFood(fs,util);
			}
		});
        Button7=new JButton("用餐完毕");
        Button7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ReDesk(fs, util);
				}
		});
        
        Button8=new JButton("查看营业情况");
        Button8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BusinessShowCB(fs,util);
			}
		});
        Button9=new JButton("查看包间和大厅");
        Button9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DeskShow(fs,util);
			}
		});
        Button0=new JButton("返回主菜单");
        Button0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				fs.setVisible(true);
			}
		});
        
        //设置背景颜色、按钮颜色
        JPanel jp=new JPanel();
        jp.add(Button1,"North");
        jp.add(Button2,"North");
        jp.add(Button3,"North");
        jp.add(Button4,"Center");
        jp.add(Button5,"Center");
        jp.add(Button6,"Center");
        jp.add(Button7,"Center");
        jp.add(Button8,"South");
        jp.add(Button9,"South");
        jp.add(Button0,"South");
        
        add(jp);
        jp.setBackground(Color.YELLOW);
        Button0.setForeground(Color.BLUE);
        Button1.setForeground(Color.blue);
        Button2.setForeground(Color.BLUE);
        Button3.setForeground(Color.BLUE);
        Button4.setForeground(Color.BLUE);
        Button5.setForeground(Color.BLUE);
        Button7.setForeground(Color.BLUE);
        Button6.setForeground(Color.BLUE);
        Button8.setForeground(Color.BLUE);
        Button9.setForeground(Color.BLUE);
        setVisible(true);
      //位置：居中
      		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
      		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
