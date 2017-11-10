package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SprintBacklogs {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();


	public SprintBacklogs(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" SprintBacklogs...");
		statement = null;
		sql = "INSERT INTO SprintBacklogs (SprintBacklogName) VALUE (?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String desc =  dataGen.makeDesc(8, 70);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1,desc);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
