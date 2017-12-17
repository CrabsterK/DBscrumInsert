package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TicketsHistory {
    Connection myConn;
    PreparedStatement statement = null;
    String sql = null;
    Random random = new Random();
    DataGenerator dataGen = new DataGenerator();

    public TicketsHistory(Connection con) {
        myConn=con;
    }

    public void tableInsert(int amount) {
        System.out.println("Inserting "+amount+" TicketsHistory...");
        statement = null;

        sql = "SELECT IdEmployer FROM Employees;";
        ArrayList<Integer> idEmpList = dataGen.getIdList(myConn, sql, "IdEmployer");

        sql = "SELECT IdSprint FROM Sprints;";
        ArrayList<Integer> idSprintList = dataGen.getIdList(myConn, sql, "IdSprint");

        sql = "SELECT IdType FROM TicketTypes;";
        ArrayList<Integer> idPTyleList = dataGen.getIdList(myConn, sql, "IdType");

        sql = "SELECT IdState FROM States;";
        ArrayList<Integer> idStatess = dataGen.getIdList(myConn, sql, "IdState");


        sql = "INSERT INTO TicketsHistory (IdEmployer, TicketDesc, IdSprint, IdType, IdState, TicketArchiveDate, TicketStartDate ) VALUES (?, ?, ?, ?, ?, ?, ?);";

        for(int quantity = 1; quantity <= amount; quantity++) {
            int idE = dataGen.getRandomId(idEmpList);
            int idSp = dataGen.getRandomId(idSprintList);
            int idT = dataGen.getRandomId(idPTyleList);
            int idSt = dataGen.getRandomId(idStatess);
            String desc = dataGen.makeDesc(8, 50);
            String archDate = dataGen.makeDate(2019, 2020);
            String startDate = dataGen.makeDate(2017, 2018);

            try {
                statement = myConn.prepareStatement(sql);
                statement.setInt(1, idE);
                statement.setString(2, desc);
                statement.setInt(3, idSp);
                statement.setInt(4, idT);
                statement.setInt(5, idSt);
                statement.setString(6, archDate);
                statement.setString(7, startDate);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("DONE");
    }
}
