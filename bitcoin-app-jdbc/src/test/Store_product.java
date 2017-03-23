package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Store_product {

	public static void main(String[] args) throws SQLException {
		
//-----------------------------------------------------------
//		新增店家擁有的產品
//			輸入：店號    產品號   數量
//			輸出	：無
//		in_store_product(store_number,product_number,product_quantity);
		
		int store_number = 1 , product_number=2 ,product_quantity = 3;
		in_store_product(store_number,product_number,product_quantity);
//-----------------------------------------------------------	

		
	}
	public static void in_store_product(int store_number, int product_number,int product_quantity) throws SQLException {
		String url = "jdbc:mysql://mcdf.asuscomm.com:3306/most-sql?" + "user=user&password=1234&useUnicode=true&characterEncoding=UTF8";	
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement stmt = conn.createStatement();
//-----------------------------------------------------------		
		String sql = "insert into store_product(store_number,product_number,product_quantity) values('"+store_number+"','"+product_number+"','"+product_quantity+"' )";
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
