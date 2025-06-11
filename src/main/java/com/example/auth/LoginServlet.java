package com.example.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final long LOCKOUT_DURATION = 15 * 60 * 1000; // 15 minutes in milliseconds

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ipAddress = request.getRemoteAddr();
        
        // Basic validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // Check if user is locked out
        if (isUserLockedOut(username)) {
            request.setAttribute("error", "Account is temporarily locked. Please try again later.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        boolean success = UserDAO.authenticateUser(username, password);
        UserDAO.recordLoginAttempt(username, ipAddress, success);
        
        if (success) {
            // Create session and store user info
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("roles", UserDAO.getUserRoles(username));
            
            // Redirect to dashboard or home page
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
    private boolean isUserLockedOut(String username) {
        // TODO: Implement lockout logic based on failed login attempts
        // This would typically check the login_attempts table for recent failed attempts
        return false;
    }
} 