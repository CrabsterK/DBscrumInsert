package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class DataGenerator {

    final int nullPercent = 20;
    StringBuilder sb;
    Random random;
    final char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    final char[] chars2 = "abcdefghijklmnopqrstuvwxyz   ".toCharArray();


    public String makeWord(int minLength, int maxLength) {
        sb = new StringBuilder();
        random = new Random();
        sb.setLength(0);
        for (int i = 0; i < random.nextInt(maxLength - minLength + 1) + minLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public String makeWord(int minLength, int maxLength, int canBeNull) {
        if(canBeNull == 1 && randBetween(1, 100) < nullPercent) {
                return null;
        }
        else
           return makeWord(minLength, maxLength);
    }

    public String makeEmail() {
            return makeWord(4, 9) + "@mail.com";
    }

    public String makeEmail(int canBeNull) {
        if(canBeNull == 1 && randBetween(1, 100) < nullPercent) {
            return null;
        }
        else
            return makeEmail();
    }

    public String makeDesc(int minLength, int maxLength) {
        sb = new StringBuilder();
        random = new Random();
        sb.setLength(0);
        for (int i = 0; i < random.nextInt(maxLength - minLength + 1) + minLength; i++) {
            if (i == 0) {   //pierwsza nie może być spacja xD
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            char c = chars2[random.nextInt(chars2.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public String makeDesc(int minLength, int maxLength, int canBeNull) {
        if(canBeNull == 1 && randBetween(1, 100) < nullPercent) {
            return null;
        }
        else
            return makeDesc(minLength, maxLength);
    }

    public String makeDate(int minYear, int maxYear) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(minYear, maxYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return (gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));
    }
    public String makeDate(int minYear, int maxYear, int canBeNull) {
        if(canBeNull == 1 && randBetween(1, 100) < nullPercent) {
            return null;
        }
        else
            return makeDate(minYear, maxYear);
    }

    public int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


    public ArrayList getIdList(Connection myConn, String sql, String column) {
        PreparedStatement statement;
        ResultSet rs = null;
        ArrayList<Integer> idList = new ArrayList<Integer>();
        try {
            statement = myConn.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                int next = rs.getInt(column);
                idList.add(next);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return idList;
    }

    public int getRandomId(ArrayList <Integer> idList){
        random = new Random();
        int id = idList.get(random.nextInt(idList.size()));
        return id;
    }
}
