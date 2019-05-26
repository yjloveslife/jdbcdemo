package log;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class signup extends JFrame implements ActionListener{
	private GridLayout layout;
	private JPanel[] jp = new JPanel[4];
	private JTextField account;
	private JPasswordField password;
	private JLabel[] L = new JLabel[3];
	private JButton[] but = new JButton[2];
	private String strname,strpassword;
	public signup() {
		layout = new GridLayout(4,1,5,5);
		this.setLayout(layout);
		
		
		jp[0] = new JPanel();
		L[0] = new JLabel("ע���˺�",JLabel.CENTER);
		jp[0].add(L[0]);
		this.add(jp[0]);
		
		
		jp[1] = new JPanel();
		L[1] = new JLabel("�˺�:");
		account = new JTextField(12);
		jp[1].add(L[1]);
		jp[1].add(account);
		this.add(jp[1]);
		
		jp[2] = new JPanel();
		L[2] = new JLabel("����:");
		password = new JPasswordField(12);
		jp[2].add(L[2]);
		jp[2].add(password);
		this.add(jp[2]);
		
		jp[3] = new JPanel();
		but[0] = new JButton("ȷ��ע��");
		but[1] = new JButton("����");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		jp[3].add(but[0]);
		jp[3].add(but[1]);
		this.add(jp[3]);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(250, 240);
		//���ô��ھ���
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screensize.getWidth()-this.getWidth())/2
				,(int)(screensize.getHeight()-this.getHeight())/2);
		//���ò��ɱ�
		this.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(but[0])) {
			//����Ƿ���ע��
			strname = account.getText();
			strpassword = new String(password.getPassword());
			connect conn = new connect();
			if(conn.isinside(strname)) {
				JOptionPane.showMessageDialog(null, "�û����ѱ�ע�ᣡ");
			}else{
				// �����û���Ϣ
				conn.insert(strname, strpassword);
				JOptionPane.showMessageDialog(null, "success!");
			};
		}else {
			//����
			this.setVisible(false);
		}
	}
	
}
