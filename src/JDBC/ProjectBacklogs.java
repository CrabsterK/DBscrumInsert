package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectBacklogs {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();
	
	public ProjectBacklogs(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" ProjectBacklogs...");
		statement = null;
		sql = "INSERT INTO ProjectBacklogs (projectBacklogDesc) VALUE (?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String desc = dataGen.makeDesc(8, 50);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, desc);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
