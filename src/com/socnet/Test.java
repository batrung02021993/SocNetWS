package com.socnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.socnet.dao.DBConnection;

public class Test {

	public static void main(String[] args) {
		try {
			Connection conn = DBConnection.createConnection();

			// Query
			Statement statement = conn.createStatement();
//			String queryString = "select * from sysobjects where type='u'";
			String queryString = "select * from [user]";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				System.out.println(rs.getString(7));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}