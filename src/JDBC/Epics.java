package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Epics {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public Epics(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Epics...");
		statement = null;
		sql = "INSERT INTO Epics (EpicName, EpicDesc) VALUE (?, ?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(49 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String name = sb.toString();
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(700 - 300) + 300 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String desc = sb.toString();
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, desc);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}
