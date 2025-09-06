package com.example.demo5.ui;

import com.example.demo5.model.Teacher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchStudentPage {
Label studentIdLabel;
TextField studentIdTextField;
Button backButton;
Button searchButton;
Label studentNameReselteLabel;
Label studentIdReselteLabel;
Label studentCgpaReselteLabel;
Label studentLevelReselteLabel;
GridPane gridPane;
Stage stage;
TeacherPage teacherPage;
Teacher teacher;

SearchStudentPage(Stage stage , Teacher teacher){
    this.stage=stage;
    this.teacher=teacher;
    initControls();
    renderScene();
    applyScene();
    initActions();
}

void initControls(){
    studentIdLabel=new Label("Enter Student ID :");
    studentIdTextField=new TextField();
    backButton=new Button("Back");
    searchButton=new Button("Search");
    studentNameReselteLabel=new Label();
    studentIdReselteLabel=new Label();
    studentCgpaReselteLabel=new Label();
    studentLevelReselteLabel=new Label();
    gridPane=new GridPane();
}

void renderScene(){
    gridPane.add(studentIdLabel,0,0);
    gridPane.add(studentIdTextField,1,0);
    gridPane.add(backButton,0,1);
    gridPane.add(searchButton,1,1);
    gridPane.add(studentNameReselteLabel,0,3,2,1);
    gridPane.add(studentIdReselteLabel,0,2,2,1);
    gridPane.add(studentLevelReselteLabel,0,4,2,1);
    gridPane.add(studentCgpaReselteLabel,0,5,2,1);
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setAlignment(Pos.CENTER);
}

void applyScene(){
    studentIdLabel.getStyleClass().add("label");
    studentIdTextField.getStyleClass().add("text-field");
    backButton.getStyleClass().add("button");
    searchButton.getStyleClass().add("button");
    studentNameReselteLabel.getStyleClass().add("label");
    studentIdReselteLabel.getStyleClass().add("text-field");
    studentLevelReselteLabel.getStyleClass().add("label");
    studentCgpaReselteLabel.getStyleClass().add("text-field");
}

void initActions(){
    backButton.setOnAction(event->{
        try {
            teacherPage = new TeacherPage(stage, teacher);
            Scene scene = teacherPage.getScene();
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    });
    searchButton.setOnAction(event->{
        try {
            String studentId = studentIdTextField.getText();
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(studentId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 studentIdReselteLabel.setText("Student ID : "+resultSet.getString("id"));
                 studentNameReselteLabel.setText("Name : "+resultSet.getString("studentName"));
                 studentLevelReselteLabel.setText("Level : "+resultSet.getString("studentLevel"));
                 studentCgpaReselteLabel.setText("CGPA : "+resultSet.getString("studentCgpa"));
                connection.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Student ID Not Found");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    });
}

Scene getScene(){
    return new Scene(gridPane,600,400);
}

}
