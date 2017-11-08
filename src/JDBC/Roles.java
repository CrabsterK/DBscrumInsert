package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Roles {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public Roles(Connection con){
		myConn = con;
	}
	
	public void tableInsert() {
		System.out.println("Inserting Roles...");
		String[] tab = {"TODO1", "TODO2", "TODO3", "TODO4"};
		statement = null;
		
		for(int quantity = 0; quantity < tab.length; quantity++) {
			
			sql = "INSERT INTO roles (RoleValue) VALUE (?)";
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
