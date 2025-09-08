package com.example.demo5.ui;

import com.example.demo5.model.Admin;
import com.example.demo5.model.AdminDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminProfilePage {
    Stage stage;
    AdminDTO adminDTO;
    Label nameLabel;
    Label idLabel;
    Label emailLabel;
    GridPane gridPane;
    Button backButton;
    AdminPage adminPage;


    public AdminProfilePage(Stage stage , AdminDTO adminDTO){
        this.stage = stage;
        this.adminDTO = adminDTO;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    public void initControls(){
         gridPane = new GridPane();
        nameLabel = new Label("Name : " + adminDTO.getName());
        idLabel = new Label("ID : " + adminDTO.getId());
        emailLabel = new Label("Email : " + adminDTO.getEmail());
        backButton = new Button("Back");
    }
    public void renderScene(){
        gridPane.add(idLabel , 0, 0);
        gridPane.add(nameLabel , 0 , 1);
        gridPane.add(emailLabel , 0 ,2);
        gridPane.add(backButton , 0 , 3);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }
    public void applyScene(){
        idLabel.getStyleClass().add("label");
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        backButton.getStyleClass().add("button");
    }
    public void initActions(){
        backButton.setOnAction(e->{
            try {
                adminPage = new AdminPage(stage, adminDTO);
                Scene scene = adminPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            });
    }
    public Scene getScene(){
        return new Scene(gridPane,600,400);
    }
}
