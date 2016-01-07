package com.socnet.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.socnet.dao.DBConnection;

@Path("/show")
public class FriendList {
	@GET
	@Path("/friend")
	@Produces(MediaType.APPLICATION_JSON)
	public String showFriend(@QueryParam("userid") int userid) {
		System.out.println(userid);
		JSONArray jsArr = new JSONArray();
		Connection dbConn = null;
		try {
			Connection conn = DBConnection.createConnection();

			// Query
			Statement statement = conn.createStatement();
			String queryString = "select users.USERNAME, users.AVARTAR_IMAGE from [USER] as users join [FRIENDLIST] as friend ON users.USERID = friend.FRIENDID where friend.OWNERID = "
					+ userid + " and friend.ACCEPTED = 1";
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("username", rs.getString("USERNAME"));
				obj.put("avatarImage", rs.getString("AVARTAR_IMAGE"));
				jsArr.put(obj);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsArr.toString();
	}
}
