package com.example.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/auth_system";
    private static final String USER = "root";
    private static final String PASSWORD ="" ;

    public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // load MySQL JDBC driver
    } catch (ClassNotFoundException e) {
        e.printStackTrace(); // helpful to debug if JDBC driver is missing
    }

    return DriverManager.getConnection(URL, USER, PASSWORD);
}

}
