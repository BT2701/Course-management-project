package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {

    public static Connection getConnection() {
		Connection c=null;
		try {
			//đăng kí driver bằng drivermanager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//các thông số của cơ sở dữ liệu
			String url="jdbc:mySQL://localhost:3306/school";
			String user="root";
			String password="";
			
			//tạo kết nối
			c=DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			new ThongBaoDialog("Chưa kết nối database", ThongBaoDialog.ERROR_DIALOG);
		}
		
		
		
		
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void printInfo(Connection c) {
		try {
			if(c!=null) {
				System.out.println(c.getMetaData().toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
