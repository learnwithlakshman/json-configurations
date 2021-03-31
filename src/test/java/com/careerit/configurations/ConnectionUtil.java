package com.careerit.configurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/test/resources/application.properties"));
			con = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
					properties.getProperty("db.password"));

		} catch (IOException e) {
			System.out.println("Configuration file not found :" + e);
		} catch (SQLException e) {
			System.out.println("While connecting with db: " + e);
		}
		return con;
	}

	public void close(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("While closing resources :" + e);
		}
	}

	public void close(Statement st, Connection con) {
		try {

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("While closing resources :" + e);
		}
	}
	
}
