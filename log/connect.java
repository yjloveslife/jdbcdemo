package log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class connect {
	Connection con = null;
	String sql = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultSet = null;
	public Connection getConnection()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 System.out.println("���ݿ��������سɹ�");
	            String url="jdbc:mysql://127.0.0.1:3306/userr" +
	            		"?useUnicode=true&" +
	            		"characterEncoding=utf8" +
	            		"&serverTimezone=GMT%2B8" +
	            		"&useSSL=false" ;

	            String user="root";
	            String passWord="123456";
	            //System.out.println("1");
	            //Connection����������java.sql.Connection��
	            con=(Connection)DriverManager.getConnection(url,user,passWord); //��������
	            System.out.println("�ѳɹ��������ݿ�MySQL�������ӣ���");
	        }catch(Exception e){
	            e.printStackTrace();}
	        return con;		
	}
	////////����user��
	public boolean isinside(String name1,String password1) {
		con=this.getConnection();
		sql = "select * from user where name = ? and password = ? ";
		try {
		preparedstatement = (PreparedStatement)con.prepareStatement(sql);
		preparedstatement.setString(1, name1);
		preparedstatement.setString(2, password1);
		ResultSet resultSet=preparedstatement.executeQuery();
			if(resultSet.next()) {
				con.close();
				return true;
			}else {
				con.close();
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isinside(String name1) {
		con=this.getConnection();
		sql = "select * from user where name = ? ";
		try {
		preparedstatement = (PreparedStatement)con.prepareStatement(sql);
		preparedstatement.setString(1, name1);
		resultSet=preparedstatement.executeQuery();
		if(resultSet.next()) {
			con.close();
			return true;
		}else {
			con.close();
			return false;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void insert(String name1,String password1) {
		con=this.getConnection();
		sql = "insert into user(name,password) values(?,?)";
		try {
		preparedstatement = (PreparedStatement)con.prepareStatement(sql);
		preparedstatement.setString(1, name1);
		preparedstatement.setString(2, password1);
		preparedstatement.execute();
		con.close();
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	///////////message��
	public void show(float a[],String name) {
		con = this.getConnection();
		try {
			sql = "select sum(inandout)as'֧��' from message where name=? and inandout<0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a[0] = resultSet.getFloat("֧��");
			}
			sql = "select sum(inandout) from message where name=? and inandout>0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a[1] = resultSet.getFloat(1);
			}
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String rate(String type, String name,String forwards,String afterwards) {
		con = this.getConnection();
		String a = null;
		try {
			sql = "select sum(inandout) from message where type =? and name = ? and time1>? and time1<?";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, type);
			preparedstatement.setString(2, name);
			preparedstatement.setString(3, forwards);
			preparedstatement.setString(4, afterwards);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a = resultSet.getString(1);
			}else {
				return null;
			}
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
		
	}
	public String getmonthly(String name,String forwards,String afterwards) {
		con = this.getConnection();
		String a = null;
		try {
			sql = "select sum(inandout) from message where name = ? and time1>? and time1<? and inandout<0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, forwards);
			preparedstatement.setString(3, afterwards);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a = resultSet.getString(1);
			}
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	public float getmonthly2(String name,String forwards,String afterwards) {
		con = this.getConnection();
		float a = 0;
		try {
			sql = "select sum(inandout) from message where name = ? and time1>? and time1<? and inandout>0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, forwards);
			preparedstatement.setString(3, afterwards);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a = resultSet.getFloat(1);
			}
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	public boolean add(String name,float a,String t,String time) {
		con = this.getConnection();
		String type = t;
		try {
			sql = "insert into message values(?,?,?,?)";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);			
			preparedstatement.setString(1, name);
			preparedstatement.setFloat(2, a);
			preparedstatement.setString(3, type);
			preparedstatement.setString(4, time);
			preparedstatement.execute();
			JOptionPane.showMessageDialog(null, "�ɹ�");
			con.close();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(String name,float a,String t,String time) {
		con = this.getConnection();
		String type = t;
		try {
			sql = "delete from message where name=? and inandout=? and type=? and time1=?";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);			
			preparedstatement.setString(1, name);
			preparedstatement.setFloat(2, a);
			preparedstatement.setString(3, type);
			preparedstatement.setString(4, time);
			preparedstatement.execute();
			JOptionPane.showMessageDialog(null, "�ɹ�");
			con.close();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean isin(String name,float a,String t,String time) {
		con = this.getConnection();
		String type = t;
		try {
			sql = "select* from message where name=? and inandout=? and type=? and time1=?";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);			
			preparedstatement.setString(1, name);
			preparedstatement.setFloat(2, a);
			preparedstatement.setString(3, type);
			preparedstatement.setString(4, time);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				con.close();
				return true;
			}else {
				con.close();
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void detail1(Vector<Vector> vec,String name) {
			con = this.getConnection();
		Vector v;
		try {
			sql = "select * from message where name = ?";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);			
			preparedstatement.setString(1, name);
			resultSet = preparedstatement.executeQuery();
			message m = null;
//			arr = new ArrayList<message>();
			while(resultSet.next()) {
//				m = new message();
//				m.setType(resultSet.getString(3));
//				m.setMoney(resultSet.getFloat(2));
				v = new Vector();
//					System.out.println(resultSet.getString(3)+resultSet.getFloat(2));
//				vec.add(m);
				v.add(resultSet.getString(3));
				v.add(resultSet.getFloat(2));
				v.add(resultSet.getString(4));//ֱ�ӻ�ȡString�ͣ���Ȼʱ���û
				vec.add(v);
			}
//			for(int i=0;i<arr.size();i++)
//				System.out.println(arr.get(i).getClass()+arr.get(i).getType());
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�Դ˼����α����Ҫ��
//	public void test() {
//		con = this.getConnection();
//		sql = "select inandout from message where name= ? and inandout<0";
//		try {
//			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
//			preparedstatement.setString(1, "a");
//			resultSet = preparedstatement.executeQuery();
//			resultSet.next();
//			System.out.println(resultSet.getFloat(1));
//			
//		}catch(SQLException e ) {
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) {
//		new connect().test();
//	}
}
