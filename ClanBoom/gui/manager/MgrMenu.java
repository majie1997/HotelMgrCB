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
    	  //�������
        setTitle("����Ա�˵�");
        setLocation(width,height);//��1������ʾ������Ļ�߿���룬��2������ʾ����Ļ�ϱ߿����
        setSize(300, 300);
        setResizable(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //������ť
        JButton Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Button0;
        Button1=new JButton("�鿴��Ա");
        Button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckMem(fs,util);
				dispose();
			}
			
		});
        Button2=new JButton("���ӻ�Ա");
        Button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddMem(fs,util);
			}
		});
        
        Button3=new JButton("ɾ����Ա");
        Button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DelMem(fs,util);
			}
		});
        Button4=new JButton("�鿴��Ʒ");
        Button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FoodShow(fs,util);
			}
		});
        Button5=new JButton("���Ӳ�Ʒ");
        Button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AddFood(fs,util);
			}
		});
        Button6=new JButton("ɾ����Ʒ");
        Button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new DelFood(fs,util);
			}
		});
        Button7=new JButton("�ò����");
        Button7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ReDesk(fs, util);
				}
		});
        
        Button8=new JButton("�鿴Ӫҵ���");
        Button8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BusinessShowCB(fs,util);
			}
		});
        Button9=new JButton("�鿴����ʹ���");
        Button9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DeskShow(fs,util);
			}
		});
        Button0=new JButton("�������˵�");
        Button0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				fs.setVisible(true);
			}
		});
        
        //���ñ�����ɫ����ť��ɫ
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
      //λ�ã�����
      		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
      		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
