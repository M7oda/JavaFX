package com.example.demo5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherPage {
    Button profileButton;
    Button searchButton;
    Button setDegreeButton;
    Button logOutButton;
    int teacherID;
    Stage stage;
    LogIn logIn;
    TeacherProfilePage teacherProfilePage;
    GridPane grid;
    SearchStudentPage searchStudentPage;
    SetDegreePage setDegreePage;


    TeacherPage (Stage stage , int teacherID) throws IOException {
        this.teacherID = teacherID;
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls(){
        profileButton = new Button("Profile");
        searchButton = new Button("Search");
        setDegreeButton = new Button("Set Degree");
        logOutButton = new Button("Log Out");
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
    }

    void renderScene(){
        grid.add(profileButton,0,0);
        grid.add(searchButton,0,1);
        grid.add(setDegreeButton,0,2);
        grid.add(logOutButton,0,3);
    }

    void applyScene(){
        profileButton.getStyleClass().add("button");
        searchButton.getStyleClass().add("button");
        setDegreeButton.getStyleClass().add("button");
        logOutButton.getStyleClass().add("button");
    }

    void initActions (){

        profileButton.setOnAction(event -> {
           teacherProfilePage = new TeacherProfilePage(stage,teacherID);
           Scene scene = teacherProfilePage.getScene();
           scene.getStylesheets().add("Style.css");
           stage.setScene(scene);
           stage.show();
        });
        logOutButton.setOnAction(event -> {
           try{
               logIn = new LogIn(stage);
               Scene scene = logIn.getScene();
               scene.getStylesheets().add("Style.css");
               stage.setScene(scene);
           } catch (Exception e) {
               System.out.println(e);
           }
        });

        searchButton.setOnAction(event -> {
            try{
                searchStudentPage = new SearchStudentPage(stage,teacherID);
                Scene scene = searchStudentPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.out.println(e);
            }
        });

        setDegreeButton.setOnAction(event -> {
           try{
               setDegreePage = new SetDegreePage(stage,teacherID);
               Scene scene = setDegreePage.getScene();
               scene.getStylesheets().add("Style.css");
               stage.setScene(scene);
               stage.show();
           }catch (Exception e){
               System.out.println(e);
           }
        });
    }
    Scene getScene(){
        return new Scene(grid,600,400);
    }
}
