package com.example.demo5;

import com.example.demo5.ui.LogInPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
LogInPage logInPage;
    public void start(Stage stage) throws IOException {
        
        logInPage = new LogInPage(stage);

        Scene scene = logInPage.getScene();
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}