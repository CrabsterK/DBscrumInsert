package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class History {
    Connection myConn;
    PreparedStatement statement = null;
    String sql = null;

    public History(Connection con) {
        myConn = con;
    }
    public void tableInsert(int amount) {
        System.out.println("Inserting "+amount+" History...");
        statement = null;

        ArrayList<Integer> idSprints = null;
        sql = "SELECT IdSprint FROM Sprints;";
        try {
            statement = myConn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            idSprints = new ArrayList<Integer>();
            while(rs.next()) {
                idSprints.add(rs.getInt("IdSprint"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> idStatuses = null;
        sql = "SELECT IdStatus FROM Statuses;";
        try {
            statement = myConn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            idStatuses = new ArrayList<Integer>();
            while(rs.next()) {
                idStatuses.add(rs.getInt("IdStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO History (IdSprint, IdStatus, StatusDate) VALUES (?, ?, ?);";

        Random random = new Random();
        for(int quantity = 1; quantity <= amount; quantity++) {

            GregorianCalendar gc = new GregorianCalendar();
            int year = randBetween(2000, 2016);
            gc.set(Calendar.YEAR, year);
            int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
            gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
            String stDat = (gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));
            System.out.println(stDat);

            int idSp = idSprints.get(random.nextInt(idSprints.size()));
            int idSt = idStatuses.get(random.nextInt(idStatuses.size()));

            try {
                statement = myConn.prepareStatement(sql);
                statement.setInt(1, idSp);
                statement.setInt(2, idSt);
                statement.setString(3, stDat);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("DONE");
    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


}
