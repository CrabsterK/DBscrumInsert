package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Teams {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();
	
	public Teams(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Teams...");
		statement = null;
		sql = "INSERT INTO Teams (TeamName) VALUES (?);";


		for(int quantity = 1; quantity <= amount; quantity++) {
			String name =dataGen.makeWord(4, 15, 1);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
	
}
