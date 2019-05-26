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
		L[0] = new JLabel("注册账号",JLabel.CENTER);
		jp[0].add(L[0]);
		this.add(jp[0]);
		
		
		jp[1] = new JPanel();
		L[1] = new JLabel("账号:");
		account = new JTextField(12);
		jp[1].add(L[1]);
		jp[1].add(account);
		this.add(jp[1]);
		
		jp[2] = new JPanel();
		L[2] = new JLabel("密码:");
		password = new JPasswordField(12);
		jp[2].add(L[2]);
		jp[2].add(password);
		this.add(jp[2]);
		
		jp[3] = new JPanel();
		but[0] = new JButton("确认注册");
		but[1] = new JButton("返回");
		but[0].addActionListener(this);
		but[1].addActionListener(this);
		jp[3].add(but[0]);
		jp[3].add(but[1]);
		this.add(jp[3]);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(250, 240);
		//设置窗口居中
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screensize.getWidth()-this.getWidth())/2
				,(int)(screensize.getHeight()-this.getHeight())/2);
		//设置不可变
		this.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(but[0])) {
			//检查是否能注册
			strname = account.getText();
			strpassword = new String(password.getPassword());
			connect conn = new connect();
			if(conn.isinside(strname)) {
				JOptionPane.showMessageDialog(null, "用户名已被注册！");
			}else{
				// 插入用户信息
				conn.insert(strname, strpassword);
				JOptionPane.showMessageDialog(null, "success!");
			};
		}else {
			//返回
			this.setVisible(false);
		}
	}
	
}
