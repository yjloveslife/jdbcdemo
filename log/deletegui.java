package log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class deletegui extends adgui implements ActionListener{
	public deletegui(String name) {
		super(name);
		this.setTitle("删除项信息");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but[0]) {
			if(tf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请填写信息");
				return;
			}
			String type = cb.getSelectedItem().toString();
			float money = Float.valueOf(tf.getText());
			connect con = new connect();
			//纠正输入
			if(type =="饮食"||type=="日用"||type=="文教娱乐"||type=="其他消费")
				if(money>0) money = -money;
				else;
			else  
				if(money<0) money = -money;
			boolean flag =false;
			if(con.isin(name, money,type)) {
				flag = con.delete(name, money,type);
			}else {
				JOptionPane.showMessageDialog(null, "未找到该项");
				return;
			}
			if(flag==true) {
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		}else if(e.getSource()==but[1]) {
			this.setVisible(false);
		}
		
	}
}
