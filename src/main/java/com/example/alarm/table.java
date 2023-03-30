package com.example.alarm;

import java.sql.*;

public class table {

    Connection con;
    public table()
    {
        try {
            // 1) Register the driver class
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Database_connection dbcon = new Database_connection();
            con = dbcon.conn;
            //con = (Connection) DriverManager.getConnection("jdbc:mysql:///project", "root", "200041123");

        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        } catch (ClassNotFoundException e) {
            System.out.println(" Failed to register driver. Exception code : " + e);
        }
    }

    public boolean insert_val(String u_name, String key, String mob_no, String mail_id) {
        try {
            // 1) Register the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;
            Statement stmt = ((java.sql.Connection) conn).createStatement();

            key=passwordhasher.hash(key);
            //if (!isUser(u_name)) {
            String query = "Insert into user_info values('"+u_name+"','"+key+"','"+mob_no+"','"+mail_id+"')";
            int a = stmt.executeUpdate(query);
            if (a > 0) {
                System.out.println("Data is inserted");
                return true;
            } else {
                System.out.println("Insertion failed");
                return false;
            }
            //stmt.close();
            //((java.sql.Connection) conn).close();
            //} else
            //  throw new IllegalAccessException();

        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        } catch (ClassNotFoundException e) {
            System.out.println(" Failed to register driver. Exception code : " + e);
        } //catch (IllegalAccessException e) {
        //  System.out.println("Already exists");
        // }
return false;
    }

    public boolean isUser(String user) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Database_connection dbcon = new Database_connection();
        Connection conn=dbcon.conn;

        PreparedStatement ptstmt = conn.prepareStatement("SELECT username,password from user_info where username = ?");
        // PreparedStatement pt = (PreparedStatement) ((java.sql.Connection) conn).prepareStatement("SELECT username,password from users where username = ?");
        ptstmt.setString(1, user);
        ResultSet resultSet = ptstmt.executeQuery();

        // if user is not in the database
        if (!resultSet.isBeforeFirst()) {
            return false;
        } else
            return true;
    }

    public boolean login(String user, String pass) throws SQLException, ClassNotFoundException {
        Database_connection dbcon = new Database_connection();
        Connection conn=dbcon.conn;
        boolean flag=false;
        PreparedStatement ptstmt = conn.prepareStatement("SELECT username,password from user_info where username = ?");
        // PreparedStatement pt = (PreparedStatement) ((java.sql.Connection) conn).prepareStatement("SELECT username,password from users where username = ?");
        ptstmt.setString(1, user);
        ResultSet resultSet = ptstmt.executeQuery();

        // if user is not in the database
        if (!resultSet.isBeforeFirst()) {

            return false;
        } else {
            while (resultSet.next()) {
                String retreivedPassword = resultSet.getString("password");
                if (retreivedPassword.equals(pass))

                    flag=true;

            }
        }

        return flag;
    }
}