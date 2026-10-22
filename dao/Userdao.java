
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Librarymanagementsystem.bean.User;

public class Userdao {

    private Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            throw new SQLException(
                "Database configuration missing. Set DB_URL, DB_USER and DB_PASSWORD."
            );
        }

        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean insertUser(User u)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO users "
                   + "(name, email, password, phoneno, gender, DOB, address) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            p.setString(1, u.getName());
            p.setString(2, u.getEmail());
            p.setString(3, u.getPassword());
            p.setString(4, u.getPhoneno());
            p.setString(5, u.getGender());
            p.setString(6, u.getDOB());
            p.setString(7, u.getAddress());

            return p.executeUpdate() > 0;
        }
    }

    public boolean isEmailExists(String email)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT 1 FROM users WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            p.setString(1, email);

            try (ResultSet rs = p.executeQuery()) {
                return rs.next();
            }
        }
    }
}
