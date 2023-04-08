package com.example.alarm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_connection {

    public static Connection conn;
    public Database_connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");

        Statement stmt = ((java.sql.Connection) conn).createStatement();
    }
}

