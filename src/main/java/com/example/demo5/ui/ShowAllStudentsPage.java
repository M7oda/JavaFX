package com.example.demo5.ui;

import com.example.demo5.model.AdminDTO;
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
    AdminDTO adminDTO;

ShowAllStudentsPage(Stage stage , AdminDTO adminDTO){
    this.stage=stage;
    this.adminDTO = adminDTO;
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
                adminPage = new AdminPage(stage , adminDTO);
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
