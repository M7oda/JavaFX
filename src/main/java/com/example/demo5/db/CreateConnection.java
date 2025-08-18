package com.example.demo5.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
    static Connection connection;
    public static Connection createConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=School;encrypt=true;trustServerCertificate=true";
                String user = "sa";
                String password = "mmq1w2e344";
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

}
