package com.example.demo5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewStudentPage {
    Label nameLabel;
    Label emailLabel;
    Label passwordLabel;
    TextField nameTextField;
    TextField emailTextField;
    PasswordField passwordTextField;
    Button addNewStudentButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;


    public AddNewStudentPage(Stage stage) throws IOException {
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();

    }

    void initControls(){
        nameLabel = new Label("Student Name");
        emailLabel = new Label("Student Email");
        passwordLabel = new Label("Student Password");
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        nameTextField = new TextField();
        addNewStudentButton = new Button("Add New Student");
        backButton = new Button("Back");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(nameLabel,0,0);
        gridPane.add(emailLabel,0,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(nameTextField,1,0);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(addNewStudentButton,0,3);
        gridPane.add(backButton,1,3);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        addNewStudentButton.getStyleClass().add("button");
        emailTextField.getStyleClass().add("text-field");
        passwordTextField.getStyleClass().add("text-field");
        nameTextField.getStyleClass().add("text-field");
        backButton.getStyleClass().add("button");
    }

    void initActions(){
       addNewStudentButton.setOnAction(event -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String name = nameTextField.getText();
            if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                }
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
                } catch (SQLException e) {
                    System.out.println(e);
                }
                try {
                    Statement statement = connection.createStatement();
                    String query = "INSERT INTO Students (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')";
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    System.out.println(e);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("Add New Student Successfully");
                alert.showAndWait();
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                try {

                    AdminPage adminPage = new AdminPage(stage);
                    Scene scene = adminPage.getScene();
                    scene.getStylesheets().add("style.css");
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException e){
                    System.out.println(e);
                }

            }else {
                if (name.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The name is empty");
                    alert.showAndWait();
                }else if (email.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The email is empty");
                    alert.showAndWait();
                }else if (password.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The password is empty");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid email");
                    alert.showAndWait();
                }

            }
        });
        backButton.setOnAction(event -> {
            try {
                AdminPage adminPage = new AdminPage(stage);
                Scene scene = adminPage.getScene();
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    Scene getScene(){
        return new Scene(gridPane,600,400);
    }

}
