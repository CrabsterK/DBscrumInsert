package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketStatesHistory {
    Connection myConn;
    PreparedStatement statement = null;
    String sql = null;
    DataGenerator dataGen = new DataGenerator();

    public TicketStatesHistory(Connection con) {
        myConn = con;
    }
    public void tableInsert(int amount) {
        System.out.println("Inserting "+amount+" TicketStatesHistory...");
        statement = null;

        sql = "SELECT IdTicket FROM Tickets;";
        ArrayList<Integer> idTickets = dataGen.getIdList(myConn, sql, "IdTicket");

        sql = "SELECT IdState FROM States;";
        ArrayList<Integer> idStatess = dataGen.getIdList(myConn, sql, "IdState");

        sql = "INSERT INTO TicketStatesHistory (IdTicket, IdState, StateDate) VALUES (?, ?, ?);";

        for(int quantity = 1; quantity <= amount; quantity++) {
            int idTic = dataGen.getRandomId(idTickets);
            int idSta = dataGen.getRandomId(idStatess);
            String date = dataGen.makeDate(2000, 2016);

            try {
                statement = myConn.prepareStatement(sql);
                statement.setInt(1, idTic);
                statement.setInt(2, idSta);
                statement.setString(3, date);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("DONE");
    }
}
