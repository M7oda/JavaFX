package com.example.demo5.ui;

import com.example.demo5.db.SSMSStudentDataAccessLayerImpl;
import com.example.demo5.model.AdminDTO;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.service.AddNewStudentService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewStudentPage {
    Label nameLabel;
    Label emailLabel;
    Label passwordLabel;
    TextField nameTextField;
    TextField emailTextField;
    PasswordField passwordTextField;
    Button addNewStudentButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;
    AdminDTO adminDTO;

    public AddNewStudentPage(Stage stage , AdminDTO adminDTO) throws IOException {
        this.stage = stage;
        this.adminDTO = adminDTO;
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
        addNewStudentButton = new Button("Add New Student");
        backButton = new Button("Back");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(nameLabel,0,0);
        gridPane.add(emailLabel,0,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(nameTextField,1,0);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(addNewStudentButton,0,3);
        gridPane.add(backButton,1,3);
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
    }

    void initActions(){
       addNewStudentButton.setOnAction(event -> {
           SSMSStudentDataAccessLayerImpl sqlLiteStudentDataAccessLayer = new SSMSStudentDataAccessLayerImpl();
           AddNewStudentService addStudentService = new AddNewStudentService(sqlLiteStudentDataAccessLayer);
           ErrorDTO errorDTO = addStudentService.prepareToCreateStudent( nameTextField.getText() , emailTextField.getText() , passwordTextField.getText());
            if ( errorDTO.getErrorMessage() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("Add New Student Successfully");
                alert.showAndWait();
                try {
                    AdminPage adminPage = new AdminPage(stage , adminDTO);
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
                alert.setHeaderText(errorDTO.getErrorMessage());
                alert.showAndWait();
            }


        });
        backButton.setOnAction(event -> {
            try {
                AdminPage adminPage = new AdminPage(stage , adminDTO);
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
