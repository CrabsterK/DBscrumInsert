package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class Employees {
	public void tableInsert(int amount) {
		//DATABASE connection
		try {			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrum", "root", "root");
			Statement myStmt = myConn.createStatement();
			
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for(int quantity = 1; quantity <= amount; quantity++) {
				
				//NAME GENERATOR
				sb.setLength(0);
				for (int i = 0; i < random.nextInt(10 - 1 + 1) + 1 ; i++) {
				    char c = chars[random.nextInt(chars.length)];
				    sb.append(c);
				}
				String name = sb.toString();
				
				//LASTNAME GENERATOR
				sb.setLength(0);
				for (int i = 0; i < random.nextInt(60 - 1 + 1) + 1 ; i++) {
				    char c = chars[random.nextInt(chars.length)];
				    sb.append(c);
				}
				String lastname = sb.toString();
				
				//MAIL GENERATOR
				sb.setLength(0);
				for (int i = 0; i < random.nextInt(41 - 1 + 1) + 1 ; i++) {
				    char c = chars[random.nextInt(chars.length)];
				    sb.append(c);
				}
				sb.append("@mail.com");
				String mail = sb.toString();
				
				//IdROLE GENERATOR
				int idRole = random.nextInt(4 - 1 + 1) + 1;
				
				String sql = "INSERT INTO employees (Name, LastName, Email, IdRole) VALUES ('"+name+"','"+lastname+"','"+mail+"','"+idRole+"');";
				myStmt.executeUpdate(sql);
				System.out.println(sql);
				System.out.println(quantity+". Query OK");
			}
		} 
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
}