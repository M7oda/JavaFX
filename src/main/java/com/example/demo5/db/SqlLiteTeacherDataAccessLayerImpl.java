package com.example.demo5.db;

import java.sql.Connection;
import java.sql.Statement;

public class SqlLiteTeacherDataAccessLayerImpl implements TeacherDataAccessLayer{
    public void saveTeacher(String name , String phoneNO , String email , String password){
        try {
            Connection connection = CreateConnection.createConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Teachers (name, phoneNO ,email, password) VALUES ('" + name + "', '" + phoneNO + "' ,'" + email + "', '" + password + "')";
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
