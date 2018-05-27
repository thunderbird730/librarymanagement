package Main;

import java.sql.SQLException;

import book.bookOperations;
import database.establishDbCon;
import userdata.userDetail;;

public class main {

	public static void main(String[] args) throws SQLException, Exception {
		//establishDbCon edc = new establishDbCon();
		//userDetail udet = new userDetail();
		bookOperations bopr = new bookOperations();
		try {
			//edc.getConnection();
			
			//udet.checkTable();			
			//udet.registerUser('P', "oaae123", "one", "one");
			//udet.getAllUserDetail();
			//udet.validateUser("one123");
			//udet.deleteUser("one123");
			
			bopr.addBook("Wizards of waverly place","J.K.Rowling", 6);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Here");
		}
	}
}
