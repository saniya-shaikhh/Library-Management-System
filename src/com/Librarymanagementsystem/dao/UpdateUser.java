
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Librarymanagementsystem.bean.User;

public class UpdateUser {

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

    public boolean updateUser(User u1) throws SQLException {

        String sql = "UPDATE users SET name = ?, email = ?, phoneno = ?, "
                   + "gender = ?, DOB = ?, address = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, u1.getName());
            pst.setString(2, u1.getEmail());
            pst.setString(3, u1.getPhoneno());
            pst.setString(4, u1.getGender());
            pst.setString(5, u1.getDOB());
            pst.setString(6, u1.getAddress());
            pst.setInt(7, u1.getId());

            return pst.executeUpdate() > 0;
        }
    }
}
