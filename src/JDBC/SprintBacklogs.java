package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SprintBacklogs {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public SprintBacklogs(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" SprintBacklogs...");
		statement = null;
		sql = "INSERT INTO SprintBacklogs (SprintBacklogName) VALUE (?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(69 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String name = sb.toString();
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
