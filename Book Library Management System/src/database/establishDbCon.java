package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class establishDbCon {
	public static Connection getConnection() throws Exception {		
		try {			
			String driver = "com.mysql.cj.jdbc.Driver" ;
			//String url1 	  = "jdbc:mysql://localhost:3306/librarymanagement?useSSL=true" ;  //DBName: librarymanagement(Non Dynamic Case) 
			String url2   = "jdbc:mysql://localhost:3306?useSSL=true";
			String uname  = "userone";
			String pwd    = "one";
			
			//Register Driver
			Class.forName(driver);
			
			//SQL command to create a database in MySQL.
	        String sql = "CREATE DATABASE IF NOT EXISTS LIBRARYMANAGEMENT;";  //Case insensitive
			
			//Establish Connection
			System.out.println("> Trying to establish connection now...");
			Connection conn = DriverManager.getConnection(url2, uname, pwd);
			System.out.println("  Connection Established !");
			
			//Handling dynamic database creation
			System.out.println("> Checking database stability...");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			System.out.println("  Database stablility check completed !");
			
			return conn;
		}catch(Exception e) {
			System.out.println(e.getMessage()); 
			System.out.println("Connection Not Established ! :( ");
		
			return null;
		}		
	}
}