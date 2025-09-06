package com.example.demo5.ui;

import com.example.demo5.model.Admin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowAllStudentsPage {
    BorderPane borderPane;
    Button backButton ;
    AdminPage adminPage;
    Stage stage;
    Admin admin;

ShowAllStudentsPage(Stage stage , Admin admin){
    this.stage=stage;
    this.admin = admin;
    initControls();
    renderScene();
    applyScene();
    initActions();
}


    void initControls(){
        borderPane = new BorderPane();
        backButton = new Button("Back");
        backButton.getStyleClass().add("Button");
    }

    void renderScene(){
        borderPane.setCenter(StudentsTableView.createStudentsTableViewForAdmin());
        backButton.getStyleClass().add("button");
        borderPane.setBottom(backButton);
    }
    void applyScene(){
        backButton.getStyleClass().add("button");
    }

    void initActions(){
        backButton.setOnAction(e -> {
            try {
                adminPage = new AdminPage(stage , admin);
                Scene scene =  adminPage.getScene();
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }
    Scene getScene(){
    return  new Scene(borderPane,600,400);
    }

}
