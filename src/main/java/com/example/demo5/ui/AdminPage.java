package com.example.demo5;

import com.example.demo5.ui.AddNewStudentPage;
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
    LogIn logIn;
    ShowAllStudentsPage showAllStudentsPage;
    GridPane gridPane;
    Stage stage;

    public AdminPage(Stage stage)throws IOException{
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();

    }

    void initControls(){
        welcomeLabel = new Label("Welcome to the Admin Page");
        addNewStudentButton = new Button("Add New Student");
        addNewTeacherButton = new Button("Add New Teacher");
        logOutButton = new Button("Log Out");
        showAllStudentsButton = new Button("Show All Students");
        try {
            addNewStudentPage = new AddNewStudentPage(stage);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            addNewTeacherPage = new AddNewTeacherPage(stage);
            logIn = new LogIn(stage);
        } catch (IOException e) {
            System.out.println(e);
        }
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(welcomeLabel,0,0);
        gridPane.add(addNewStudentButton,0,1);
        gridPane.add(addNewTeacherButton,0,2);
        gridPane.add(showAllStudentsButton,0,3);
        gridPane.add(logOutButton,0,4);
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
    }

    void initActions()  {
        addNewStudentButton.setOnAction(event -> {
            try {
                addNewStudentPage = new AddNewStudentPage(stage);
                Scene scene = addNewStudentPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                System.out.println(e);
            }
        });

        addNewTeacherButton.setOnAction(event -> {
            Scene scene = addNewTeacherPage.getScene();
            scene.getStylesheets().add("Style.css");
            stage.setScene(scene);
            stage.show();
        });
        logOutButton.setOnAction(event -> {
            Scene scene = logIn.getScene();
            scene.getStylesheets().add("Style.css");
            stage.setScene(scene);
            stage.show();
        });
        showAllStudentsButton.setOnAction(event -> {
            showAllStudentsPage = new ShowAllStudentsPage(stage);
            Scene scene = showAllStudentsPage.getScene();
            stage.setScene(scene);
            stage.show();
        });
    }


   public Scene getScene(){
       return  new Scene(gridPane,600,400);
    }

}
