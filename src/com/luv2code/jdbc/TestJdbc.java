package com.luv2code.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "";
		String user = "";
		String pass = "";

		try (InputStream input = TestJdbc.class.getClassLoader().getResourceAsStream("resources/dbconfig.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			jdbcUrl = prop.getProperty("db.url");
			user = prop.getProperty("db.user");
			pass = prop.getProperty("db.password");

			System.out.println("Url: " + jdbcUrl + ", user: " + user + ", password: " + pass);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Connection myConn = null;
		try {
			System.out.println("Connect to database: " + jdbcUrl + "...");

			myConn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
