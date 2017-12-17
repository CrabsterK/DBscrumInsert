package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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

		sql = "SELECT IdProject FROM Projects;";
		ArrayList<Integer> idProjects = dataGen.getIdList(myConn, sql, "IdProject");

		sql = "SELECT IdSprintBacklog FROM SprintBacklogs;";
		ArrayList<Integer> idSprintBacklogs = dataGen.getIdList(myConn, sql, "IdSprintBacklog");

		sql = "INSERT INTO SprintsStorage (SprintName,SprintTime,IdProject,IdSprintBacklog,StartDate,ArchiveDate) VALUES (?, ?, ?, ?, ?, ?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String name = dataGen.makeWord(4, 15);
			String startDate = dataGen.makeDate(2015, 2016);
			String archDate = dataGen.makeDate(2017, 2018);
			int idP = dataGen.getRandomId(idProjects);
			int idSB = dataGen.getRandomId(idSprintBacklogs);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setInt(2, SPRINT_TIME);
				statement.setInt(3, idP);
				statement.setInt(4, idSB);
				statement.setString(5, startDate);
				statement.setString(6, archDate);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
