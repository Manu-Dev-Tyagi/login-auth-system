<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Check if user is logged in
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container { max-width: 800px; margin-top: 50px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
            <form action="logout" method="post" class="d-inline">
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
        </div>
        
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Your Account Information</h5>
                <p class="card-text">
                    <strong>Username:</strong> <%= session.getAttribute("username") %><br>
                    <strong>Roles:</strong> <%= session.getAttribute("roles") %>
                </p>
            </div>
        </div>
    </div>
</body>
</html> 