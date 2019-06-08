package log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class adgui extends JFrame{
	protected float money = 0;
	protected JPanel jp[] = new JPanel[3];
	protected JLabel L = null;
	protected JTextField tf = null;
	protected BorderLayout layout = new BorderLayout();
	protected JComboBox cb = null;
	protected JButton but[] = new JButton[2];
	protected String name = null;
	protected JComboBox cbyear = null;
	protected JComboBox cbmonth = null;
	protected JComboBox cbday = null;
	protected JComboBox cbhours = null;
	protected JComboBox cbmins = null;
	public adgui(String name) {
		this.name = name;
		this.setLayout(layout);
		L = new JLabel("流动金额：");
		tf = new JTextField("",10);
		jp[0] = new JPanel();
		jp[0].add(L);
		jp[0].add(tf);
//		jp[0].setBackground(new Color(255,200,100));
//		jp[0].setSize(20, 10);
		
		jp[1] = new JPanel();
		cb = new JComboBox();
		cb.addItem("饮食");
		cb.addItem("日用");
		cb.addItem("文教娱乐");
		cb.addItem("其他消费");
		cb.addItem("工资收入");
		cb.addItem("其他收入");
		
		cbyear = new JComboBox();
		for(int i=2000;i<=Calendar.getInstance().get(Calendar.YEAR);i++) {//p179
			cbyear.addItem(i);
		}
		cbmonth = new JComboBox();
		for(int i=1;i<=12;i++){
			cbmonth.addItem(i);
		}
		cbday = new JComboBox();
		for(int i=1;i<=31;i++) {
			cbday.addItem(i);
		}
		cbhours = new JComboBox();
		for(int i=0;i<=23;i++) {
			cbhours.addItem(i);
		}
		cbmins = new JComboBox();
		for(int i=0;i<=59;i++) {
			cbmins.addItem(i);
		}
		//设置默认值
		cbyear.setSelectedIndex(Calendar.getInstance().get(Calendar.YEAR)-2000);
		cbmonth.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
		cbday.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
		cbhours.setSelectedIndex(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		cbmins.setSelectedIndex(Calendar.getInstance().get(Calendar.MINUTE));
//		jp[1].add(new JLabel());  //本来想控制格式
		jp[1].add(new JLabel("日期(年月日)"));
		jp[1].add(cbyear);
		jp[1].add(cbmonth);
		jp[1].add(cbday);
		jp[1].add(new JLabel("时间"));
		jp[1].add(cbhours);
		jp[1].add(cbmins);
		JPanel px = new JPanel();
		px.add(new JLabel("类型"));
		px.add(cb);
		jp[1].add(px);
		
		jp[2] = new JPanel();
		but[0] = new JButton("确认");
		but[1] = new JButton("取消");
		jp[2].add(but[0]);
		jp[2].add(but[1]);
		
//		jp[0].setBackground(new Color(255,100,200));
//		jp[2].setBackground(new Color(255,100,200));
		this.add(jp[0],BorderLayout.NORTH);
		this.add(jp[1],BorderLayout.CENTER);
		this.add(jp[2],BorderLayout.SOUTH);
		
		this.setSize(250,230);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screensize.getWidth()-this.getWidth())/2
				,(int)(screensize.getHeight()-this.getHeight())/2);
		//设置不可变
		this.setResizable(false);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//主界面也会关闭
	}
	
//	public static void main (String[] args) {
//		new adgui("a");
//	}
}
