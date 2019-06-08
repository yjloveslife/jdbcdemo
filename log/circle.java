package log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class circle extends JFrame{
	public double eat = 0;
	public double daily = 0;
	public double fun = 0;
	public double others = 0;
	public double sum = 0;
	private String name = null;
	private String forwards = null;
	private String afterwards = null;
	private float jieyu = 0;
	public circle(String name,String year,String month) {
		this.setTitle(year+"��"+month+"���������");
		this.name = name;
		int day = getDaysOfMonth(new 			Date(Integer.parseInt(year),Integer.parseInt(month)-1,1));//�·ݴ�0��ʼ
//		System.out.println(month);
		forwards = year+"-"+month+"-1 0:0:0";
		afterwards = year+"-"+month+"-"+day+" 0:0:0";
		connect con = new connect();
		String sums =con.getmonthly(name, forwards, afterwards);
		jieyu =  con.getmonthly2(name, forwards, afterwards);
		if(sums!=null) {
			if(!sums.equals("")||!sums.equals(null)) {
				sum = Double.parseDouble(sums);
//				System.out.println(sum);
					String eats = con.rate("��ʳ", name,forwards, 								afterwards);
		System.out.println(eats);
					if(eats==null) {
						eat=0;
					}else {
						eat = Double.parseDouble(eats);
						eat/=sum;
						eat=(int)(-eat*360);
						System.out.println(eat);
					}
					
					String dailys = con.rate("����", name,forwards, 								afterwards);
		System.out.println(dailys);
//					System.out.println(dailys+"222");
					if(dailys==null) {
						daily=0;
					}else {
						daily = Double.parseDouble(dailys);
						daily/=sum;
						daily=(int)(-daily*360);
						System.out.println(daily);
					}
					
					String funs = con.rate("�Ľ�����", name,forwards, 								afterwards);
		System.out.println(funs);
					if(funs==null) {
						fun=0;
					}else {
						fun = Double.parseDouble(funs);
						fun/=sum;
						fun=(int)(-fun*360);
						System.out.println(fun);
					}
					
					String otherss = con.rate("��������", name,forwards, 								afterwards);
		System.out.println(otherss);
					if(otherss==null) {
						others=0;
					}else {
						others = Double.parseDouble(otherss);
						others/=sum;
						others=(int)(-others*360);
						System.out.println(others);
					}
	//			}
				this.add(new mypanel(),BorderLayout.CENTER);
				JPanel big = new JPanel();
				JLabel L[] = new JLabel[4];
				L[0] = new JLabel("   ��ʳ���ѣ�"+(int)(-eat/3.6)+"%");
				L[0].setOpaque(true);
				L[0].setBackground(new Color(255,200,100));
				L[1] = new JLabel("   �������ѣ�"+(int)(-daily/3.6)+"%");
				L[1].setOpaque(true);
				L[1].setBackground(new Color(255,100,200));
				L[2] = new JLabel(" �Ľ����֣�"+(int)(-fun/3.6)+"%");
				L[2].setOpaque(true);
				L[2].setBackground(new Color(255,100,100));
				L[3] = new JLabel(" �������ѣ�"+(int)(-others/3.6)+"%");
				L[3].setOpaque(true);
				L[3].setBackground(new Color(155,200,100));
				big.setLayout(new GridLayout(4,1,5,5));
				big.add(L[0]);
				big.add(L[1]);
				big.add(L[2]);
				big.add(L[3]);
//				big.setBackground(new Color(255,255,255));
				this.add(big,BorderLayout.EAST);
				
//				System.out.println(eat+" "+daily+" "+fun+" "+others);
//									���Ǹ���
				
			}
			this.setVisible(true);
			this.setSize(300,200);
			this.setLocation(450, 365);
			
		}else{
			JOptionPane.showMessageDialog(null, "�������û������");
		}
	}
	public  int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	class mypanel extends JPanel{
		 public void paint(Graphics g) {
			 Graphics2D g2d = (Graphics2D)g;
			 DecimalFormat df = new DecimalFormat("#.00");//����float�����ʽ
			 g.drawString("�����ѣ�"+df.format(-sum)+"Ԫ", 10, 10);
			 g.drawString("�����룺"+df.format(jieyu)+"Ԫ", 10, 25);
			 g.drawString("��    �ࣺ"+df.format((jieyu+sum))+"Ԫ", 10, 40);
			 g.setColor(new Color(255,200,100));
			 g.fillArc(50, 50, 100, 100, 0, (int)eat-1);
			 g.setColor(new Color(255,100,200));
			 g.fillArc(50, 50, 100, 100, (int)(eat-1), (int)daily);
			 g.setColor(new Color(255,100,100));
			 g.fillArc(50, 50, 100, 100, (int)(eat-1+daily),(int)fun);
			 g.setColor(new Color(155,200,100));
			 g.fillArc(50, 50, 100, 100, (int)(eat-1+daily+fun), (int)others-1);
			 System.out.println(eat+" "+daily+" "+fun+" "+others);
			 
		 }
	}
}
