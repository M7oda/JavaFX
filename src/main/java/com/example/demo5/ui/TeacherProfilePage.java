package com.example.demo5.ui;

import com.example.demo5.model.Teacher;
import com.example.demo5.model.TeacherDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeacherProfilePage {
    Label idLabel;
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Button backButton;
    TeacherPage teacherPage;
    Stage stage;
    GridPane gridPain;
    TeacherDTO teacherDTO;

    public TeacherProfilePage(Stage stage , TeacherDTO teacherDTO) {
        this.stage = stage;
        this.teacherDTO = teacherDTO;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls() {
        gridPain = new GridPane();
        idLabel = new Label("ID : " + teacherDTO.getId());
        nameLabel = new Label("Name : " + teacherDTO.getName());
        emailLabel = new Label("Email : " + teacherDTO.getEmail());
        phoneLabel = new Label("Phone : " + teacherDTO.getPhoneNO());
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
                teacherPage = new TeacherPage(stage, teacherDTO);
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
