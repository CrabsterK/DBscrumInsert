package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class UserStories {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	Random random = new Random();
	DataGenerator dataGen = new DataGenerator();
	
	public UserStories(Connection con) {
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" UserStories...");
		statement = null;

		sql = "SELECT IdProjectBacklog FROM ProjectBacklogs;";
		ArrayList<Integer> idProjectBacklogs = dataGen.getIdList(myConn, sql, "IdProjectBacklog");

		sql = "SELECT IdEpic FROM Epics;";
		ArrayList<Integer> idEpics = dataGen.getIdList(myConn, sql, "IdEpic");

		sql = "SELECT IdSprintBacklog FROM SprintBacklogs";
		ArrayList<Integer> idSprintBacklogs = dataGen.getIdList(myConn, sql, "IdSprintBacklog");

		sql = "INSERT INTO UserStories (StoryDesc, IdProjectBacklog, IdEpic, IdSprintBacklog) VALUES (?, ?, ?,?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String desc = dataGen.makeDesc(8, 50);
			int idE = idEpics.get(random.nextInt(idEpics.size()));
			int idPB = idProjectBacklogs.get(random.nextInt(idProjectBacklogs.size()));
			int idSB = idSprintBacklogs.get(random.nextInt(idSprintBacklogs.size()));

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, desc);
				statement.setInt(2, idPB);
				statement.setInt(3, idE);
				statement.setInt(4, idSB);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
}
