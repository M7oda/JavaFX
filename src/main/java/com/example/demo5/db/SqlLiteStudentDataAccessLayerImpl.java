package com.example.demo5.db;

import com.example.demo5.model.ErrorDTO;

import java.sql.*;

public class SqlLiteStudentDataAccessLayerImpl implements StudentDataAccessLayer{

    @Override
    public void saveStudent(String name , String email , String password ,int level) {
        try {
            Connection connection = CreateConnection.createConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Students (name , email, password , level ) VALUES ('" + name + "', '" + email + "', '" + password + "' , '" +  level + "' )";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Boolean searchStudent(int id){
        try {
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public Boolean setStudentDegree(int id , double degree){
        try {
            if (searchStudent(id)) {
                Connection connection = CreateConnection.createConnection();
                String setDegree = "UPDATE Students  SET cgpa = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(setDegree);
                preparedStatement.setDouble(1, degree);
                preparedStatement.setInt(2, id);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public Boolean editStudent (int id , String name , double cgpa , int level ){
        try {

            if (searchStudent(id)) {
                Connection connection = CreateConnection.createConnection();
                String query = "UPDATE Students  SET name = ? , cgpa = ? , level = ?  WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,name);
                preparedStatement.setDouble(2,cgpa);
                preparedStatement.setInt(3,level);
                preparedStatement.setInt(4 , id);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows >0){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
