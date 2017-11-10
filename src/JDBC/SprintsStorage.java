package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SprintsStorage {
	final int SPRINT_TIME = 30;
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();
	
	public SprintsStorage(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" SprintsStorage...");
		statement = null;
		sql = "INSERT INTO SprintsStorage (SprintName,SprintTime,IdProject,IdSprintBacklog,ArchiveDate) VALUES (?, ?, ?, ?, ?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String name = dataGen.makeWord(4, 15);
			String date = dataGen.makeDate(2000, 2016);
			int idproject = dataGen.randBetween(1, 10);
			int idsprintbacklog = dataGen.randBetween(1, 10);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setInt(2, SPRINT_TIME);
				statement.setInt(3, idproject);
				statement.setInt(4, idsprintbacklog);
				statement.setString(5, date);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
