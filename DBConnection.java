package com.donor;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/blood_bank_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yamuna";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully...");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return con;
    }
}