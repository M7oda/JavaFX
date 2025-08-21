package com.example.demo5.ui;

import com.example.demo5.db.SqlLiteStudentDataAccessLayerImpl;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.service.AddNewStudentService;
import com.example.demo5.service.PasswordHashingService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AddNewStudentPage {
    Label nameLabel;
    Label emailLabel;
    Label passwordLabel;
    Label levelLabel;
    TextField nameTextField;
    TextField emailTextField;
    TextField levelTextField;
    PasswordField passwordTextField;
    Button addNewStudentButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;

    public AddNewStudentPage(Stage stage) throws IOException {
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();

    }

    void initControls(){
        nameLabel = new Label("Student Name");
        emailLabel = new Label("Student Email");
        passwordLabel = new Label("Student Password");
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        nameTextField = new TextField();
        levelTextField = new TextField();
        addNewStudentButton = new Button("Add New Student");
        backButton = new Button("Back");
        levelLabel = new Label("Level");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(nameLabel,0,0);
        gridPane.add(emailLabel,0,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(nameTextField,1,0);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(levelLabel,0,3);
        gridPane.add(levelTextField,1,3);
        gridPane.add(addNewStudentButton,0,4);
        gridPane.add(backButton,1,4);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){
        nameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        addNewStudentButton.getStyleClass().add("button");
        emailTextField.getStyleClass().add("text-field");
        passwordTextField.getStyleClass().add("text-field");
        nameTextField.getStyleClass().add("text-field");
        backButton.getStyleClass().add("button");
        levelTextField.getStyleClass().add("text-field");
        levelLabel.getStyleClass().add("label");
    }

    void initActions(){
       addNewStudentButton.setOnAction(event -> {
           SqlLiteStudentDataAccessLayerImpl  sqlLiteStudentDataAccessLayer = new SqlLiteStudentDataAccessLayerImpl();
           AddNewStudentService addStudentService = new AddNewStudentService(sqlLiteStudentDataAccessLayer);
           ErrorDTO errorDTO = addStudentService.prepareToCreateStudent( nameTextField.getText() , emailTextField.getText() , passwordTextField.getText() , levelTextField.getText());
            if ( errorDTO.getError() == null){
                String hashPassword;
                try {
                     hashPassword = PasswordHashingService.getHash(passwordTextField.getText());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                sqlLiteStudentDataAccessLayer.saveStudent(nameTextField.getText() , emailTextField.getText() , hashPassword , Integer.parseInt(levelTextField.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("Add New Student Successfully");
                alert.showAndWait();
                try {
                    AdminPage adminPage = new AdminPage(stage);
                    Scene scene = adminPage.getScene();
                    scene.getStylesheets().add("style.css");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(errorDTO.getError());
                alert.showAndWait();
            }


        });
        backButton.setOnAction(event -> {
            try {
                AdminPage adminPage = new AdminPage(stage);
                Scene scene = adminPage.getScene();
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

   public Scene getScene(){
        return new Scene(gridPane,600,400);
    }

}
