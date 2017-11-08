package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	
	private static String url = "jdbc:mysql://localhost:3306/scrum";
	private static String user = "root";
	private static String password = "root";
	
	public static void main(String[] args){
		Connection myConn = null;
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		States statesTable = new States(myConn);
		statesTable.tableInsert();
		
		Statuses statusesTable = new Statuses(myConn);
		statusesTable.tableInsert();
		
		TicketTypes ticketTypesTable = new TicketTypes(myConn);
		ticketTypesTable.tableInsert();
		
		Roles rolesTable = new Roles(myConn);
		rolesTable.tableInsert();
		
		SprintBacklogs sprintBacklogsTable = new SprintBacklogs(myConn);
		sprintBacklogsTable.tableInsert(0);
		
		SprintsStorage sprintsStorageTable = new SprintsStorage(myConn);
		sprintsStorageTable.tableInsert(0);
		
		Epics epicsTable = new Epics(myConn);
		epicsTable.tableInsert(0);
		
		Employees employeesTable = new Employees(myConn);
		employeesTable.tableInsert(0);
		
		Teams teamsTable = new Teams(myConn);
		teamsTable.tableInsert(2);
	}
	
}
