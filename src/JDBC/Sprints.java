package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Sprints {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	
	public Sprints(Connection con) {
		myConn=con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Sprints...");
		statement = null;
		ResultSet rs = null;
		
		ArrayList<Integer> idProjects = new ArrayList<Integer>();
		sql = "SELECT IdProject FROM Projects;";
		try {
			statement = myConn.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int next = rs.getInt("IdProject");
				idProjects.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> idSprintBacklogs = new ArrayList<Integer>();
		sql = "SELECT IdSprintBacklog FROM SprintBacklogs;";
		try {
			statement = myConn.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int next = rs.getInt("IdSprintBacklog");
				idSprintBacklogs.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int quantity = 1; quantity <= amount; quantity++) {
			sb.setLength(0);
			int test = random.nextInt(59) +1;
			for (int i = 0; i < test; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String name = sb.toString();
			
			int time = random.nextInt(30) +1;
			
			int idP = idProjects.get(random.nextInt(idProjects.size()-1));
			int idSB = idSprintBacklogs.get(random.nextInt(idSprintBacklogs.size()-1));
			sql = "INSERT INTO Sprints (SprintName, SprintTime, IdProject, IdSprintBacklog) VALUES (?, ?, ?,?);";
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setInt(2, time);
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
