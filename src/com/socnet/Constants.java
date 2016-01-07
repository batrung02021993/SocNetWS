package com.socnet;

public class Constants {

	/* --- MySQL on local host --- */
	// public static String dbClass = "com.mysql.jdbc.Driver";
	// private static String dbName = "users";
	// public static String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
	// public static String dbUser = "root";
	// public static String dbPwd = "root";

	/* --- Microsoft SQL Server on somee.com --- */
	// public static String dbClass =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// public static String dbUrl =
	// "jdbc:sqlserver://dbMTOManager.mssql.somee.com";
	// public static String dbUser = "nguyenbatrung_SQLLogin_1";
	// public static String dbPwd = "cuojk6z4w6";

	/* --- Microsoft SQL Server on local host --- */
	public static String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbName = "socnetdb";
	public static String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName="+dbName;// Must open port 1433 TCP/IP in SQL Server Configuration Manager
	public static String dbUser = "sa";
	public static String dbPwd = "password";
}
