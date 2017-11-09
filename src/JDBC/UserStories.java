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
		sql = "SELECT IdProjectBacklog FROM projectbacklogs;";
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
				System.out.println(next);
				idProjectBacklogs.add(next);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
