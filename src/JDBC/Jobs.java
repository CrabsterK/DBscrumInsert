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
	
	public Jobs(Connection con) {
		myConn = con;
	}
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Jobs...");
		statement = null;
		
		ArrayList<Integer> idEmployees = null;
		sql = "SELECT IdEmployer FROM Employees;";
		try {
			statement = myConn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			idEmployees = new ArrayList<Integer>();
			while(rs.next()) {
				idEmployees.add(rs.getInt("IdEmployer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> idTeams = null;
		sql = "SELECT IdTeam FROM Teams;";
		try {
			statement = myConn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			idTeams = new ArrayList<Integer>();
			while(rs.next()) {
				idTeams.add(rs.getInt("Idteam"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "INSERT INTO Jobs (IdEmployer, IdTeam) VALUES (?, ?);";
		
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
					
			int idT = idTeams.get(random.nextInt(idTeams.size()));
			int idE = idEmployees.get(random.nextInt(idEmployees.size()));
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setInt(1, idT);
				statement.setInt(2, idE);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("DONE");
	}
	
}
