package test;

import java.sql.*;
//import com.mysql.jdbc.*;

public class Login {
	public static void main(String[] args){
		

//		String url = "jdbc:mysql://120.125.85.30:3306/test";
//		String user = "user01";
//		String password = "master3421";
		
		String url = "jdbc:mysql://120.125.85.30:3306/test?"
                + "user=user01&password=master3421&useUnicode=true&characterEncoding=UTF8";
		
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------
//			String in_name = "eric",in_passwd="321v";		
//			in_user(in_name,in_passwd,stmt);
//-----------------------------------------------------------		
//			String in_name = "davidfish";		
//			ask_pass(in_name,stmt);		
//-----------------------------------------------------------
//			String in_name = "davidfish",in_passwd="1312312f3";
//			System.out.println(pass_check(in_name,in_passwd,stmt));
//-----------------------------------------------------------
			conn.close(); }
		
		catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
//新增使用者帳號
	public static void in_user(String in_name,String in_passwd,Statement stmt) throws SQLException {
		String sql = "insert into user(name,passwd) values('"+in_name+"','"+in_passwd+"' )";
		stmt.executeUpdate(sql);
		
	}
	public static String ask_pass(String in_name,Statement stmt) throws SQLException {
		String sql = "select passwd from user where name = '"+in_name+"' ";
		ResultSet rs = stmt.executeQuery(sql);
		String passwd = null;
	      while(rs.next()){

	         String passwd0  = rs.getString("passwd");
//	         System.out.print("passwd: " + passwd0);
	         passwd=passwd0;
	      }
	      rs.close();
	      return passwd;
	}
	public static int pass_check(String in_name,String in_passwd,Statement stmt) throws SQLException  {
		String right_passwd=ask_pass(in_name,stmt);
		int check = 0;
		if(right_passwd.equals(in_passwd))check=1;
		return check;
	}
	
	public static int username_check(String in_name,Statement stmt){
		int check=0;
		
		return check;
	}
}
