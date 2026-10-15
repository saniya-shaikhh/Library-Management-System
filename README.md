# Library Management System

A Java-based console application that manages user registration, login, logout, and user records using JDBC and MySQL.

## Features

- User registration with input validation
- User login and logout
- Display all registered users through admin access
- Update existing user details
- Delete user records
- MySQL database connectivity using JDBC

## Technologies Used

- **Programming Language:** Java
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **IDE:** Eclipse

## Project Structure

```text
Library-Management-System/
├── src/
│   └── com/Librarymanagementsystem/
│       ├── bean/
│       │   └── User.java
│       ├── controller/
│       │   └── Usercontroller.java
│       └── dao/
│           ├── Userdao.java
│           ├── Loginuserdao.java
│           ├── Logoutuserdao.java
│           ├── ShowUserdata.java
│           ├── UpdateUser.java
│           └── DeleteUser.java
├── .classpath
└── .project
```

## Database Configuration

This project uses a MySQL database named `library`.

Configure the following environment variables before running the application:

- `DB_URL` — JDBC URL for the database
- `DB_USER` — MySQL username
- `DB_PASSWORD` — MySQL password

Create and configure the required database tables according to the SQL queries used in the project.

**Important:** Never commit your actual database password or private user data to GitHub.

## How to Run

1. Install Java, MySQL, and Eclipse IDE.
2. Create the `library` database and required tables.
3. Add the MySQL JDBC driver to the project's build path.
4. Configure the database environment variables.
5. Import the project into Eclipse.
6. Run `Usercontroller.java` and follow the console menu.

## Learning Outcomes

- Java object-oriented programming
- JDBC-based database operations
- CRUD operations with MySQL
- Input validation and exception handling
- Organizing code using bean, controller, and DAO packages

## Author

Developed as a Java and MySQL learning project.
