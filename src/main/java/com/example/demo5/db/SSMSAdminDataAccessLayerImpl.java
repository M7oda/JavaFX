package com.example.demo5.db;

import com.example.demo5.model.Admin;
import com.example.demo5.model.AdminDTO;
import com.example.demo5.model.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SSMSAdminDataAccessLayerImpl implements AdminDataAccessLayer{
    @Override
    public int AdminLogin(String email, String password) {
        try{
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM Admins WHERE email = ? AND password = ?";
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
    public AdminDTO searchAdmin(int id){
        try {
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM Admins WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new AdminDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
