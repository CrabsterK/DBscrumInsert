package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Sprints {
	final int SPRINT_TIME = 30;
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	Random random = new Random();
	DataGenerator dataGen = new DataGenerator();
	
	public Sprints(Connection con) {
		myConn=con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Sprints...");
		statement = null;

		sql = "SELECT IdProject FROM Projects;";
		ArrayList<Integer> idProjects = dataGen.getIdList(myConn, sql, "IdProject");

		sql = "SELECT IdSprintBacklog FROM SprintBacklogs;";
		ArrayList<Integer> idSprintBacklogs = dataGen.getIdList(myConn, sql, "IdSprintBacklog");

		sql = "INSERT INTO Sprints (SprintName, SprintTime, IdProject, IdSprintBacklog) VALUES (?, ?, ?,?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String name = dataGen.makeWord(4, 15);
			int idP = dataGen.getRandomId(idProjects);
			int idSB = dataGen.getRandomId(idSprintBacklogs);

			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setInt(2, SPRINT_TIME);
				statement.setInt(3, idP);
				statement.setInt(4, idSB);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
}
