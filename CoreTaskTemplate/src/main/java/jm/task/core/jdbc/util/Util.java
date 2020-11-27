package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pobeda?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345678";
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }



}
