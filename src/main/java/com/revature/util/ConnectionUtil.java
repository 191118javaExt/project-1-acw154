package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() {
		
		// The url includes the driver we're using, which is from postgres,
		// as well as the computer, which localhost, and the port, which is 5432
		// The last '/' is important, as it refers to the specific database
		// we are connecting to
		// However, since we only have 1 database, we can connect to that one
		// without specifying
		/*
		String url = "jdbc:postgresql://localhost:5432/postgres?currentschema=project1";
		String username = "postgres";
		String password = "postgres";
		*/
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties props = new Properties();
		Connection conn = null;
		
		// This may not be neccessary, but it is basically saying to 
		// search for files in the current project
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			props.load(loader.getResourceAsStream("connection.properties"));
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				logger.warn("Unable to obtain connection to database", e);
			}
		} catch (IOException e1) {
		}
		
		return conn;
	}
}