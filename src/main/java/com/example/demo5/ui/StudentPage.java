package com.example.demo5.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StudentPage {
    Label idLabel;
    Label nameLabel;
    Label emailLabel;
    Label levelLabel;
    Label cgpaLabel;
    Button logOutButton;
    GridPane gridPain;
    int studentId;
    Stage stage;
    LogInPage logInPage;

    public StudentPage(Stage stage ,int id) {
        this.stage = stage;
        studentId = id;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            idLabel = new Label("ID : " +  resultSet.getString("id"));
            nameLabel = new Label("Name : " +  resultSet.getString("name"));
            emailLabel = new Label("Email : " +  resultSet.getString("email"));
            levelLabel = new Label("Level : " +  resultSet.getString("level"));
            cgpaLabel = new Label("CGPA : " +  resultSet.getString("cgpa"));
            connection.close();
            logOutButton = new Button("Log Out");
            gridPain = new GridPane();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void renderScene(){
        gridPain.add(idLabel,0,0);
        gridPain.add(nameLabel,0,1);
        gridPain.add(emailLabel,0,2);
        gridPain.add(levelLabel,0,3);
        gridPain.add(cgpaLabel,0,4);
        gridPain.add(logOutButton,0,5,2,1);
        gridPain.setHgap(10);
        gridPain.setVgap(10);
        gridPain.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        idLabel.getStyleClass().add("label");
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        levelLabel.getStyleClass().add("label");
        cgpaLabel.getStyleClass().add("label");
        logOutButton.getStyleClass().add("button");
    }

    void initActions(){

            logOutButton.setOnAction(e -> {
                try {


                logInPage = new LogInPage(stage);
                    Scene scene = logInPage.getScene();
                    scene.getStylesheets().add("style.css");
                    stage.setScene(scene);
                    stage.show();
            } catch (IOException ex) {
                    System.out.println(ex);
                }
        });
    }

    Scene getScene(){
        return new Scene(gridPain,600,400);
    }
}
