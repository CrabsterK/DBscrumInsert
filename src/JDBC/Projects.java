package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Projects {

	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	Random random = new Random();
	DataGenerator dataGen = new DataGenerator();
	
	public Projects(Connection con) {
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Projects...");
		sql = "SELECT IdTeam FROM Teams;";
		ArrayList<Integer> idTeamList = dataGen.getIdList(myConn, sql, "IdTeam");

		sql = "SELECT IdProjectBacklog FROM ProjectBacklogs;";
		ArrayList<Integer> idPBList =  dataGen.getIdList(myConn, sql, "IdProjectBacklog");

		sql = "INSERT INTO Projects (ProjectName, ProjectDesc, IdTeam, IdProjectBacklog) VALUES (?, ?, ?,?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String name =  dataGen.makeWord(4, 9);
			String desc = dataGen.makeDesc(8, 50);
			int idT = idTeamList.get(random.nextInt(idTeamList.size()-1));
			int idPB = idPBList.get(random.nextInt(idPBList.size()-1));

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, desc);
				statement.setInt(3, idT);
				statement.setInt(4, idPB);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
}
