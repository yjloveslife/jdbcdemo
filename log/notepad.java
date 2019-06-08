package log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.xdevapi.Result;

public class notepad extends JFrame implements ActionListener{
//	private GridLayout layout = new GridLayout(2,1,5,5);
	private JMenuBar mb = new JMenuBar();
	private JMenu m = new JMenu("�ļ�");
	private JMenuItem midetail = new JMenuItem("��ʾ��ϸ");
	private JMenuItem mianalyze = new JMenuItem("��������");
	private JMenuItem miexit = new JMenuItem("�˳�");
	private JMenu m2 = new JMenu("�༭");
	private JMenuItem  m2new = new JMenuItem("�����֧��");
	private JMenuItem  m2delete = new JMenuItem("ɾ����");
	private JPanel p[] = new JPanel[3];
	private JLabel L[] = new JLabel[3];
	private JTextField tf0 = new JTextField(6); 
	private JTextField tf1 = new JTextField(6); 
	private JTextField tf2 = new JTextField(6); 
	private connect con= null;
	private String name  = null;
	
	String[] s = {"����","�������","����ʱ��"};
	private DefaultTableModel tm = new DefaultTableModel(null,s);
	private JTable table = new JTable(tm);
	private JScrollPane sc = new JScrollPane(table);
	private TableColumnModel columnMode = null;
	private TableColumn tablecolumn = null;
	private Vector<Vector> vec = new Vector<Vector>();
	
	public notepad(String name1) {
		p[0] = new JPanel();
		p[0].setLayout(new BorderLayout());
		
//		p.add(tf);
		this.name = name1;
//		���ҿ��ܿ�����
//		ResultSet resultSet=preparedstatement.executeQuery();
//		resultSet.getArray(0);
		p[1] = new JPanel();
		L[0] = new JLabel("��֧����");
		L[1] = new JLabel("�����룺");
		L[2] = new JLabel("���ࣺ");
		p[1].add(L[0]);
		p[1].add(tf0);
		p[1].add(L[1]);
		p[1].add(tf1);
		p[1].add(L[2]);
		p[1].add(tf2);
		p[0].add(p[1],BorderLayout.NORTH);
		
		//���
		p[2] = new JPanel();
//		String[] s = {"�������","����"};
//		String[][] ss = {{"200","hello","12.2"},{"-200","hello2","1.5"}
//					,{"200","hello","12.2"},{"200","hello","12.2"},{"-200","hello2","1.5"}
//					,{"200","hello","12.2"},{"200","hello","12.2"},{"-200","hello2","1.5"}
//					,{"200","hello","12.2"},{"200","hello","12.2"},{"200","hello","12.2"}};
//		tm = new DefaultTableModel(ss,s);
//		table = new JTable(tm);
//		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(315,200));//�����������
		
		p[2].add(sc);
		
		p[0].add(p[2],BorderLayout.CENTER);
//		System.out.println(sc.getSize());
		
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�Զ���Ӧ�п�ȥ��
//		int colunms = table.getColumnCount();  
//        for(int i = 0; i < colunms; i++)  
//        {  
//            tablecolumn = table.getColumnModel().getColumn(i);  
//            /*��ÿһ�е�Ĭ�Ͽ������Ϊ100*/  
//            tablecolumn.setPreferredWidth(100);  
//        } 
		//ǰ����СЩ
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�Զ���Ӧ�п�ȥ��
		int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms-1; i++) {  
            tablecolumn = table.getColumnModel().getColumn(i);  
            /*��ÿһ�е�Ĭ�Ͽ������Ϊ100*/
            tablecolumn.setPreferredWidth(85);
        }  
        tablecolumn = table.getColumnModel().getColumn(2);
        tablecolumn.setPreferredWidth(127);  
		
		midetail.addActionListener(this);
		mianalyze.addActionListener(this);
		miexit.addActionListener(this);
		m2new.addActionListener(this);
		m2delete.addActionListener(this);
		m.add(midetail);
		m.add(mianalyze);
		m.add(miexit);
		m2.add(m2new);
		m2.add(m2delete);
		mb.add(m);
		mb.add(m2);
		
		this.add(p[0]);
		this.setJMenuBar(mb);
		this.setVisible(true);
		this.setSize(400, 300);
		this.setLocation(500,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		
		//ֱ����ʾ��֧
//		showdetail();
		Timer timer = new Timer();
		timer.schedule(new task(), 100,5000);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(m2new)) {
			new addgui(name);//�����Ϣ�����
		}else if(e.getSource().equals(m2delete)) {
			new deletegui(name);
		}else if(e.getSource().equals(midetail)) {
			showdetail();
		}else if(e.getSource().equals(mianalyze)) {
			new analyze(name);
		}else {
			System.out.println("click���˳�");
			System.exit(0);
			System.out.println(table.getSelectedRow());
		}
	}
	class task extends TimerTask {
		public void run() {
			showdetail();
		}
	}
	public  void showdetail() {
//		���������������ַ���
//		1
//		for(int i=0; i<tm.getRowCount();i++)
//			tm.removeRow(0);
//		2
//			tm.setRowCount(0);
//					
					vec.clear();//����vec��Ϣ
		if(con == null)
			con = new connect();//!
		float a[] = new float[]{0,0};
		con.show(a, name);
		if((String.valueOf(a[0])+"Ԫ").equals(tf0.getText())
				&&(String.valueOf(a[1])+"Ԫ").equals(tf1.getText())
				&&(String.valueOf(a[1]-a[0])+"Ԫ").equals(tf2.getText()))
		{
//			System.out.println("is same");
			return;
		}
		tf0.setText(String.valueOf(a[0])+"Ԫ");
		tf1.setText(String.valueOf(a[1])+"Ԫ");
		tf2.setText(String.valueOf(a[1]-a[0])+"Ԫ");
		tm.setRowCount(0);
		con.detail1(vec, name);
		for(int i=0;i<vec.size();i++) {
//			System.out.println(arr.get(i).getMoney()+arr.get(i).getType());
//			table.addColumn(new TableColumn());
			tm.addRow(vec.get(i));
//			vec.remove(0);
//			vec.remove(1);
		}
	}
}
