package log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class deletegui extends adgui implements ActionListener{
	public deletegui(String name) {
		super(name);
		this.setTitle("ɾ������Ϣ");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but[0]) {
			if(tf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "����д��Ϣ");
				return;
			}
			String type = cb.getSelectedItem().toString();
			float money = Float.valueOf(tf.getText());
			connect con = new connect();
			//��������
			if(type =="��ʳ"||type=="����"||type=="�Ľ�����"||type=="��������")
				if(money>0) money = -money;
				else;
			else  
				if(money<0) money = -money;
			boolean flag =false;
			if(con.isin(name, money,type)) {
				flag = con.delete(name, money,type);
			}else {
				JOptionPane.showMessageDialog(null, "δ�ҵ�����");
				return;
			}
			if(flag==true) {
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
			}
		}else if(e.getSource()==but[1]) {
			this.setVisible(false);
		}
		
	}
}
