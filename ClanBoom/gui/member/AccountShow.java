package ClanBoom.gui.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.WindowCB;
import ClanBoom.utils.ManUtil;

public class AccountShow extends JFrame implements ActionListener {

	private static final int width=450;
	private static final int height=300;
	

	private JTextArea jta=new JTextArea(3,30);

//	public static void main(String[] args) {
//		new AccountShow(new ManUtil(),"111111");
//	}
	
	public AccountShow(FirstMenuCB fs,ManUtil util,String userName){
//		util.initMem();
		//显示账户信息
		jta.append("账号"+"\t"
					+"姓名"+"\t"
					+"余额"+"\t"
					+"生日\n");
		jta.append(util.Memmap.get(userName).getNum()+
							"\t"+util.Memmap.get(userName).getManName()+
							"\t"+util.Memmap.get(userName).getRestMoney()+
							"\t"+util.Memmap.get(userName).getBirth());
		jta.setLineWrap(true);
		jta.setEditable(false);
		JPanel jp=new JPanel();
		jp.add(jta);
		this.add(jp,BorderLayout.CENTER);
		//添加返回按钮
		JButton backBt=new JButton("返回会员菜单");
		JPanel btJp=new JPanel();
		btJp.add(backBt);
		this.add(btJp,BorderLayout.SOUTH);
		//按钮监听
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MemberMenu(fs, util, userName);
				dispose();
			}
		});
		
		//设置窗口参数
		setTitle("账户信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //setSize(width,height);
		pack();
	    setVisible(true);
	    setResizable(false);
	    this.getContentPane().setBackground(Color.pink);
	    //位置：居中
	  	Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
	  	setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
