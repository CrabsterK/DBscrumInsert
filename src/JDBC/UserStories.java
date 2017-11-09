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
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	
	public UserStories(Connection con) {
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" UserStories...");
		statement = null;
		
		ResultSet rs = null;
		ArrayList<Integer> idProjectBacklogs = new ArrayList<Integer>();
		sql = "SELECT IdProjectBacklog FROM ProjectBacklogs;";
		try {
			statement = myConn.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int next = rs.getInt("IdProjectBacklog");
				idProjectBacklogs.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = null;
		ArrayList<Integer> idEpics = new ArrayList<Integer>();
		sql = "SELECT IdEpic FROM Epics;";
		try {
			statement = myConn.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int next = rs.getInt("IdEpic");
				idEpics.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = null;
		ArrayList<Integer> idSprintBacklogs = new ArrayList<Integer>();
		sql = "SELECT IdSprintBacklog FROM SprintBacklogs";
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
			int test = random.nextInt(700 - 500) +1;
			for (int i = 0; i < test; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String desc = sb.toString();
			
			int idE = idEpics.get(random.nextInt(idEpics.size()-1));
			int idPB = idProjectBacklogs.get(random.nextInt(idProjectBacklogs.size()-1));
			int idSB = idSprintBacklogs.get(random.nextInt(idSprintBacklogs.size()-1));
			
			sql = "INSERT INTO UserStories (StoryDesc, IdProjectBacklog, IdEpic, IdSprintBacklog) VALUES (?, ?, ?,?);";
			
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
