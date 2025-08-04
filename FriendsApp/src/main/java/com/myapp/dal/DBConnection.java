package com.myapp.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded!");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=MyFriendsDB;user=sa;password=sqlserver;trustServerCertificate=true;";

            connection =  DriverManager.getConnection(url);

        }
        catch (SQLException| ClassNotFoundException exception) {
            System.out.println(exception.getStackTrace());
        }
        return connection;
    }

}
