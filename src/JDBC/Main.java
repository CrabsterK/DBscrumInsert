package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	
<<<<<<< HEAD
	//private static String url = "jdbc:mysql://sql11.freemysqlhosting.net/sql11204078";
	//private static String user = "sql11204078";
	//private static String password = "m5vyibFsDT";
=======
//	private static String url = "jdbc:mysql://sql11.freemysqlhosting.net/sql11204078";
//	private static String user = "sql11204078";
//	private static String password = "m5vyibFsDT";
>>>>>>> 86e536d5cfea6292478113ee6ba465d7591972ec
	
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
		sprintBacklogsTable.tableInsert(22);

		SprintsStorage sprintsStorageTable = new SprintsStorage(myConn);
		sprintsStorageTable.tableInsert(21);

		ProjectBacklogs projectBacklogsTable = new ProjectBacklogs(myConn);
		projectBacklogsTable.tableInsert(20);

		Epics epicsTable = new Epics(myConn);
		epicsTable.tableInsert(80);

		Employees employeesTable = new Employees(myConn);
		employeesTable.tableInsert(15);

		Teams teamsTable = new Teams(myConn);
		teamsTable.tableInsert(15);

		Jobs jobsTable = new Jobs(myConn);
		jobsTable.tableInsert(30);

		Projects projectsTable = new Projects(myConn);
		projectsTable.tableInsert(4);

		UserStories userStoriesTable = new UserStories(myConn);
		userStoriesTable.tableInsert(3);

		Sprints sprintsTable = new Sprints(myConn);
		sprintsTable.tableInsert(3);

		History history = new History(myConn);
		history.tableInsert(50);

		Tickets ticketsTable = new Tickets(myConn);
		ticketsTable.tableInsert(10);
	}
	
}
