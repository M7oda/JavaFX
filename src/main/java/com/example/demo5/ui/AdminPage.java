package com.example.demo5.ui;

import com.example.demo5.model.Admin;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPage {
    Label welcomeLabel;
    Button addNewStudentButton;
    Button addNewTeacherButton;
    AddNewStudentPage addNewStudentPage;
    AddNewTeacherPage addNewTeacherPage;
    Button logOutButton;
    Button showAllStudentsButton;
    LogInPage logInPage;
    ShowAllStudentsPage showAllStudentsPage;
    GridPane gridPane;
    Stage stage;
    Admin admin;
    Button profileButton;
    AdminProfilePage adminProfilePage;

    public AdminPage(Stage stage , Admin admin)throws IOException{
        this.stage = stage;
        this.admin=admin;
        initControls();
        renderScene();
        applyScene();
        initActions();

    }

    void initControls(){
        gridPane = new GridPane();
        welcomeLabel = new Label("Welcome to the Admin Page : " + admin.getName());
        addNewStudentButton = new Button("Add New Student");
        addNewTeacherButton = new Button("Add New Teacher");
        logOutButton = new Button("Log Out");
        showAllStudentsButton = new Button("Show All Students");
        profileButton = new Button("Profile");
    }

    void renderScene(){
        gridPane.add(welcomeLabel,0,0);
        gridPane.add(addNewStudentButton,0,1);
        gridPane.add(addNewTeacherButton,0,2);
        gridPane.add(showAllStudentsButton,0,3);
        gridPane.add(profileButton,0,4);
        gridPane.add(logOutButton,0,5);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        welcomeLabel.getStyleClass().add("title");
        addNewStudentButton.getStyleClass().add("button");
        addNewTeacherButton.getStyleClass().add("button");
        logOutButton.getStyleClass().add("button");
        showAllStudentsButton.getStyleClass().add("button");
        profileButton.getStyleClass().add("button");
    }

    void initActions()  {
        addNewStudentButton.setOnAction(event -> {
            try {
                addNewStudentPage = new AddNewStudentPage(stage , admin);
                Scene scene = addNewStudentPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                System.out.println(e);
            }
        });

        addNewTeacherButton.setOnAction(event -> {
            try {
                addNewTeacherPage = new AddNewTeacherPage(stage, admin);
                Scene scene = addNewTeacherPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        logOutButton.setOnAction(event -> {
            try {
                logInPage = new LogInPage(stage);
                Scene scene = logInPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        showAllStudentsButton.setOnAction(event -> {
            showAllStudentsPage = new ShowAllStudentsPage(stage , admin);
            Scene scene = showAllStudentsPage.getScene();
            stage.setScene(scene);
            stage.show();
        });
        profileButton.setOnAction(e->{
            adminProfilePage = new AdminProfilePage(stage , admin);
            Scene scene = adminProfilePage.getScene();
            scene.getStylesheets().add("Style.css");
            stage.setScene(scene);
            stage.show();
        });
    }


   public Scene getScene(){
       return  new Scene(gridPane,600,400);
    }

}
