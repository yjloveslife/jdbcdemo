package log;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class addgui extends adgui implements ActionListener{
	
	public addgui(String name) {
		super(name);
		this.setTitle("增加项信息");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but[0]) {
			if(tf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请填写信息");
				return;
			}
			System.out.println(tf.getText());
			String type = cb.getSelectedItem().toString();
			float money = Float.valueOf(tf.getText());
			connect con = new connect();
			//纠正输入
			if(type =="饮食"||type=="日用"||type=="文教娱乐"||type=="其他消费")
				if(money>0) money = -money;
				else;
			else  
				if(money<0) money = -money;
			String time = cbyear.getSelectedItem().toString()+"-"
				+cbmonth.getSelectedItem().toString()+"-"
				+cbday.getSelectedItem().toString()+" "
				+cbhours.getSelectedItem().toString()+":"
				+cbmins.getSelectedItem().toString()+":0";
//			Date d = new Date(year, month, date, hrs, min, sec);
//			System.out.println(time);
			boolean flag = con.add(name, money,type,time);
			if(flag==true) {
				this.setVisible(false);
			}
		}else if(e.getSource()==but[1]) {
			this.setVisible(false);
		}
	}
}
