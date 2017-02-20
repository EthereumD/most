package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class store {

	public static void main(String[] args){
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.125.85.30:3306/test";
		String user = "user01";
		String password = "master3421";

		try {
			
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------			
			int store_num=33;
			String store_name = "7-11",store_btc="1F1tAaz5x1HUXrCNLbtMDqcw6o5GNn4xqX";
						
			String sql = in_store(store_num,store_name,store_btc);
					
			stmt.executeUpdate(sql);
			conn.close(); }
//-----------------------------------------------------------
		catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
//新增店家
	public static String in_store(int store_num,String store_name,String store_btc) {
		String sql = "insert into store(store_num,store_name,store_btc) values('"+store_num+"','"+store_name+"','"+store_btc+"' )";
		return sql;
	}

}
