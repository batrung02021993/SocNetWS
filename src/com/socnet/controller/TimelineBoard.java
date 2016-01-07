package com.socnet.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

//import org.codehaus.jettison.json.JSONArray;
//import org.codehaus.jettison.json.JSONObject;

import com.socnet.dao.DBConnection;

@Path("/timeline")
public class TimelineBoard {

	@GET
	@Path("/response")
	@Produces(MediaType.APPLICATION_JSON)
	public String responseTimeline(@QueryParam("userid") int userId) throws SQLException {
		System.out.println("userId=" + userId);
		String response = "";
		Connection conn = null;
		try {
			conn = DBConnection.createConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM [post] WHERE userid = " + userId;
			ResultSet rs = stmt.executeQuery(query);

			JSONArray jArr = new JSONArray();
			while (rs.next()) {
				JSONObject jObj = new JSONObject();
				jObj.put("postId", rs.getInt(1));
				jObj.put("userId", rs.getInt(2));

				// Format date time
				Timestamp timeStamp = rs.getTimestamp(3);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(timeStamp.getTime());
				jObj.put("time", dateFormat.format(date));
				// jObj.put("time", rs.getDate(3));

				jObj.put("content", rs.getString(4));
				jObj.put("image", rs.getString(5));
				jObj.put("like", rs.getInt(6));
				jObj.put("comment", rs.getInt(7));

				jArr.put(jObj);
			}
			response = jArr.toString();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				conn.close();
			}
		}
		return response;
	}
}
