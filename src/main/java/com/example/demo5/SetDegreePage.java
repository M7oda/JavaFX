package com.example.demo5;

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

           if (!studentIdTextField.getText().isEmpty() &&  !degreeTextField.getText().isEmpty()) {
               if (studentIdTextField.getText().matches("\\d+")) {
                   int studentId = Integer.parseInt(studentIdTextField.getText());
                   try {
                       Class.forName("org.sqlite.JDBC");
                       Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
                       String query = "SELECT * FROM students WHERE id = ?";
                       PreparedStatement preparedStatement = connection.prepareStatement(query);
                       preparedStatement.setInt(1, studentId);
                       ResultSet resultSet = preparedStatement.executeQuery();
                       if (resultSet.next()) {
                           if (Double.parseDouble(degreeTextField.getText()) >= 0.0 && Double.parseDouble(degreeTextField.getText()) <= 4.0) {
                               String updateDegree = "UPDATE Students  SET cgpa = ? WHERE id = ?";
                               preparedStatement = connection.prepareStatement(updateDegree);
                               preparedStatement.setDouble(1, Double.parseDouble(degreeTextField.getText()));
                               preparedStatement.setInt(2, studentId);
                               int affectedRows = preparedStatement.executeUpdate();
                               if (affectedRows > 0) {
                                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                   alert.setTitle("Success");
                                   alert.setHeaderText("Success");
                                   alert.setContentText("Degree Updated Successfully");
                                   alert.showAndWait();
                               } else {
                                   Alert alert = new Alert(Alert.AlertType.ERROR);
                                   alert.setTitle("Error");
                                   alert.setHeaderText("Error");
                                   alert.setContentText("Degree Updated Failed");
                                   alert.showAndWait();
                               }
                           } else {
                               Alert alert = new Alert(Alert.AlertType.ERROR);
                               alert.setTitle("Error");
                               alert.setHeaderText("CGPA WRONG NUMBER");
                               alert.setContentText("The number you entered is above 4.0 or below 1.0");
                               alert.showAndWait();
                           }
                           connection.close();
                       } else {
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                           alert.setTitle("Error");
                           alert.setHeaderText("Error");
                           alert.setContentText("Student ID Not Found");
                           alert.showAndWait();
                       }
                   } catch (Exception e) {
                       System.out.println(e);
                   }

               } else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Error");
                   alert.setContentText("NUMBERS ONLY");
                   alert.showAndWait();
               }
           }else {
                   if (studentIdTextField.getText().isEmpty()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setHeaderText("Error");
                       alert.setContentText("Student ID Empty");
                       alert.showAndWait();
                   }else{
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setHeaderText("Error");
                       alert.setContentText("Degree Empty");
                       alert.showAndWait();
                   }
               }

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
