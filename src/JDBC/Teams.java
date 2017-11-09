package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Teams {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public Teams(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Teams...");
		statement = null;
		sql = "INSERT INTO Teams (TeamName) VALUES (?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(49 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String teamname = sb.toString();
			
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, teamname);

				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
	}
}
