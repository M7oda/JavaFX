package com.example.demo5.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
    static Connection connection;
    public static Connection createConnection() {
        if (connection == null){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
        return connection;
    }
}
