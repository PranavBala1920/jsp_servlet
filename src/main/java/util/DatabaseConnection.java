package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	private DatabaseConnection() {
		throw new IllegalStateException("Utility class");
	}

	public static Connection createConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/employees";
		String user = "root";
		String password = "Pranav@1920@";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}