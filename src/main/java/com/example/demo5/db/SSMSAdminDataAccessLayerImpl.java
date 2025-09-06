package com.example.demo5.db;

import com.example.demo5.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SSMSAdminDataAccessLayerImpl implements AdminDataAccessLayer{
    @Override
    public Admin AdminLogin(String email, String password) {
        try{
            Connection connection = CreateConnection.createConnection();
            String query = "SELECT * FROM Admins WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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
