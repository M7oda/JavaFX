package com.example.demo5.db;

import com.example.demo5.model.Student;
import com.example.demo5.model.StudentDTO;

import java.sql.*;

public class SSMSStudentDataAccessLayerImpl implements StudentDataAccessLayer{

    @Override
    public void saveStudent(String name , String email , String password) {
        try {
            Connection connection = CreateConnection.createConnection();
            String query = "INSERT INTO Students (name , email, password) VALUES (? , ? , ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,password);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public StudentDTO searchStudent(int id){
        try {
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new StudentDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDouble("cgpa"),
                        resultSet.getInt("level")
                );
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Boolean setStudentDegree(int id , double degree){
        try {
            if (searchStudent(id)!=null) {
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

            if (searchStudent(id)!=null) {
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

    public int studentLogin(String email, String password){
        try{
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM Students WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

}
