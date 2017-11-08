package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class SprintsStorage {
	Connection myConn;
	PreparedStatement statement = null;
	String sql = null;
	
	public SprintsStorage(Connection con){
		myConn = con;
	}
	
	public void tableInsert(int amount) {
		System.out.println("Inserting "+amount+" SprintsStorage...");
		statement = null;
		sql = "INSERT INTO sprintsstorage (SprintName,SprintTime,IdProjekt,IdSprintBacklog,ArchiveDate) VALUES (?, ?, ?, ?, ?);";
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int quantity = 1; quantity <= amount; quantity++) {
			
			sb.setLength(0);
			for (int i = 0; i < random.nextInt(59 - 1) + 1 ; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String sptintname = sb.toString();
			
			int sprinttime = random.nextInt(200 - 1) + 1;
			
			int idproject = random.nextInt(200 - 1) + 1;
			
			int idsprintbacklog = random.nextInt(200 - 1) + 1;
			
			
			
			
			GregorianCalendar gc = new GregorianCalendar();

	        int year = randBetween(2000, 2016);

	        gc.set(Calendar.YEAR, year);

	        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

	        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
	        
	        String archivedate = (gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));
	        
			try {
				statement = myConn.prepareStatement(sql);
				statement.setString(1, sptintname);
				statement.setInt(2, sprinttime);
				statement.setInt(3, idproject);
				statement.setInt(4, idsprintbacklog);
				statement.setString(5, archivedate);
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
