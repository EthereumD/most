package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class store {
	
	public static void main(String[] args) throws SQLException{
		
//-----------------------------------------------------------
//				新增店家
//					輸入：店號 , 店名 , 地址 , 比特幣地址
//					輸出	：無
//					in_store(store_number, store_name,store_address,store_btc_address)
		int store_number=123;
		String store_name = "first bank" , store_address= "Taipei",store_btc_address= "15tDRUTA2DAWjq1aXXtGeHsbPyRWxaCv71";
		in_store(store_number, store_name,store_address,store_btc_address);
//-----------------------------------------------------------		
		
		}
	
	
	public static void in_store(int store_number, String store_name,String store_address,String store_btc_address) throws SQLException {
		String url = "jdbc:mysql://mcdf.asuscomm.com:3306/most-sql?" + "user=user&password=1234&useUnicode=true&characterEncoding=UTF8";	
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------		
		String sql = "insert into store(store_number, store_name,store_address,store_btc_address) values('"+store_number+"','"+store_name+"','"+store_address+"','"+store_btc_address+"' )";
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
	

}
