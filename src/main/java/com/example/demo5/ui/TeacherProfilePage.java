package com.example.demo5.ui;

import com.example.demo5.model.Teacher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherProfilePage {
    Label idLabel;
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Button backButton;
    TeacherPage teacherPage;
    Stage stage;
    GridPane gridPain;
    Teacher teacher;

    public TeacherProfilePage(Stage stage , Teacher teacher) {
        this.stage = stage;
        this.teacher = teacher;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls() {
        gridPain = new GridPane();
        idLabel = new Label("ID : " + teacher.getId());
        nameLabel = new Label("Name : " + teacher.getName());
        emailLabel = new Label("Email : " + teacher.getEmail());
        phoneLabel = new Label("Phone : " +teacher.getPhoneNO());
        backButton = new Button("back");
    }

    void renderScene(){
        gridPain.add(idLabel,0,0);
        gridPain.add(nameLabel,0,1);
        gridPain.add(emailLabel,0,2);
        gridPain.add(phoneLabel,0,3);
        gridPain.add(backButton,0,4,2,1);
        gridPain.setHgap(10);
        gridPain.setVgap(10);
        gridPain.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        idLabel.getStyleClass().add("label");
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        phoneLabel.getStyleClass().add("label");
        backButton.getStyleClass().add("button");
    }

    void initActions(){
        backButton.setOnAction(e -> {
            try {
                teacherPage = new TeacherPage(stage, teacher);
                Scene scene = teacherPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex){
                System.out.println(ex);
            }
        });
    }

    Scene getScene(){
      return   new Scene(gridPain , 600, 400);
    }
}
