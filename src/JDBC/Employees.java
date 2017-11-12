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
	DataGenerator dataGen = new DataGenerator();
	Random random = new Random();

	public Employees(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" employers...");
		statement = null;

		sql = "SELECT IdRole FROM Roles;";
		ArrayList<Integer> idRolesList = dataGen.getIdList(myConn, sql, "IdRole");
		
		sql = "INSERT INTO Employees (Name, LastName, Email, IdRole) VALUES (?, ?, ?,?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			int idRole = dataGen.getRandomId(idRolesList);
			String name = dataGen.makeWord(4, 9);
			String lastName = dataGen.makeWord(5,12);
			String email = dataGen.makeEmail(1);

			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, lastName);
				statement.setString(3, email);
				if(dataGen.randBetween(1, 100) < dataGen.nullProcent) {
					statement.setString(4, null);
				}
				else statement.setInt(4, idRole);
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}
}