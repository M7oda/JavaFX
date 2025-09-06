package com.example.demo5.ui;

import com.example.demo5.db.CreateConnection;
import com.example.demo5.model.Student;
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
    Stage stage;
    LogInPage logInPage;

    Student student;

    public StudentPage(Stage stage , Student student) {
        this.stage = stage;
        this.student = student;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls(){
        gridPain = new GridPane();
        idLabel = new Label("ID : " + student.getId());
        nameLabel = new Label("Name : " + student.getName());
        emailLabel = new Label("Email : " + student.getEmail());
        levelLabel = new Label("Level : " + student.getLevel());
        cgpaLabel = new Label("CGPA : " + student.getCgpa());
        logOutButton = new Button("Log Out");
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
