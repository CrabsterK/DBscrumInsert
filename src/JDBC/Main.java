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
		sprintBacklogsTable.tableInsert(22);
		
		SprintsStorage sprintsStorageTable = new SprintsStorage(myConn);
		sprintsStorageTable.tableInsert(21);
		
		ProjectBacklogs projectBacklogsTable = new ProjectBacklogs(myConn);
		projectBacklogsTable.tableInsert(20);
		
		Epics epicsTable = new Epics(myConn);
		epicsTable.tableInsert(20);
		
		Employees employeesTable = new Employees(myConn);
		employeesTable.tableInsert(15);
		
		Teams teamsTable = new Teams(myConn);
		teamsTable.tableInsert(15);
		
		Jobs jobsTable = new Jobs(myConn);
		jobsTable.tableInsert(30);
		
		Projects projectsTable = new Projects(myConn);
		projectsTable.tableInsert(1);
		
		UserStories userStoriesTable = new UserStories(myConn);
		userStoriesTable.tableInsert(3);
	}
	
}
