package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Jobs {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();


	public Jobs(Connection con) {
		myConn = con;
	}
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Jobs...");
		statement = null;

		sql = "SELECT IdEmployer FROM Employees;";
		ArrayList<Integer> idEmployees = dataGen.getIdList(myConn, sql, "IdEmployer");

		sql = "SELECT IdTeam FROM Teams;";
		ArrayList<Integer> idTeams = dataGen.getIdList(myConn, sql, "IdTeam");

		sql = "INSERT INTO Jobs (IdEmployer, IdTeam) VALUES (?, ?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			int idE = dataGen.getRandomId(idEmployees);
			int idT = dataGen.getRandomId(idTeams);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setInt(1, idE);
				statement.setInt(2, idT);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
	
}
