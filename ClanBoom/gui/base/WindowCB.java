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

		//顶层窗口,关闭方式
		setTitle(title);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//布局
		GridBagLayout gbl=new GridBagLayout();
		setLayout(gbl);
		//窗口大小
		setSize(width,height);
		//位置：居中
		Dimension dms=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dms.getWidth()-width)/2, (int)(dms.getHeight()-height)/2);
		//背景颜色
		getContentPane().setBackground(c);
		//网格布局,容器
		//ct=this.getContentPane();
		//ct.setBackground(Color.ORANGE);
		gbc.fill=GridBagConstraints.NONE;
		//gbc.gridwidth=1;
		//gbc.gridheight=1;
		// 组件彼此的间距
		gbc.insets= new Insets(20, 20, 0, 0); 
		//面板可见，不可编辑
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * 设置添加到网格中的格式
	 * @param cp组件
	 * @param gbc网格布局容器
	 * @param x横坐标
	 * @param y纵坐标
	 * @param w网格长
	 * @param h网格高
	 */
	public void addComponent(Component cp,GridBagConstraints gbc,int x,int y,int w,int h){
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		add(cp,gbc);
	}
}
