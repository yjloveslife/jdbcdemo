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
		this.setTitle(year+"年"+month+"月消费情况");
		this.name = name;
		int day = getDaysOfMonth(new 			Date(Integer.parseInt(year),Integer.parseInt(month)-1,1));//月份从0开始
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
					String eats = con.rate("饮食", name,forwards, 								afterwards);
		System.out.println(eats);
					if(eats==null) {
						eat=0;
					}else {
						eat = Double.parseDouble(eats);
						eat/=sum;
						eat=(int)(-eat*360);
						System.out.println(eat);
					}
					
					String dailys = con.rate("日用", name,forwards, 								afterwards);
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
					
					String funs = con.rate("文教娱乐", name,forwards, 								afterwards);
		System.out.println(funs);
					if(funs==null) {
						fun=0;
					}else {
						fun = Double.parseDouble(funs);
						fun/=sum;
						fun=(int)(-fun*360);
						System.out.println(fun);
					}
					
					String otherss = con.rate("其他消费", name,forwards, 								afterwards);
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
				L[0] = new JLabel("   饮食消费："+(int)(-eat/3.6)+"%");
				L[0].setOpaque(true);
				L[0].setBackground(new Color(255,200,100));
				L[1] = new JLabel("   日用消费："+(int)(-daily/3.6)+"%");
				L[1].setOpaque(true);
				L[1].setBackground(new Color(255,100,200));
				L[2] = new JLabel(" 文教娱乐："+(int)(-fun/3.6)+"%");
				L[2].setOpaque(true);
				L[2].setBackground(new Color(255,100,100));
				L[3] = new JLabel(" 其他消费："+(int)(-others/3.6)+"%");
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
//									都是负的
				
			}
			this.setVisible(true);
			this.setSize(300,200);
			this.setLocation(450, 365);
			
		}else{
			JOptionPane.showMessageDialog(null, "这个月你没有消费");
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
			 DecimalFormat df = new DecimalFormat("#.00");//控制float输出格式
			 g.drawString("月消费："+df.format(-sum)+"元", 10, 10);
			 g.drawString("月收入："+df.format(jieyu)+"元", 10, 25);
			 g.drawString("结    余："+df.format((jieyu+sum))+"元", 10, 40);
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
