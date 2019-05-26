package log;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.*;
import	javax.swing.*;

public class login extends	JFrame implements ActionListener {
	private GridLayout layout = new GridLayout(4,1,5,5);
	JPanel	pbig	=	new	JPanel();
	JPanel	p1	=	new	JPanel();
	JPanel	p2	=	new	JPanel();
	JPanel	p3 	=	new JPanel();
	//居中显示
	JLabel	L1	=	new	JLabel("登录",JLabel.CENTER);
	JLabel	L2	=	new	JLabel("username:");
	JLabel	L3	=	new	JLabel("password:");
	JTextField name = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	JButton butlogin = new JButton("log in");
	JButton butsignup = new JButton("sign up");
	JButton butexit = new JButton("exit");
	String strname = null,
			strpassword = null;
	public	login() {
		pbig.setLayout(layout);
		pbig.add(L1);
		
		p1.add(L2);
		p1.add(name);
		pbig.add(p1);
		
		p2.add(L3);
		p2.add(password);
		pbig.add(p2);
		
		butlogin.setSize(15,10);
		p3.add(butlogin);
		butsignup.setSize(10,10);
		p3.add(butsignup);
		p3.add(butexit);
		pbig.add(p3);
		butlogin.addActionListener(this);
		butexit.addActionListener(this);
		butsignup.addActionListener(this);
		
		this.add(pbig);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 240);
		//设置窗口居中
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screensize.getWidth()-this.getWidth())/2
				,(int)(screensize.getHeight()-this.getHeight())/2);
		this.setResizable(false);
	}
	public	static	void	main(String	[]	args){
		new	login();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(butexit)) {
			System.exit(0);
		}else if(e.getSource().equals(butlogin)) {
			//登录
			strname = name.getText();
			strpassword = new String(password.getPassword());
			connect connect1 = new connect();
			if(connect1.isinside(strname, strpassword)) {
				JOptionPane.showMessageDialog(this, "success");
				strname = name.getText();
				new notepad(strname);
				//隐藏登录窗口
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(this, "failure");
			}
		}else {
			//可添加模态和单例p127
			new signup();
		}
	}
}
