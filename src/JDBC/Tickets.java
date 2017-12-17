package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Tickets {
    Connection myConn;
    PreparedStatement statement = null;
    String sql = null;
    Random random = new Random();
    DataGenerator dataGen = new DataGenerator();

    public Tickets(Connection con) {
        myConn=con;
    }

    public void tableInsert(int amount) {
        System.out.println("Inserting "+amount+" Tickets...");
        statement = null;

        sql = "SELECT IdEmployer FROM Employees;";
        ArrayList<Integer> idEmpList = dataGen.getIdList(myConn, sql, "IdEmployer");

        sql = "SELECT IdSprint FROM Sprints;";
        ArrayList<Integer> idSprintList = dataGen.getIdList(myConn, sql, "IdSprint");

        sql = "SELECT IdType FROM TicketTypes;";
        ArrayList<Integer> idPTyleList = dataGen.getIdList(myConn, sql, "IdType");


        sql = "INSERT INTO Tickets (IdEmployer, IdSprint, IdType, TicketDesc, TicketStartDate) VALUES (?, ?, ?, ?, ?);";

        for(int quantity = 1; quantity <= amount; quantity++) {
            int idE = dataGen.getRandomId(idEmpList);
            int idSp = dataGen.getRandomId(idSprintList);
            int idT = dataGen.getRandomId(idPTyleList);
            String desc = dataGen.makeDesc(8, 50);
            String startDate = dataGen.makeDate(2017, 2018);

            try {
                statement = myConn.prepareStatement(sql);
                statement.setInt(1, idE);
                statement.setInt(2, idSp);
                statement.setInt(3, idT);
                statement.setString(4, desc);
                statement.setString(5, startDate);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("DONE");
    }
}
