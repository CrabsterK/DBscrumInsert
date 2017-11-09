package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketTypes {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public TicketTypes(Connection con){
		myConn = con;
		}
	
	public void tableInsert() {
		System.out.println("Inserting TicketTypes...");
		String[] tab = {"TODO1", "TODO2", "TODO3", "TODO4", "TODO5"};
		statement = null;
		
		for(int quantity = 0; quantity < tab.length; quantity++) {
			
			sql = "INSERT INTO TicketTypes (TypeValue) VALUE (?)";
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
