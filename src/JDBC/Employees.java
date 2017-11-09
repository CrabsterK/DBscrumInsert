package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


public class Employees {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public Employees(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" employers...");
		statement = null;
		
		ArrayList<Integer> idRoles = null;
		sql = "SELECT IdRole FROM roles;";
		try {
			statement = myConn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			idRoles = new ArrayList<Integer>();
			while(rs.next()) {
				idRoles.add(rs.getInt("IdRole"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "INSERT INTO employees (Name, LastName, Email, IdRole) VALUES (?, ?, ?,?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(10 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String name = sb.toString();
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(60 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String lastname = sb.toString();
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(41 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			sb.append("@mail.com");
			String mail = sb.toString();
			
			int idRole = idRoles.get(random.nextInt(idRoles.size()-1));
			
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, lastname);
				statement.setString(3, mail);
				statement.setInt(4, idRole);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}