package ClanBoom.gui.manager;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.gui.base.FirstMenuCB;
import ClanBoom.utils.ManUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
public class DelMem extends JFrame implements ActionListener{
	private JButton ok,cancel;
	private JTextField num;
	
	public DelMem(FirstMenuCB fs,ManUtil util){
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		
		ok=new JButton("ȷ��");
		ok.setForeground(Color.black);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isE=false;
				Set<String> numbers = util.Memmap.keySet();
				Iterator<String> it = numbers.iterator();
				String n=num.getText();
				while (it.hasNext()) {
					String searchNum = it.next();
					if (searchNum.equals(n)){
						MySQLUtil.deleteMember_ClanBoom(util.Memmap.get(searchNum));
						util.Memmap.remove(searchNum);
						isE=true;
						break;
					}
				}
				if(isE){
					dispose();
					new DelOk(fs,util);
				}else{
					setTitle("������Ļ�Ա�����󣡣���");
					num.setText("");
				}
				
			}
		});
		
		cancel=new JButton("ȡ��");
		cancel.setForeground(Color.black);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MgrMenu(fs,util);
				
			}
		});
		
		JPanel panel=new JPanel();
		panel.add(ok);
		panel.add(cancel);
		
		JLabel lead=new JLabel("������Ҫɾ���Ļ�Ա�˻��ţ�");
		util.initMem();
		JLabel usernum=new JLabel("�˻���");

		num=new JTextField(10);
		GridBagConstraints constraints=new GridBagConstraints();
		
	
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		
		this.add(lead, constraints, 0,0,4,1);
		this.add(usernum,constraints,0,1,1,1);
		this.add(num, constraints,1,1,3,1);

		this.add(panel,constraints, 0,2,4,1);
		this.setTitle("ɾ����Ա");
		
		this.setBounds(500, 300, 300, 150);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.yellow);

	}
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=w;
		constraints.gridheight=h;
		add(c,constraints);
	}
//	public static void main(String[] args){
//		new DelMem(new  ManUtil());
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}