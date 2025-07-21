package com.example.demo5;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LogIn {
    Label emailLabel;
    Label passwordLabel;
    TextField emailTextField;
    PasswordField passwordTextField;
    Button loginButton;
    GridPane gridPane;
    Label welcomeLabel;
    AdminPage adminPage;
    Stage stage;
    int studentId;
    int teacherId;
    StudentPage studentPage;
    TeacherPage teacherPage;

    public LogIn(Stage stage)throws IOException {
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls() {
        emailLabel = new Label("Email:");
        passwordLabel = new Label("Password:");
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        loginButton = new Button("Login");
        gridPane = new GridPane();
        welcomeLabel = new Label("Welcome to the Login Page");
    }

    void renderScene(){
        gridPane.add(welcomeLabel,0,0,2,1);
        gridPane.add(emailLabel,0,1);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(loginButton,0,3,2,1);
        GridPane.setHalignment(loginButton, HPos.CENTER );
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }


    void applyScene(){
        welcomeLabel.getStyleClass().add("title");
        emailLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        loginButton.getStyleClass().add("button");
        emailTextField.getStyleClass().add("text-field");
        passwordTextField.getStyleClass().add("text-field");
    }

    void initActions() {

        loginButton.setOnAction(event -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$" )){
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
                    String query = "SELECT * FROM Students WHERE email = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, email);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        studentId = resultSet.getInt("id");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Done");
                        alert.setHeaderText("Log In Successfully");
                        alert.setContentText("You have successfully logged in : " + name);
                        alert.showAndWait();
                        studentPage = new StudentPage(stage , resultSet.getInt("id"));
                        Scene scene =  studentPage.getScene();
                        scene.getStylesheets().add("Style.css");
                        stage.setScene(scene);
                        stage.show();

                    }else {
                        String teacherQuery = "SELECT * FROM Teachers WHERE email = ? AND password = ?";
                        PreparedStatement teacherStatement = connection.prepareStatement(teacherQuery);
                        teacherStatement.setString(1, email);
                        teacherStatement.setString(2, password);
                        ResultSet teacherResultSet = teacherStatement.executeQuery();
                        if (teacherResultSet.next()) {
                            teacherId = teacherResultSet.getInt("id");
                            String teacherName = teacherResultSet.getString("name");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done");
                            alert.setHeaderText("Log In Successfully");
                            alert.setContentText("You have successfully logged in : " + teacherName);
                            alert.showAndWait();
                            try {
                                teacherPage = new TeacherPage(stage, teacherId);
                                Scene scene =  teacherPage.getScene();
                                scene.getStylesheets().add("Style.css");
                                stage.setScene(scene);
                                stage.show();
                            }catch (Exception e){
                                System.out.println(e);
                            }

                        }else {
                            String adminQuery = "SELECT * FROM Admins WHERE email = ? AND password = ?";
                            PreparedStatement adminStatement = connection.prepareStatement(adminQuery);
                            adminStatement.setString(1, email);
                            adminStatement.setString(2, password);
                            ResultSet adminResultSet = adminStatement.executeQuery();
                            String adminName = adminResultSet.getString("name");
                            if (adminResultSet.next()) {
                                Alert  alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done");
                                alert.setHeaderText("Log In Successfully");
                                alert.setContentText("You have successfully logged in : " + adminName);
                                alert.showAndWait();
                                try {
                                    adminPage = new AdminPage(stage);
                                    Scene scene = adminPage.getScene();
                                    scene.getStylesheets().add("style.css");
                                    stage.setScene(scene);
                                    stage.show();
                                }catch (IOException e){
                                    System.out.println(e);
                                }
                            }else {

                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Email or password is incorrect");
                                alert.showAndWait();
                            }
                        }
                    }

                } catch (SQLException e) {
                    System.out.println(e);
                }


                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }else {
                if (email.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The email is empty");
                    alert.showAndWait();
                }else if (password.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("The password is empty");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid email");
                    alert.showAndWait();
                }
            }
        });
    }

    Scene getScene(){
        return new Scene(gridPane,600,400);
    }
}
