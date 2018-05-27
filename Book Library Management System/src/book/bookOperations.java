package book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.establishDbCon;

public class bookOperations {

	private static establishDbCon conn = new establishDbCon();
	
	public bookOperations() throws SQLException, Exception {
			//Retrieving connection
		    String sql = "CREATE TABLE IF NOT EXISTS LIBRARYMANAGEMENT.bookdetails (\n"
		            + "    bookname VARCHAR(500) NOT NULL,\n"
		    		+ "    author VARCHAR(500) NOT NULL,\n"
		            + "    copies INT(5) NOT NULL\n"
		            + ");";
			
			//Handling dynamic table creation
			System.out.println("Checking book detail table existence...");
			PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
			stmt.execute();
			System.out.println("Book table existence check completed !");
		}
	
	public void addBook(String bookname, String author,int copies) throws SQLException, Exception{
		if(searchBook(bookname)==false) {
			
				System.out.println("Adding book details to database...");
				String sql = "INSERT INTO librarymanagement.bookdetails VALUES(\'" + bookname.trim() + "\',\'" + author.trim() + "\',\'" + copies + "\');";
				System.out.println("Framed sql statement is as : "+ sql);
				PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
				stmt.execute();
				System.out.println("Book Details added Successfully !");
		} else {
			System.out.println("Book details already exist in database!");
		}
	}
	
	public void deleteBook(String bookname) throws SQLException, Exception{
		Statement stmt = conn.getConnection().createStatement();
		if(searchBook(bookname)==true) {
			stmt.executeUpdate("DELETE from librarymanagement.userdetails WHERE uname = \'"+bookname+"\';");
			System.out.println(bookname+" deleted!");
		} else {
			System.out.println("Can\'t delete book: "+bookname+", no such book present!");
		}
	}
	
	public Boolean searchBook(String bookname) throws SQLException, Exception{
		 Boolean val = false;
		 Statement stmt = conn.getConnection().createStatement();
		 ResultSet rs = stmt.executeQuery("select bookname from librarymanagement.bookdetails");
		 while(rs.next()) {
			 if(bookname.equals(rs.getString(1))) {
				 val = true;
			 } 
		 }
		 return val;
	}
	
	public void getAllBookDetail() throws SQLException, Exception {
	    Statement stmt = conn.getConnection().createStatement();
	    ResultSet rs = stmt.executeQuery("select * from librarymanagement.bookdetails");
	    
	    while(rs.next()) {
	    	System.out.println(rs.getString(1) +'\t'+ rs.getString(2) +'\t'+ rs.getInt(3) );
	    }
	}
	
	public void updateBookDetail() throws SQLException, Exception {
		
	}
}

