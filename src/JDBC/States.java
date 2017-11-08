package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class States {
	
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public States(Connection con){
	myConn = con;
	}
	
	public void tableInsert() {
		System.out.println("Inserting states...");
		String[] tab = {"TODO1", "TODO2", "TODO3", "TODO4"};
		statement = null;
		
		for(int quantity = 0; quantity < tab.length; quantity++) {
			
			sql = "INSERT INTO states (StateValue) VALUE (?)";
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, tab[quantity]);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		System.out.println("Done");
	}
}