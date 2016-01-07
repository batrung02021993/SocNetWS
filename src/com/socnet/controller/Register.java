package com.socnet.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.socnet.Utility;
import com.socnet.dao.DBConnection;

@Path("/register")
public class Register {
	@POST
	@Path("/doregister")
	@Produces(MediaType.APPLICATION_JSON)
	public String doRegister(@FormParam("username") String username, @FormParam("phone") String phone,
			@FormParam("password") String pwd) {
		System.out.println("username: " + username);
		System.out.println("phone: " + phone);
		System.out.println("password: " + pwd);

		String response = "";
		try {
			int retCode = registerUser(username, phone, pwd);
			if (retCode == 0) {
				response = Utility.constructJSON("register", true);
			} else if (retCode == 1) {
				response = Utility.constructJSON("register", false, "Số điện thoại này đã đăng ký tài khoản");
			} else if (retCode == -1) {
				response = Utility.constructJSON("register", false, "Error occured");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return response;
	}

	private int registerUser(String username, String phone, String pwd) throws SQLException {
		int result = -1;

		Connection dbConn = null;
		try {
			dbConn = DBConnection.createConnection();
			String query = "select * from [USER] where [PHONE] = '" + phone + "'";
			Statement stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				result = 1;
			} else if (insertUser(username, phone, pwd)) {
				result = 0;
			} else {
				result = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}

		return result;
	}

	private boolean insertUser(String username, String phone, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();

			String query = "INSERT into [user] (username, phone, password) values ('" + username + "'," + "'" + phone
					+ "','" + pwd + "')";
			// System.out.println(query);
			int records = stmt.executeUpdate(query);
			System.out.println("statement");
			// System.out.println(records);
			// When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}

		return insertStatus;
	}

}
