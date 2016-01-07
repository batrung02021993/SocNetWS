package com.socnet.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;

import com.socnet.Utility;
import com.socnet.dao.DBConnection;

@Path("/login")
public class Login {
	int userId;

	@GET
	@Path("/dologin")
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(@QueryParam("phone") String phone, @QueryParam("password") String pwd) {
		System.out.println("phone: " + phone);
		System.out.println("password: " + pwd);

		String response = "";
		if (checkCredentials(phone, pwd)) {
			response = Utility.constructJSON("login", true, userId);
		} else {
			response = Utility.constructJSON("login", false, "Số điện thoại hoặc mật khẩu không đúng");
		}
		return response;
	}

	private boolean checkCredentials(String phone, String pwd) {
		boolean result = false;

		if (Utility.isNotNull(phone) && Utility.isNotNull(pwd)) {
			try {
				result = checkLogin(phone, pwd);
			} catch (Exception e) {
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}

	private boolean checkLogin(String phone, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM [user] WHERE phone = '" + phone + "' AND password=" + "'" + pwd + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				userId = rs.getInt(1);
				isUserAvailable = true;
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
		return isUserAvailable;
	}
}
