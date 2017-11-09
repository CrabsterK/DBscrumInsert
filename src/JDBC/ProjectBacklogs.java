package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class ProjectBacklogs {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public ProjectBacklogs(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" ProjectBacklogs...");
		statement = null;
		sql = "INSERT INTO ProjectBacklogs (projectBacklogDesc) VALUE (?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(700 - 500) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String desc = sb.toString();
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, desc);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
