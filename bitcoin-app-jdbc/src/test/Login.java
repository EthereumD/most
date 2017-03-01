package test;

import java.sql.*;

public class Login {
	public static void main(String[] args){
			
//-----------------------------------------------------------
//		新增使用者
//			輸入：帳號 密碼
//			輸出	：無
//		in_user(name,passwd);
//-----------------------------------------------------------		
//		詢問使用者密碼
//			輸入：使用者名稱
//			輸出	：密碼
//		ask_pass(name);		
//-----------------------------------------------------------
//		檢查所輸入的帳號密碼是否匹配
//			輸入：帳號 密碼
//			輸出	：0 or 1
//		pass_check(name,passwd)
//-----------------------------------------------------------
//		修改密碼
//			輸入：使用者 舊密碼  新密碼
//			輸出	：0 or 1
//		pass_change(name,old_passwd,new_passwd)
//-----------------------------------------------------------
		}

	public static void in_user(String in_name,String in_passwd) throws SQLException {
		
		String url = "jdbc:mysql://120.125.85.30:3306/test?" + "user=user01&password=master3421&useUnicode=true&characterEncoding=UTF8";	
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------		
		String sql = "insert into user(name,passwd) values('"+in_name+"','"+in_passwd+"' )";
		stmt.executeUpdate(sql);
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
	public static String ask_pass(String in_name) throws SQLException {
		String url = "jdbc:mysql://120.125.85.30:3306/test?" + "user=user01&password=master3421&useUnicode=true&characterEncoding=UTF8";	
		String driver = "com.mysql.jdbc.Driver";
		String passwd = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------		
			String sql = "select passwd from user where name = '"+in_name+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			
		      while(rs.next()){

		         String passwd0  = rs.getString("passwd");
//		         System.out.print("passwd: " + passwd0);
		         passwd=passwd0;
		      }
		      rs.close();
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
		return passwd;
	}
	public static int pass_check(String in_name,String in_passwd) throws SQLException  {
		int check = 0;		
//-----------------------------------------------------------		
			String right_passwd=ask_pass(in_name);
			if(right_passwd.equals(in_passwd))check=1;
//-----------------------------------------------------------				
		return check;
	}
	public static int username_check(String in_name){
		int check=0;
		
		return check;
	}
	public static int pass_change(String in_name,String old_passwd,String new_passwd) throws SQLException{
		int success = 0;
		
		String url = "jdbc:mysql://120.125.85.30:3306/test?" + "user=user01&password=master3421&useUnicode=true&characterEncoding=UTF8";	
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------		
			if(pass_check(in_name,old_passwd)==1){
				String sql = "update user set passwd = '"+new_passwd+"' where name ='"+new_passwd+"' )";
				stmt.executeUpdate(sql);
				success = 1;
			}
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
		
		
		
		return success;
		
	}
}
