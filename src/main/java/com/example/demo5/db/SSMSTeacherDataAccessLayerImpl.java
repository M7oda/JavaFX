package com.example.demo5.db;

import com.example.demo5.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SSMSTeacherDataAccessLayerImpl implements TeacherDataAccessLayer{
    public void saveTeacher(String name , String phoneNO , String email , String password){
        try {
            Connection connection = CreateConnection.createConnection();
            String query = "INSERT INTO Teachers (name, phoneNO ,email, password) VALUES (? , ? , ? , ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2 , phoneNO);
            statement.setString(3 , email);
            statement.setString(4 , password);
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public Teacher teacherLogin(String email , String password){
        try{
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM Teachers WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Teacher(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("phoneNO"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
