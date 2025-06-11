# 🔐 Login Authentication System

A robust, production-ready user authentication system built with Java Servlets (Jakarta EE), JSP, MySQL, and deployed on Apache Tomcat. Includes secure password storage, role-based access control, and login attempt tracking.

---

## 🚀 Features

- ✅ User Registration & Login
- 🔒 Secure Password Hashing (BCrypt)
- 👥 Role-Based Access Control
- 🌐 Session Management
- 🧠 Login Attempt Tracking
- 📦 Maven for Build Management


## 🛠️ Technology Stack

| Layer        | Technology                    |
|-------------|--------------------------------|
| Backend      | Java Servlets (Jakarta EE 9+) |
| Frontend     | JSP, HTML, CSS (Bootstrap 5)  |
| Database     | MySQL 8.0                     |
| Server       | Apache Tomcat 11              |
| Security     | BCrypt                        |
| Build Tool   | Maven                         |
-----------------------------------------------


## 📁 Project Structure
login-auth-system/
├── src/
│ └── main/
│ ├── java/
│ │ └── com/example/auth/
│ │ ├── UserDAO.java
│ │ ├── DBUtil.java
│ │ ├── RegisterServlet.java
│ │ ├── LoginServlet.java
│ │ └── LogoutServlet.java
│ ├── resources/
│ │ └── schema.sql
│ └── webapp/
│ ├── WEB-INF/
│ ├── register.jsp
│ ├── login.jsp
│ └── dashboard.jsp
└── pom.xml

🖥️ Running the Project
Step 1: Clone the Repository
git clone https://github.com/your-username/login-auth-system.git
cd login-auth-system

Step 2: Setup the Database
mysql -u root -p < src/main/resources/schema.sql

Step 3: Build & Deploy
mvn clean package
cp target/login-auth-system.war /path/to/tomcat/webapps/

Step 4: Start Tomcat
sudo catalina start

App available at: http://localhost:8080/login-auth-system/register.jsp


🔐 Security Features
BCrypt password hashing
Minimum password strength check
Session validation and logout handling
Login attempt logging with IP tracking
Structure in place for lockout mechanism

📈 Future Enhancements
📧 Email verification
🔁 Password reset via email
📱 Mobile API endpoints
🕵️‍♂️ Two-Factor Authentication
🔒 Remember Me functionality
⚙️ Admin panel and role-based dashboards


<img width="658" alt="Screenshot 2025-06-11 at 9 32 58 PM" src="https://github.com/user-attachments/assets/e86b5f2e-e9b8-482d-8262-9d74ec2ce0d2" />

<img width="658" alt="Screenshot 2025-06-11 at 9 34 15 PM" src="https://github.com/user-attachments/assets/4e435642-b823-48db-94ca-6aa12c99a603" />

<img width="817" alt="Screenshot 2025-06-11 at 9 35 04 PM" src="https://github.com/user-attachments/assets/2c6c3bd7-49d0-490d-b9e5-f898f64aacad" />

Database
<img width="1120" alt="Screenshot 2025-06-11 at 9 38 52 PM" src="https://github.com/user-attachments/assets/ef180dae-9ec3-490c-8b93-d9901e2e3b46" />


📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

