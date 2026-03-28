
📌 User Management & Transaction System (Java Servlet Project)
📖 Overview

This project is a Java-based web application built using Servlets, Filters, DAO, and Service layers. It provides a complete user authentication system, admin management, and transaction handling with scheduled interest calculation.

The system ensures:
Secure user authentication
Input validation using filters and regex
Password hashing
Role-based access (User/Admin)
Monthly interest calculation using scheduler


🚀 Features

🔐 Authentication
User Signup (/signup)
User Signin (/signin)
Logout (/logout)

👤 User Operations
Update User (/user/update)
Delete User (/user/delete)
Deposit Money (/deposit)

👑 Admin Operations
View all users (/admin/alluser) with pagination
Admin email notification on login

⚙️ Background Job
Monthly interest calculation using Quartz Scheduler (0.58%)

🏗️ Project Flow

📝 Signup Flow
User sends request to /signup
SignupFilter:
Validates input using regex
Checks if username already exists
If valid → SignUp Servlet
Password is hashed
Data sent to UserDao
User stored in database

🔑 Signin Flow
User sends request to /signin
SigninFilter:
Checks login status
Prevents multiple logins
Data sent to LoginDao
Password is verified (hashed comparison)
If valid:
Login successful
If Admin → Email notification sent


👑 Admin Flow
Endpoint: /admin/alluser
Checks:
User is logged in
Role is Admin
Returns:
List of users
Pagination supported

👤 User Update Flow
Endpoint: /user/update
Checks:
User is logged in
New username is unique
Updates user details in database

❌ User Delete Flow
Endpoint: /user/delete
Checks:
User is logged in
Deletes user account

💰 Deposit Flow
Endpoint: /deposit
Checks:
User is logged in
Role is User
Deposits money into account

⏳ Scheduler (Quartz)
Runs monthly
Adds 0.58% interest to user balances

🚪 Logout Flow
Endpoint: /logout
Checks:
User is logged in
Logs out user session

🧱 Project Structure
Controller/
 ├── AdminServlet.java
 ├── Logout.java
 ├── SignIn.java
 ├── SignUp.java
 ├── UserServlet.java

DAO/
 ├── LoginDao.java
 ├── UserDao.java

DTO/
 ├── Admin.java
 ├── LoginDto.java
 ├── User.java

Filters/
 ├── SigninFilter.java
 ├── SignupFilter.java

Service/
 └── ServiceLayer.java

Config/
 ├── AppStartupListener.java
 ├── DataSourceProvider.java
 ├── LiquibaseRunner.java
 ├── QuartzScheduler.java

Util/
 ├── EmailSMTP.java
 ├── Property.java

Resources/
 ├── application.properties
 ├── changelog.xml
 ├── log4j2.xml

🔒 Security Features
Input validation using Filters and Regex
Password hashing (secure storage)
Role-based authorization (User/Admin)
Session-based authentication
Prevention of duplicate login

🛠️ Technologies Used
Java Servlets
JDBC
Filters
Quartz Scheduler
Liquibase (DB migration)
Log4j2 (Logging)
SMTP (Email Service)

⚡ Setup Instructions
Clone the repository
Configure application.properties
Setup database
Run Liquibase migrations
Deploy on server (Tomcat)
Start application

📌 Endpoints Summary

Endpoint	Method	      Description
/signup	         POST	      Register new user
/signin	         POST         Login user
/logout	         POST	      Logout user
/admin/alluser	 GET	      Get all users (Admin only)
/user/update	 POST	      Update user details
/user/delete	 DELETE	      Delete user
/deposit	 POST	      Deposit money
