
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Librarymanagementsystem.bean.User;

public class Loginuserdao {

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

    public User logindata(User u)
            throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, u.getEmail());
            pst.setString(2, u.getPassword());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User u1 = new User();
                    u1.setId(rs.getInt("id"));
                    u1.setName(rs.getString("name"));
                    return u1;
                }
            }
        }

        return null;
    }

    public void savelogindetails(User login)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO login (u_id, name, logintime) VALUES (?, ?, NOW())";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, login.getId());
            pst.setString(2, login.getName());

            pst.executeUpdate();
        }
    }
}
