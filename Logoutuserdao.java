
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Logoutuserdao {

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

    public void savelogoutdetails(String name)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE login SET logouttime = NOW() WHERE name = ?";

        try (Connection con = getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            p.setString(1, name);
            p.executeUpdate();
        }
    }
}
