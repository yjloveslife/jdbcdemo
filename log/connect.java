package log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class connect {
	Connection con = null;
	String sql = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultSet = null;
	public Connection getConnection()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 System.out.println("数据库驱动加载成功");
	            String url="jdbc:mysql://127.0.0.1:3306/userr" +
	            		"?useUnicode=true&" +
	            		"characterEncoding=utf8" +
	            		"&serverTimezone=GMT%2B8" +
	            		"&useSSL=false" ;

	            String user="root";
	            String passWord="123456";
	            //System.out.println("1");
	            //Connection对象引的是java.sql.Connection包
	            con=(Connection)DriverManager.getConnection(url,user,passWord); //创建连接
	            System.out.println("已成功的与数据库MySQL建立连接！！");
	        }catch(Exception e){
	            e.printStackTrace();}
	        return con;		
	}
	////////连接user表
	public boolean isinside(String name1,String password1) {
		con=this.getConnection();
		sql = "select * from user where name = ? and password = ? ";
		try {
		preparedstatement = (PreparedStatement)con.prepareStatement(sql);
		preparedstatement.setString(1, name1);
		preparedstatement.setString(2, password1);
		ResultSet resultSet=preparedstatement.executeQuery();
		if(resultSet.next()) {
			return true;
		}else
			return false;
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
			return true;
		}else
			return false;
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
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	///////////message表
	public void show(float a[],String name) {
		con = this.getConnection();
		try {
			sql = "select sum(inandout)as'支出' from message where name=? and inandout<0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a[0] = resultSet.getFloat("支出");
			}
			sql = "select sum(inandout) from message where name=? and inandout>0";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);
			preparedstatement.setString(1, name);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				a[1] = resultSet.getFloat(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(String name,float a,String t) {
		con = this.getConnection();
		String type = t;
		try {
			sql = "insert into message values(?,?,?)";
			preparedstatement = (PreparedStatement)con.prepareStatement(sql);			
			preparedstatement.setString(1, name);
			preparedstatement.setFloat(2, a);
			preparedstatement.setString(3, type);
			preparedstatement.execute();
			JOptionPane.showMessageDialog(null, "成功");
			return true;
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
				m = new message();
				m.setType(resultSet.getString(3));
				m.setMoney(resultSet.getFloat(2));
				v = new Vector();
//					System.out.println(resultSet.getString(3)+resultSet.getFloat(2));
//				vec.add(m);
				v.add(resultSet.getString(3));
				v.add(resultSet.getFloat(2));
				vec.add(v);
			}
//			for(int i=0;i<arr.size();i++)
//				System.out.println(arr.get(i).getClass()+arr.get(i).getType());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//以此纪念游标的重要性
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
