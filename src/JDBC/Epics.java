package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Epics {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	DataGenerator dataGen = new DataGenerator();

	public Epics(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" Epics...");
		statement = null;
		sql = "INSERT INTO Epics (EpicName, EpicDesc) VALUE (?, ?);";

		for(int quantity = 1; quantity <= amount; quantity++) {
			String name = dataGen.makeWord(4, 9);
			String desc = dataGen.makeDesc(8, 50);

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
