
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {

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

    public boolean deleteUser(int deleteId) throws SQLException {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            p.setInt(1, deleteId);

            return p.executeUpdate() > 0;
        }
    }
}
