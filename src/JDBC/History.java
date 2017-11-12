package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class History {
    Connection myConn;
    PreparedStatement statement = null;
    String sql = null;
    DataGenerator dataGen = new DataGenerator();

    public History(Connection con) {
        myConn = con;
    }
    public void tableInsert(int amount) {
        System.out.println("Inserting "+amount+" History...");
        statement = null;

        sql = "SELECT IdSprint FROM Sprints;";
        ArrayList<Integer> idSprints = dataGen.getIdList(myConn, sql, "IdSprint");

        sql = "SELECT IdStatus FROM Statuses;";
        ArrayList<Integer> idStatuses = dataGen.getIdList(myConn, sql, "IdStatus");

        sql = "INSERT INTO History (IdSprint, IdStatus, StatusDate) VALUES (?, ?, ?);";

        Random random = new Random();
        for(int quantity = 1; quantity <= amount; quantity++) {
            int idSp = dataGen.getRandomId(idSprints);
            int idSt = dataGen.getRandomId(idStatuses);
            String date = dataGen.makeDate(2000, 2016);

            try {
                statement = myConn.prepareStatement(sql);
                statement.setInt(1, idSp);
                statement.setInt(2, idSt);
                statement.setString(3, date);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("DONE");
    }
}
