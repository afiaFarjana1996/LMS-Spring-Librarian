package com.ss.LMSBorrower.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DBConnection {
	static Connection con = null;

	public static Connection getConnection() {
		if (con != null)
			return con;

		return getConnection("library", "root", "smoothstack");
	}

	private static Connection getConnection(String db_name, String user_name, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + db_name + "?serverTimezone=UTC",
					user_name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	

}
