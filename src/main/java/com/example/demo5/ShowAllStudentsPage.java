package com.example.demo5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowAllStudentsPage {
    BorderPane borderPane;
    Button backButton ;
    AdminPage adminPage;
    Stage stage;

ShowAllStudentsPage(Stage stage){
    this.stage=stage;
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
        borderPane.setCenter(StudentsTableView.createStudentsTableView());
        backButton.getStyleClass().add("button");
        borderPane.setBottom(backButton);
    }
    void applyScene(){
        backButton.getStyleClass().add("button");
    }

    void initActions(){
        backButton.setOnAction(e -> {
            try {
                adminPage = new AdminPage(stage);
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
