package com.example.demo5.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherProfilePage {
    Label idLabel;
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Button backButton;
    TeacherPage teacherPage;
    Stage stage;
    int teacherId;
    GridPane gridPain;

    public TeacherProfilePage(Stage stage , int teacherId ) {
        this.stage = stage;
        this.teacherId = teacherId;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
        String query = "SELECT * FROM Teachers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, teacherId);
        ResultSet resultSet = preparedStatement.executeQuery();
        idLabel = new Label("ID : " +  resultSet.getString("id"));
        nameLabel = new Label("Name : " +  resultSet.getString("name"));
        emailLabel = new Label("Email : " +  resultSet.getString("email"));
        phoneLabel = new Label("Phone : " +  resultSet.getString("phoneNO"));
        connection.close();
        backButton = new Button("back");
        gridPain = new GridPane();

    } catch (Exception e) {
        System.out.println(e);
    }
    }

    void renderScene(){
        gridPain.add(idLabel,0,0);
        gridPain.add(nameLabel,0,1);
        gridPain.add(emailLabel,0,2);
        gridPain.add(phoneLabel,0,3);
        gridPain.add(backButton,0,4,2,1);
        gridPain.setHgap(10);
        gridPain.setVgap(10);
        gridPain.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        idLabel.getStyleClass().add("label");
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        phoneLabel.getStyleClass().add("label");
        backButton.getStyleClass().add("button");
    }

    void initActions(){
        backButton.setOnAction(e -> {
            try {
                teacherPage = new TeacherPage(stage, teacherId);
                Scene scene = teacherPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex){
                System.out.println(ex);
            }
        });
    }

    Scene getScene(){
      return   new Scene(gridPain , 600, 400);
    }
}
