package com.example.alarm;

import java.sql.*;
import java.util.ArrayList;

public class table2 {
    public boolean insert_val2(String productID, String ProductName, String productprice, String totalavailable, String product_Description, String imageURL) {
        try {
            // 1) Register the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;

            Statement stmt = ((java.sql.Connection) conn).createStatement();

            //if (!isUser(u_name)) {
            String query = "Insert into product_table values('"+productID+"', '"+ProductName+"','"+productprice+"','"+totalavailable+"','"+product_Description+"','"+imageURL+"' )";
            int a = stmt.executeUpdate(query);

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




    public  ArrayList<Product> getProductlist()
    {

        ArrayList<Product> productlist=new ArrayList<>();
        try {
            // 1) Register the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;
            Statement stmt = ((java.sql.Connection) conn).createStatement();
            ResultSet rset;
            rset=stmt.executeQuery("SELECT  * FROM Product_Table");
            int i=0;
            while (rset.next())
            {
                Product newproduct=new Product();

                newproduct.Product_ID=rset.getString(1);
                newproduct.Product_Name=rset.getString(2);
                newproduct.Product_Price=rset.getString(3);
                newproduct.Product_Total_Available=rset.getString(4);
                newproduct.Product_Description=rset.getString(5);
                newproduct.Product_Image_URL=rset.getString(6);

                productlist.add(i,newproduct);

            }

        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        } catch (ClassNotFoundException e) {
            System.out.println(" Failed to register driver. Exception code : " + e);
        }


        return productlist;
    }


}