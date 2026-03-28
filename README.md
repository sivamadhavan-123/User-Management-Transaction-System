# 📌 User Management & Transaction System (Java Servlet Project)

## 📖 Overview

This project is a **Java-based web application** built using **Servlets, Filters, DAO, and Service layers**. It provides a complete user authentication system, admin management, and transaction handling with scheduled interest calculation.

The system ensures:
- Secure user authentication  
- Input validation using Filters and Regex  
- Password hashing  
- Role-based access (User/Admin)  
- Monthly interest calculation using a scheduler  

---

## 🚀 Features

### 🔐 Authentication
- User Signup (`/signup`)  
- User Signin (`/signin`)  
- Logout (`/logout`)  

### 👤 User Operations
- Update User (`/user/update`)  
- Delete User (`/user/delete`)  
- Deposit Money (`/deposit`)  

### 👑 Admin Operations
- View all users (`/admin/alluser`) with pagination  
- Admin email notification on login  

### ⚙️ Background Job
- Monthly interest calculation using **Quartz Scheduler (0.58%)**

---

## 🏗️ Project Flow

### 📝 Signup Flow
1. User sends request to `/signup`  
2. **SignupFilter**:
   - Validates input using regex  
   - Checks if username already exists  
3. If valid → `SignUp Servlet`  
4. Password is hashed  
5. Data sent to `UserDao`  
6. User stored in database  

---

### 🔑 Signin Flow
1. User sends request to `/signin`  
2. **SigninFilter**:
   - Checks login status  
   - Prevents multiple logins  
3. Data sent to `LoginDao`  
4. Password is verified (hashed comparison)  
5. If valid:
   - Login successful  
   - If Admin → Email notification sent  

---

### 👑 Admin Flow
- **Endpoint:** `/admin/alluser`  

**Checks:**
- User is logged in  
- Role is Admin  

**Returns:**
- List of users  
- Pagination support  

---

### 👤 User Update Flow
- **Endpoint:** `/user/update`  

**Checks:**
- User is logged in  
- New username is unique  

**Action:**
- Updates user details in database  

---

### ❌ User Delete Flow
- **Endpoint:** `/user/delete`  

**Checks:**
- User is logged in  

**Action:**
- Deletes user account  

---

### 💰 Deposit Flow
- **Endpoint:** `/deposit`  

**Checks:**
- User is logged in  
- Role is User  

**Action:**
- Deposits money into account  

---

### ⏳ Scheduler (Quartz)
- Runs monthly  
- Adds **0.58% interest** to user balances  

---

### 🚪 Logout Flow
- **Endpoint:** `/logout`  

**Checks:**
- User is logged in  

**Action:**
- Logs out user session  

---

## 🧱 Project Structure
User_Management_Transaction_System
│
├── pom.xml
├── src/main/java
│ └── com.project
│ ├── config
│ │ ├── AppStartupListener.java
│ │ ├── DataSourceProvider.java
│ │ ├── LiquibaseRunner.java
│ │ └── QuartzScheduler.java
│ │
│ ├── controller
│ │ ├── AdminServlet.java
│ │ ├── SignUp.java
│ │ ├── SignIn.java
│ │ ├── Logout.java
│ │ └── UserServlet.java
│ │
│ ├── dao
│ │ ├── UserDao.java
│ │ └── LoginDao.java
│ │
│ ├── dto
│ │ ├── User.java
│ │ ├── Admin.java
│ │ └── LoginDto.java
│ │
│ ├── filter
│ │ ├── SignupFilter.java
│ │ └── SigninFilter.java
│ │
│ ├── service
│ │ └── ServiceLayer.java
│ │
│ └── util
│ ├── EmailSMTP.java
│ └── Property.java
│
└── src/main/resources
├── application.properties
├── changelog.xml
└── log4j2.xml


---

## 🔒 Security Features

- Input validation using Filters and Regex  
- Password hashing (secure storage)  
- Role-based authorization (User/Admin)  
- Session-based authentication  
- Prevention of duplicate login  

---

## 🛠️ Technologies Used

- Java Servlets  
- JDBC  
- Filters  
- Quartz Scheduler  
- Liquibase (DB migration)  
- Log4j2 (Logging)  
- SMTP (Email Service)  

---

## ⚡ Setup Instructions

1. Clone the repository  
2. Configure `application.properties`  
3. Setup database  
4. Run Liquibase migrations  
5. Deploy on server (Tomcat)  
6. Start the application  

---

## 📌 Endpoints Summary

| Endpoint         | Method | Description                     |
|----------------|--------|---------------------------------|
| `/signup`       | POST   | Register new user              |
| `/signin`       | POST   | Login user                     |
| `/logout`       | POST   | Logout user                    |
| `/admin/alluser`| GET    | Get all users (Admin only)     |
| `/user/update`  | PUT    | Update user details            |
| `/user/delete`  | DELETE | Delete user                    |
| `/deposit`      | POST   | Deposit money                  |

---
