package userdata;

import java.sql.*;
import database.establishDbCon;

public class userDetail {
	
	private static establishDbCon conn = new establishDbCon();
	
	public static void checkTable() throws SQLException,Exception{
		//Retrieving connection
	    String sql = "CREATE TABLE IF NOT EXISTS LIBRARYMANAGEMENT.userdetails (\n"
	            + "    user CHAR(1) NOT NULL,\n"
	    		+ "    uname VARCHAR(45) NOT NULL,\n"
	            + "    first_name VARCHAR(45) NOT NULL,\n"
	            + "    last_name VARCHAR(45) NOT NULL\n"
	            + ");";
		
		//Handling dynamic table creation
		System.out.println("Checking user detail table existence...");
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.execute();
		System.out.println("Table existence check completed !");
	}
	
	public void registerUser(char user, String username,String fname, String lname) throws SQLException, Exception{
		if(searchUser(username)==false) {
		if(user == 'U' || user == 'A' || user == 'L'){
			System.out.println("Inserting Values");
			String sql = "INSERT INTO librarymanagement.userdetails VALUES(\'" + user + "\',\'" + username.trim() + "\',\'" + fname.trim() + "\',\'" + lname.trim() + "\');";
			System.out.println("Framed sql statement is as : "+ sql);
			PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
			stmt.execute();
			System.out.println("User Registered Successfully !");
	    }else {
	    	System.out.println("Invalid User Privilages !");
	    }
		} else {
			System.out.println("Alas! Username already exist, try a different one.");
		}
	}
	
	public void deleteUser(String username) throws SQLException, Exception{
		Statement stmt = conn.getConnection().createStatement();
		if(searchUser(username)==true) {
			stmt.executeUpdate("DELETE from librarymanagement.userdetails WHERE uname = \'"+username+"\';");
			System.out.println(username+" user deleted!");
		} else {
			System.out.println("Can\'t delete user: "+username+", no such user present!");
		}
	}
	
	public void updateUserDetail() {
		
	}

	public Boolean searchUser(String username) throws SQLException, Exception{
		 Boolean val = false;
		 Statement stmt = conn.getConnection().createStatement();
		 ResultSet rs = stmt.executeQuery("select uname from librarymanagement.userdetails");
		 while(rs.next()) {
			 if(username.equals(rs.getString(1))) {
				 val = true;
			 } 
		 }
		 return val;
	}
	
	public void getAllUserDetail() throws SQLException, Exception {
	    Statement stmt = conn.getConnection().createStatement();
	    ResultSet rs = stmt.executeQuery("select * from librarymanagement.userdetails");
	    
	    while(rs.next()) {
	    	System.out.println(rs.getString(1) +'\t'+ rs.getString(2) +'\t'+ rs.getString(3) +'\t'+ rs.getString(4) );
	    }
	}
	
	public void validateUser(String username) throws SQLException, Exception {
		 Statement stmt = conn.getConnection().createStatement();
		 ResultSet rs = stmt.executeQuery("select user,uname from librarymanagement.userdetails");
		 while(rs.next()) {
			 //System.out.println("Value: " + rs.next());
			 if(username.equals(rs.getString(2))) {				 
				 System.out.println(rs.getString(2) + " is a valid user.");
				 if(rs.getString(1).equals('A')) {
					 System.out.println("Administrator Level Accessess Granted !");
				 }else if(rs.getString(1).equals('L')) {
					 System.out.println("Librarian Level Accessess Granted!");
				 }else {
					 System.out.println("User Level Accessess Granted!");
				 }
			 }else {
				 System.out.println(rs.getString(2) + " is not a valid User"); 
			 } 
		 }
	}
}
