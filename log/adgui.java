package log;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

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
	protected GridLayout layout = new GridLayout(3,1,5,5);
	protected JComboBox cb = null;
	protected JButton but[] = new JButton[2];
	protected String name = null;
	public adgui(String name) {
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
		cb.addItem("��������");
		cb.addItem("��������");
		jp[1].add(cb);
		
		jp[2] = new JPanel();
		but[0] = new JButton("ȷ��");
		but[1] = new JButton("ȡ��");
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
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������Ҳ��ر�
	}
}
