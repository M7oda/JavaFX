package com.example.demo5.ui;

import com.example.demo5.db.SqlLiteStudentDataAccessLayerImpl;
import com.example.demo5.service.AddNewTeacherService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SetDegreePage {
    Label studentIdLabel;
    TextField studentIdTextField;
    Label degreeLabel;
    TextField degreeTextField;
    Button setDegreeButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;
    TeacherPage teacherPage;
    int teacherId;

    SetDegreePage(Stage stage, int teacherId) {
        this.stage = stage;
        this.teacherId = teacherId;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls(){
        studentIdLabel = new Label("Enter Student ID : ");
        studentIdTextField = new TextField();
        degreeLabel = new Label("Enter Degree : ");
        degreeTextField = new TextField();
        setDegreeButton = new Button("Set Degree");
        backButton = new Button("Back");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(studentIdLabel,0,0);
        gridPane.add(studentIdTextField,1,0);
        gridPane.add(degreeLabel,0,1);
        gridPane.add(degreeTextField,1,1);
        gridPane.add(setDegreeButton,1,2);
        gridPane.add(backButton,0,2);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        studentIdLabel.getStyleClass().add("label");
        studentIdTextField.getStyleClass().add("text-field");
        degreeLabel.getStyleClass().add("label");
        degreeTextField.getStyleClass().add("text-field");
        setDegreeButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");
    }

    void initActions(){
        setDegreeButton.setOnAction(event -> {
            SqlLiteStudentDataAccessLayerImpl sqlLiteStudentDataAccessLayer = new SqlLiteStudentDataAccessLayerImpl();
            AddNewTeacherService addNewTeacherService ;
        });

        backButton.setOnAction(event -> {
            try{
                teacherPage = new TeacherPage(stage, teacherId);
                Scene scene = teacherPage.getScene();
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.out.println(e);
            }
        });
    }

    Scene getScene(){
        return  new Scene(gridPane, 600 , 400);
    }
}
