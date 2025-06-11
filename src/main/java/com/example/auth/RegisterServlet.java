package com.example.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-auth-system/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        // Basic validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Password strength validation
        if (password.length() < 8) {
            request.setAttribute("error", "Password must be at least 8 characters long");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Email format validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        boolean success = UserDAO.registerUser(username, password, email);
        
        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Username or email might already exist.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
