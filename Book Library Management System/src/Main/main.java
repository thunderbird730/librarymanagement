package Main;

import database.establishDbCon;
import userdata.userDetail;;

public class main {

	public static void main(String[] args) {
		//establishDbCon edc = new establishDbCon();
		userDetail udet = new userDetail();
		try {
			//edc.getConnection();
			//udet.checkTable();			
			//udet.registerUser('P', "oaae123", "one", "one");
			//udet.getAllUserDetail();
			//udet.validateUser("one123");
			//udet.deleteUser("one123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Here");
		}
	}
}
