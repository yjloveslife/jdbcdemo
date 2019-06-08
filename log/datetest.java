package log;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Date;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class datetest extends JFrame {
	
	public  datetest() {
		this.setVisible(true);
		this.setSize(200, 200);
		JPanel pp = new JPanel();
//		pp.add(new mypanel());
		this.add(pp,BorderLayout.CENTER);
//		this.add(new mypanel1());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(getDaysOfMonth(new Date(2019,1,1)));
		String nul = null;
//		System.out.println(nul.equals(null));
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date());
		System.out.println(c.getMaximum(Calendar.HOUR_OF_DAY));
		System.out.println(c.get(c.HOUR_OF_DAY));
	}
	public static void main(String[] args) {
//		Calendar c = Calendar.getInstance();
//		System.out.println(Calendar.getInstance().get(Calendar.HOUR));
		new datetest();
	}
	public  int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

}
class mypanel1 extends JPanel{
	 public void paint(Graphics g) {
		 Graphics2D g2d = (Graphics2D)g;
		 g.drawArc(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2, 180,0 );
	 }
}
