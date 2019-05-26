package log;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;

public class addgui extends JFrame implements ActionListener{
	private float money = 0;
	private JPanel jp[] = new JPanel[3];
	private JLabel L = null;
	private JTextField tf = null;
	private GridLayout layout = new GridLayout(3,1,5,5);
	private JComboBox cb = null;
	private JButton but[] = new JButton[2];
	String name = null;
	public addgui(String name) {
		this.name = name;
		this.setLayout(layout);
		L = new JLabel("������");
		tf = new JTextField("",10);
		jp[0] = new JPanel();
		jp[0].add(L);
		jp[0].add(tf);
		
		jp[1] = new JPanel();
		cb = new JComboBox();
		cb.addItem("��ʳ");
		cb.addItem("����");
		cb.addItem("�Ľ�����");
		cb.addItem("��������");
		jp[1].add(cb);
		
		jp[2] = new JPanel();
		but[0] = new JButton("ȷ��");
		but[1] = new JButton("ȡ��");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		jp[2].add(but[0]);
		jp[2].add(but[1]);
		this.add(jp[0]);
		this.add(jp[1]);
		this.add(jp[2]);
		
		
		this.setSize(200,200);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screensize.getWidth()-this.getWidth())/2
				,(int)(screensize.getHeight()-this.getHeight())/2);
		//���ò��ɱ�
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but[0]) {
			connect con = new connect();
			boolean flag = con.add(name, Float.valueOf(tf.getText()),cb.getSelectedItem().toString());
			if(flag==true) {
				this.setVisible(false);
			}
		}else if(e.getSource()==but[1]) {
			this.setVisible(false);
		}
		
	}
	
}
