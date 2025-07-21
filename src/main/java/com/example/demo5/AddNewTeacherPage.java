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

public class AddNewTeacherPage {
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Label passwordLabel;
    TextField nameTextField;
    TextField emailTextField;
    TextField phoneTextField;
    PasswordField passwordTextField;
    Button addNewTeacherButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;

    public AddNewTeacherPage(Stage stage)throws IOException {
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls (){
        nameLabel = new Label("Name:");
        emailLabel = new Label("Email:");
        passwordLabel = new Label("Password:");
        phoneLabel = new Label("Phone:");
        nameTextField = new TextField();
        emailTextField = new TextField();
        phoneTextField = new TextField();
        passwordTextField = new PasswordField();
        addNewTeacherButton = new Button("Add New Teacher");
        backButton = new Button("Back");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(nameLabel,0,0);
        gridPane.add(emailLabel,0,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(phoneLabel,0,3);
        gridPane.add(nameTextField,1,0);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(phoneTextField,1,3);
        gridPane.add(addNewTeacherButton,0,4);
        gridPane.add(backButton,1,4);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){

    nameLabel.getStyleClass().add("label");
    emailLabel.getStyleClass().add("label");
    passwordLabel.getStyleClass().add("label");
    phoneLabel.getStyleClass().add("label");
    addNewTeacherButton.getStyleClass().add("button");
    emailTextField.getStyleClass().add("text-field");
    phoneTextField.getStyleClass().add("text-field");
    passwordTextField.getStyleClass().add("text-field");
    nameTextField.getStyleClass().add("text-field");
    backButton.getStyleClass().add("button");
    }

    void initActions(){
        addNewTeacherButton.setOnAction(event -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")&&!phone.isEmpty()){
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
                    String query = "INSERT INTO Teachers (name, phoneNO ,email, password) VALUES ('" + name + "', '" + phone + "' ,'" + email + "', '" + password + "')";

                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    System.out.println(e);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("Add New Teacher Successfully");
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
                } else if (phone.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The phone number is empty");
                    alert.showAndWait();
                } else {
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
        return  new Scene(gridPane,600,400);
    }
}
