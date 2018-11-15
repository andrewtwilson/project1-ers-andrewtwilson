package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url;
		String port;
		String dbName;
		String dbSchema;
		String username;
		String password;
		
		if("USE_PROPERTY".equals(System.getProperty("USE_PROPERTY"))) {
			url = System.getProperty("DB_URL");
			port = System.getProperty("DB_PORT");
			dbName = System.getProperty("DB_NAME");
			dbSchema = System.getProperty("DB_SCHEMA");
			username = System.getProperty("DB_USERNAME");
			password = System.getProperty("DB_PASSWORD");
		} else {
			url = System.getenv("DB_URL");
			port = System.getenv("DB_PORT");
			dbName = System.getenv("DB_NAME");
			dbSchema = System.getenv("DB_SCHEMA");
			username = System.getenv("DB_USERNAME");
			password = System.getenv("DB_PASSWORD");
		}

		String dataSource = "jdbc:postgresql://" + url + ":" + port + "/" + dbName + "?currentSchema=" + dbSchema;

		return DriverManager.getConnection(dataSource, username, password);
	}
}
