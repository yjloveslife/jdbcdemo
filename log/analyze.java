package log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class analyze extends JFrame implements ActionListener{
	private BorderLayout layout = new BorderLayout();
	private JPanel[] p = new JPanel[2];
	private JPanel pic = null;
	private JComboBox cbyear = null;
	private JComboBox cbmonth = null;
	private JButton but[] = new JButton[2];
	private String name = null;
	public analyze(String name){
		this.name = name;
		this.setLayout(layout);
		
		p[0] = new JPanel();
		p[0].add(new JLabel("选择分析月份",JLabel.CENTER));
		cbyear = new JComboBox();
		for(int i=2000;i<=Calendar.getInstance().get(Calendar.YEAR);i++) {//p179
			cbyear.addItem(i);
		}
		cbmonth = new JComboBox();
		for(int i=1;i<=12;i++){
			cbmonth.addItem(i);
		}
		cbyear.setSelectedIndex(Calendar.getInstance().get(Calendar.YEAR)-2000);
		cbmonth.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
		p[0].add(cbyear);
		p[0].add(cbmonth);
		this.add(p[0],BorderLayout.NORTH);
		
//		pic = new JPanel();
//		mypanel mp[] = new mypanel[2];
//		mp[0] = new mypanel();
//		String year = cbyear.getSelectedItem().toString()
//				, month = cbmonth.getSelectedItem().toString();
//		pic.add(mp[0]);
//		pic.setBackground(new Color(255,200,100));
//		this.add(mp[0],BorderLayout.CENTER);
		
		p[1] = new JPanel();
		but[0] = new JButton("confirm");
		but[1] = new JButton("cancel");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		p[1].add(but[0]);
		p[1].add(but[1]);
		
		this.add(p[1],BorderLayout.SOUTH);
		this.setSize(300, 300);
		this.setLocation(450,300);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==but[0]) {
			String year = cbyear.getSelectedItem().toString();
			String month = cbmonth.getSelectedItem().toString();
			if(year!=null&&month!=null&&!year.equals("")&&!month.equals("")) {
//				System.out.println(name+" "+year+" "+month+" 1");
				new circle(name,year,month);
//				System.out.println(name+" "+year+" "+month+" 2");
			}
		}else {
			System.exit(0);
		}
	}
}
