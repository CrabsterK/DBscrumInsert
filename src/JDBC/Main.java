package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	
	//private static String url = "jdbc:mysql://sql11.freemysqlhosting.net/sql11204078";
	//private static String user = "sql11204078";
	//private static String password = "m5vyibFsDT";

	private static String url = "jdbc:mysql://localhost:3306/scrum";
	private static String user = "root";
	private static String password = "root";
	
	public static void main(String[] args){
		Connection myConn = null;
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Initiation OK");
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
		sprintBacklogsTable.tableInsert(1);

		SprintsStorage sprintsStorageTable = new SprintsStorage(myConn);
		sprintsStorageTable.tableInsert(1);

		ProjectBacklogs projectBacklogsTable = new ProjectBacklogs(myConn);
		projectBacklogsTable.tableInsert(1);

		Epics epicsTable = new Epics(myConn);
		epicsTable.tableInsert(1);

		Employees employeesTable = new Employees(myConn);
		employeesTable.tableInsert(1);

		Teams teamsTable = new Teams(myConn);
		teamsTable.tableInsert(1);

		Jobs jobsTable = new Jobs(myConn);
		jobsTable.tableInsert(1);

		Projects projectsTable = new Projects(myConn);
		projectsTable.tableInsert(1);

		UserStories userStoriesTable = new UserStories(myConn);
		userStoriesTable.tableInsert(1);

		Sprints sprintsTable = new Sprints(myConn);
		sprintsTable.tableInsert(1);

		History history = new History(myConn);
		history.tableInsert(1);

		Tickets ticketsTable = new Tickets(myConn);
		ticketsTable.tableInsert(1);
	}
	
}
