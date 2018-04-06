package ClanBoom.gui.member;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.gui.base.WindowCB;
import ClanBoom.utils.ManUtil;

public class MemberMenu extends WindowCB implements ActionListener {

	private JLabel tipLabel=new JLabel("△▼△▼△会员菜单△▼△▼△");
	private JButton roomOrderBt=new JButton("预定包间");
	private JButton hallOrderBt=new JButton("预定大厅");
	private JButton feastOrderBt=new JButton("预订酒席");
	private JButton accountShowBt=new JButton("查看账户");
	private JButton accountModifyBt=new JButton("修改账户信息");
	private JButton backBt=new JButton("返回主菜单");
 	
//	public static void main(String[] args) {
//		new MemberMenu(new FirstMenuCB(),new ManUtil(),"1");
//	}
	
	public MemberMenu(FirstMenuCB fs,ManUtil util,String userName) {
		super("会员菜单", Color.pink);
		//添加组件
		addComponent(tipLabel, gbc, 0, 0, 7, 1);
		addComponent(roomOrderBt, gbc, 0, 1, 3, 1);
		addComponent(hallOrderBt, gbc, 4, 1, 3, 1);
		addComponent(feastOrderBt, gbc, 0, 2, 3, 1);
		addComponent(accountShowBt, gbc, 4, 2, 3, 1);
		addComponent(accountModifyBt, gbc, 0, 3, 4, 1);
		addComponent(backBt, gbc, 4, 3, 3, 1);
		//按钮监听
		roomOrderBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RoomOrder(fs,util,userName);
				dispose();
			}
		});
		hallOrderBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HallOrderCB(fs,util,userName);
				dispose();
			}
		});
		feastOrderBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FeastOrderCB(fs,util,userName);
				dispose();
			}
		});
		accountShowBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AccountShow(fs,util,userName);
				dispose();
			}
		});
		accountModifyBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AccountModifyCB(fs,util,userName);
				dispose();
			}
		});
		backBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fs.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}




}
