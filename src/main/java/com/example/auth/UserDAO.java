package com.example.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    public static boolean registerUser(String username, String password, String email) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.executeUpdate();
            
            // Assign default role
            assignRole(username, "USER");
            
            System.out.println("✅ User registered: " + username);
            return true;

        } catch (Exception e) {
            System.out.println("❌ Error registering user:");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ? AND is_active = true";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                boolean matches = BCrypt.checkpw(password, hashedPassword);
                
                if (matches) {
                    updateLastLogin(username);
                }
                
                return matches;
            }
            return false;

        } catch (Exception e) {
            System.out.println("❌ Error authenticating user:");
            e.printStackTrace();
            return false;
        }
    }

    private static void updateLastLogin(String username) {
        String sql = "UPDATE users SET last_login = CURRENT_TIMESTAMP WHERE username = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error updating last login:");
            e.printStackTrace();
        }
    }

    public static void recordLoginAttempt(String username, String ipAddress, boolean success) {
        String sql = "INSERT INTO login_attempts (username, ip_address, success) VALUES (?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, ipAddress);
            stmt.setBoolean(3, success);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error recording login attempt:");
            e.printStackTrace();
        }
    }

    private static void assignRole(String username, String role) {
        String sql = "INSERT INTO user_roles (user_id, role_name) " +
                    "SELECT id, ? FROM users WHERE username = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, role);
            stmt.setString(2, username);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error assigning role:");
            e.printStackTrace();
        }
    }

    public static List<String> getUserRoles(String username) {
        String sql = "SELECT r.role_name FROM user_roles r " +
                    "JOIN users u ON r.user_id = u.id " +
                    "WHERE u.username = ?";
        List<String> roles = new ArrayList<>();
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                roles.add(rs.getString("role_name"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error getting user roles:");
            e.printStackTrace();
        }
        
        return roles;
    }
}
