package com.example.demo5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class HelloApplication extends Application {
LogIn logIn;
    public void start(Stage stage) throws IOException {
        
        logIn = new LogIn(stage);

        Scene scene = logIn.getScene();
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}