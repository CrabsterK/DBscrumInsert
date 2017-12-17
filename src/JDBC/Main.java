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
		final int size = 10;
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
		sprintBacklogsTable.tableInsert(size);

		ProjectBacklogs projectBacklogsTable = new ProjectBacklogs(myConn);
		projectBacklogsTable.tableInsert(size);

		Epics epicsTable = new Epics(myConn);
		epicsTable.tableInsert(size);

		Employees employeesTable = new Employees(myConn);
		employeesTable.tableInsert(size);

		Teams teamsTable = new Teams(myConn);
		teamsTable.tableInsert(size);

		Jobs jobsTable = new Jobs(myConn);
		jobsTable.tableInsert(size);

		Projects projectsTable = new Projects(myConn);
		projectsTable.tableInsert(size);

		UserStories userStoriesTable = new UserStories(myConn);
		userStoriesTable.tableInsert(size);

		Sprints sprintsTable = new Sprints(myConn);
		sprintsTable.tableInsert(size);

		History history = new History(myConn);
		history.tableInsert(size);

		Tickets ticketsTable = new Tickets(myConn);
		ticketsTable.tableInsert(size);

		TicketStatesHistory ticketStatesHistory = new TicketStatesHistory(myConn);
		ticketStatesHistory.tableInsert(size);

		SprintsStorage sprintsStorageTable = new SprintsStorage(myConn);
		sprintsStorageTable.tableInsert(size);

		TicketsHistory ticketsHistory = new TicketsHistory(myConn);
		ticketsHistory.tableInsert(size);
	}

}