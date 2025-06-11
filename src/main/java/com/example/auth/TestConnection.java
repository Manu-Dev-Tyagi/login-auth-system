package com.example.auth;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();
            System.out.println("✅ Connection successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
