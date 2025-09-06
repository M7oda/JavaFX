package com.example.demo5.ui;

import com.example.demo5.model.Teacher;
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
    Stage stage;
    LogInPage logInPage;
    TeacherProfilePage teacherProfilePage;
    GridPane grid;
    SearchStudentPage searchStudentPage;
    StudentsTableView studentsTableView;
    SetDegreePageTableView setDegreePageTableView;
    Teacher teacher;


    TeacherPage (Stage stage , Teacher teacher) throws IOException {
        this.teacher = teacher;
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
           teacherProfilePage = new TeacherProfilePage(stage,teacher);
           Scene scene = teacherProfilePage.getScene();
           scene.getStylesheets().add("Style.css");
           stage.setScene(scene);
           stage.show();
        });
        logOutButton.setOnAction(event -> {
           try{
               logInPage = new LogInPage(stage);
               Scene scene = logInPage.getScene();
               scene.getStylesheets().add("Style.css");
               stage.setScene(scene);
           } catch (Exception e) {
               System.out.println(e);
           }
        });

        searchButton.setOnAction(event -> {
            try{
                searchStudentPage = new SearchStudentPage(stage,teacher);
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
                setDegreePageTableView = new SetDegreePageTableView(stage , teacher);
                Scene scene = setDegreePageTableView.getScene();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
    Scene getScene(){
        return new Scene(grid,600,400);
    }
}
