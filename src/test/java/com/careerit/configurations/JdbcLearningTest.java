package com.careerit.configurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class JdbcLearningTest {

	private static final String GET_PRODUCTS = "select pid,pname,price from product";
	private static final String ADD_PRODUCT = "insert into product(pname,price) values(?,?)";
	private static final String DEL_PRODUCT = "delete from product where pname = ?";

	@Test
	public void getProductInformation() {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(GET_PRODUCTS);
			while (rs.next()) {
				int pid = rs.getInt("pid");
				String pname = rs.getString("pname");
				float price = rs.getFloat("price");
				System.out.printf("%d %s %f\n", pid, pname, price);
			}
		} catch (SQLException e) {
			System.out.println("While getting products : " + e);
		} finally {
			close(rs, st, con);
		}

	}

	@Test
	public void addProductTest() {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getConnection();
			pst = con.prepareStatement(ADD_PRODUCT);
			pst.setString(1, "Dell Inspiron");
			pst.setFloat(2, 38000);
			int res = pst.executeUpdate();
			if (res != 0) {
				System.out.println("Product is added successfully");
			} else {
				System.out.println("Product couldn't be added...");
			}
		} catch (SQLException e) {
			System.out.println("While adding product: " + e);
		} finally {
			close(pst, con);
		}
	}

	@Test
	public void deleteProductTest() {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getConnection();
			pst = con.prepareStatement(DEL_PRODUCT);
			String pname = "'Dell Studio15' or 1 = 1";
			pst.setString(1, pname);
			System.out.println(pst);
			int res = pst.executeUpdate();
			
			if (res != 0) {
				System.out.println("Product is deleted successfully");
			} else {
				System.out.println("Product couldn't be deleted...");
			}
		} catch (SQLException e) {
			System.out.println("While deleting product: " + e);
		} finally {
			close(pst, con);
		}
	}

	
	private Connection getConnection() {
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

	private void close(ResultSet rs, Statement st, Connection con) {
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

	private void close(Statement st, Connection con) {
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
	
	// execute()               => DDL 
	// executeQuery()          => DQL 
	// executeUpdate();        => DML
}
