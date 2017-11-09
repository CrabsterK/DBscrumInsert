package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Projects {
	
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	
	
	public Projects(Connection con) {
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Projects...");
		statement = null;
		
		ResultSet rs = null;
		ArrayList<Integer> idTeams = new ArrayList<Integer>();
		sql = "SELECT IdTeam FROM Teams;";
		try {
			statement = myConn.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int next = rs.getInt("IdTeam");
				idTeams.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = null;
		ArrayList<Integer> idProjectbacklogs = new ArrayList<Integer>();
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
				idProjectbacklogs.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int quantity = 1; quantity <= amount; quantity++) {
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(49 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String name = sb.toString();
			
			int test = random.nextInt(700 - 500);
			sb.setLength(0);
			for (int i = 0; i < test; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String desc = sb.toString();
			
			int idT = idTeams.get(random.nextInt(idTeams.size()-1));
			int idPB = idProjectbacklogs.get(random.nextInt(idProjectbacklogs.size()-1));
			
			sql = "INSERT INTO Projects (ProjectName, ProjectDesc, IdTeam, IdProjectBacklog) VALUES (?, ?, ?,?);";
			
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
