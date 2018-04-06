package ClanBoom.gui.base;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class WindowCB extends JFrame {
	private static final int width=400;
	private static final int height=300;
	
	public GridBagConstraints gbc=new GridBagConstraints();
	
	public WindowCB(String title,Color c){

		//���㴰��,�رշ�ʽ
		setTitle(title);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//����
		GridBagLayout gbl=new GridBagLayout();
		setLayout(gbl);
		//���ڴ�С
		setSize(width,height);
		//λ�ã�����
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//������ɫ
		getContentPane().setBackground(c);
		//���񲼾�,����
		//ct=this.getContentPane();
		//ct.setBackground(Color.ORANGE);
		gbc.fill=GridBagConstraints.NONE;
		//gbc.gridwidth=1;
		//gbc.gridheight=1;
		// ����˴˵ļ��
		gbc.insets= new Insets(20, 20, 0, 0); 
		//���ɼ������ɱ༭
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * ������ӵ������еĸ�ʽ
	 * @param cp���
	 * @param gbc���񲼾�����
	 * @param x������
	 * @param y������
	 * @param w����
	 * @param h�����
	 */
	public void addComponent(Component cp,GridBagConstraints gbc,int x,int y,int w,int h){
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		add(cp,gbc);
	}
}
