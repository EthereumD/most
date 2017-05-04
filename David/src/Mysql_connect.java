import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class Mysql_connect {

	private static Statement stat = null;
	private static Statement stat2 = null;
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	private static java.sql.PreparedStatement pst = null;
	private static String selectSQL = "SELECT * FROM david.login";
	private static String insertdbSQL_Product = "INSERT INTO `david`.`product`(`product_number`, `product_name`, `product_detail`) "
			+ "select ifNULL(max(`product_number`), 0)+1, ?, ? From `david`.`product`;";
	private static String insertdbSQL_store = "INSERT INTO `david`.`store`(`store_number`, `store_name`, `store_address`, `store_btc_address`) "
			+ "select ifNULL(max(`store_number`), 0)+100, ?, ?, ? From `david`.`store`;";
	private static String insertdbSQL_login = "INSERT INTO `david`.`login`(`storenumber`, `account`, `password`) "
			+ "select ifNULL(max(`storenumber`), 0)+100, ?, ? From `david`.`login`;";
	private static String updatsqldata = "SELECT product_name, product_detail, product_quantity "
			+ "FROM david.product, david.`store stock` "
			+ "where store_number = and david.product.product_number = david.`store stock`.product_number ";
	public static Connection con = null;
	public static Connection con2 = null;

	public static void main(String[] args) throws ClassNotFoundException {
		// Connectdb();
		// SelectTable_account(selectSQL);
		// search_name_btc(100);
		// update_table(100);
	}

	public static void Connectdb() {
		try {
			// 連接MySQL
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("連接成功MySQLToJava");
			// 建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			String datasource = "jdbc:mysql://localhost/david?user=root&password=p@$$w0rd";
			// 以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			con = DriverManager.getConnection(datasource);
			con2 = DriverManager.getConnection(datasource);
			System.out.println("連接成功MySQL");
			Statement stat = con.createStatement();
		} catch (Exception e) {
			System.out.println("?");
		} finally {
			Close();
		}
	}

	public static boolean SelectTable_account(String account, char[] password) {
		try {
			Connectdb();
			stat = con.createStatement();
			rs = stat.executeQuery("select password from david.login where login.account = '" + account + "'");
			while (rs.next()) {
				String pass = new String(password);
				if (pass.equals(rs.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return false;
	}

	public static String Insertdb_Product(String product_name, String product_detail) {
		String product_number = null;
		try {

			pst = con.prepareStatement(insertdbSQL_Product);

			pst.setString(1, product_name);
			pst.setString(2, product_detail);
			pst.executeUpdate();

			stat = con.createStatement();
			rs = stat.executeQuery("SELECT max(product_number) FROM david.product;");
			while (rs.next()) {
				product_number = rs.getString("max(product_number)");
				// return product_number;
			}
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return product_number;
	}

	private static void Close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
		} catch (SQLException e) {
			System.out.println("Close Exception :" + e.toString());
		}
	}

	public static int getstorenumber(String account) {
		try {
			Connectdb();
			stat = con.createStatement();
			rs = stat.executeQuery("select storenumber from david.login where login.account = '" + account + "'");
			while (rs.next()) {

				String storenumber = rs.getString("storenumber");
				int x = Integer.parseInt(storenumber);
				System.out.println(rs.getString("storenumber"));
				return x;
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return 0;
	}

	public static String search_name_btc(int no) {
		// TODO Auto-generated method stub
		String string = null;
		try {
			Connectdb();
			stat = con.createStatement();
			rs = stat.executeQuery("SELECT store_name, store_btc_address " + "FROM david.login, david.store s "
					+ "where s.store_number = " + no + " group by store_name");
			while (rs.next()) {
				string = rs.getString("store_name") + " " + rs.getString("store_btc_address");
				System.out.println(string);
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return string;
	}

	public static void update_table(DefaultTableModel model, int no) {

		// 清除table資料
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		try {
			Connectdb();
			stat = con.createStatement();
			rs = stat.executeQuery("SELECT product_name, product_detail, product_quantity "
					+ "FROM david.product, david.`store stock` " + "where store_number = " + no
					+ " and david.product.product_number = david.`store stock`.product_number ");
			while (rs.next()) {

				int i = 0;
				System.out.print(rs.getString("product_name") + " ");
				System.out.print(rs.getString("product_detail") + " ");
				System.out.println(rs.getString("product_quantity"));
				Object[] object = new Object[] { rs.getString("product_name"), rs.getString("product_detail"),
						rs.getString("product_quantity") };
				model.insertRow(i, object);
				i++;
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public static void Update_TableAll(DefaultTableModel model, int no) {

		// 清除table資料
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		try {
			Connectdb();
			stat = con.createStatement();
			stat2 = con2.createStatement();

			rs2 = stat2.executeQuery("SELECT product_name, product_detail " + "FROM david.product;");

			int i = 0;
			int a = 0;
			while (rs2.next()) {
				int flag = 0;
				
//				SELECT *, sum(product_quantity) FROM david.`store stock`
//				where david.`store stock`.store_number = 600 && product_number = 7
				
				rs = stat.executeQuery("SELECT product_name, product_detail, sum(product_quantity) as product_quantity "
						+ "FROM david.product, david.`store stock` "
						+ "where store_number = "+no+" and david.product.product_number = david.`store stock`.product_number "
								+ "group by david.product.product_number");
				while (rs.next()) {
					if (rs.getString("product_name").equals(rs2.getString("product_name"))
							&& rs.getString("product_detail").equals(rs2.getString("product_detail"))) {
						Object[] object = new Object[] { rs.getString("product_name"), rs.getString("product_detail"),
								rs.getString("product_quantity") };
						model.insertRow(i, object);
						i++;
						flag = 1;
						break;
					}
				}
				if (flag != 1) {
					Object[] object = new Object[] { rs2.getString("product_name"), rs2.getString("product_detail"),
							0 };
					model.insertRow(i, object);
					i++;
				}
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public void inserent_login(String acount, String password, String name, String addr, String btc_addr) {
		try {
			Connectdb();
			stat = con.createStatement();
			pst = con.prepareStatement(insertdbSQL_login);
			pst.setString(1, acount);
			pst.setString(2, password);
			pst.executeUpdate();

			pst = con.prepareStatement(insertdbSQL_store);
			pst.setString(1, name);
			pst.setString(2, addr);
			pst.setString(3, btc_addr);
			pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public static void Insert_Pstorestock(String store_no, String maxproductnumber, String date) {
		try {

			pst = con.prepareStatement(
					"INSERT INTO `david`.`store stock` (`store_number`, `product_number`, `product_quantity`, `date`) "
							+ "VALUES ('" + store_no + "', '" + maxproductnumber + "', '0', '" + date + "');");

			// pst.setString(1, product_name);
			// pst.setString(2, product_detail);
			// pst.setString(3, date);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	//String store_no, String maxproductnumber, quantity, String date
	public void Insertdata(int no, int product_number, String pquantity, String date) {
		// TODO Auto-generated method stub
		try {

			pst = con.prepareStatement(
					"INSERT INTO `david`.`store stock` (`store_number`, `product_number`, `product_quantity`, `date`) "
							+ "VALUES ('" + no + "', '" + product_number + "','" +pquantity+ "','"+ date + "');");

			// pst.setString(1, product_name);
			// pst.setString(2, product_detail);
			// pst.setString(3, date);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public int search_product_number(String pname, String pdetail) {
		// TODO Auto-generated method stub
		try {
			Connectdb();
			stat = con.createStatement();
			rs = stat.executeQuery("select product_number from david.product where product.product_name = '" + pname + "'&&product.product_detail = '" + pdetail + "'");
			while (rs.next()) {

				String storenumber = rs.getString("product_number");
				int x = Integer.parseInt(storenumber);
				System.out.println(rs.getString("product_number"));
				return x;
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return 0;
	}
}
