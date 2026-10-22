
package com.Librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Librarymanagementsystem.bean.User;

public class ShowUserdata {

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

    public void adminshow() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM users";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ArrayList<User> arr = new ArrayList<>();

            while (rs.next()) {
                User u2 = new User();

                u2.setId(rs.getInt("id"));
                u2.setName(rs.getString("name"));
                u2.setEmail(rs.getString("email"));
                u2.setPassword(rs.getString("password"));
                u2.setPhoneno(rs.getString("phoneno"));
                u2.setGender(rs.getString("gender"));
                u2.setDOB(rs.getString("DOB"));
                u2.setAddress(rs.getString("address"));

                arr.add(u2);
                System.out.println("ID: " + u2.getId());
                System.out.println("Name: " + u2.getName());
                System.out.println("Email: " + u2.getEmail());
                System.out.println("Phone: " + u2.getPhoneno());
                System.out.println("----------------------");
            }

            // User records are now stored in arr.
            // Display them here if your application requires it.
        }
    }
}
