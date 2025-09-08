package com.example.demo5.ui;

import com.example.demo5.db.SSMSTeacherDataAccessLayerImpl;
import com.example.demo5.model.Admin;
import com.example.demo5.model.AdminDTO;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.service.AddNewTeacherService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewTeacherPage {
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Label passwordLabel;
    TextField nameTextField;
    TextField emailTextField;
    TextField phoneTextField;
    PasswordField passwordTextField;
    Button addNewTeacherButton;
    Button backButton;
    GridPane gridPane;
    Stage stage;
    AdminDTO adminDTO;
    public AddNewTeacherPage(Stage stage , AdminDTO adminDTO)throws IOException {
        this.stage = stage;
        this.adminDTO = adminDTO;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls (){
        nameLabel = new Label("Name:");
        emailLabel = new Label("Email:");
        passwordLabel = new Label("Password:");
        phoneLabel = new Label("Phone:");
        nameTextField = new TextField();
        emailTextField = new TextField();
        phoneTextField = new TextField();
        passwordTextField = new PasswordField();
        addNewTeacherButton = new Button("Add New Teacher");
        backButton = new Button("Back");
        gridPane = new GridPane();
    }

    void renderScene(){
        gridPane.add(nameLabel,0,0);
        gridPane.add(emailLabel,0,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(phoneLabel,0,3);
        gridPane.add(nameTextField,1,0);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(phoneTextField,1,3);
        gridPane.add(addNewTeacherButton,0,4);
        gridPane.add(backButton,1,4);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    void applyScene(){

    nameLabel.getStyleClass().add("label");
    emailLabel.getStyleClass().add("label");
    passwordLabel.getStyleClass().add("label");
    phoneLabel.getStyleClass().add("label");
    addNewTeacherButton.getStyleClass().add("button");
    emailTextField.getStyleClass().add("text-field");
    phoneTextField.getStyleClass().add("text-field");
    passwordTextField.getStyleClass().add("text-field");
    nameTextField.getStyleClass().add("text-field");
    backButton.getStyleClass().add("button");
    }

    void initActions(){
        addNewTeacherButton.setOnAction(event -> {
            SSMSTeacherDataAccessLayerImpl sqlLiteTeacherDataAccessLayer = new SSMSTeacherDataAccessLayerImpl();
            AddNewTeacherService addNewTeacherService = new AddNewTeacherService(sqlLiteTeacherDataAccessLayer);
            ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher(nameTextField.getText(),phoneTextField.getText(),emailTextField.getText(),passwordTextField.getText());
            if (errorDTO.getErrorMessage() == null){
                try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("Add New Teacher Successfully");
                alert.showAndWait();

                    AdminPage adminPage = new AdminPage(stage , adminDTO);
                    Scene scene = adminPage.getScene();
                    scene.getStylesheets().add("Style.css");
                    stage.setScene(scene);
                    stage.show();
                }catch (Exception e){
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

    Scene getScene(){
        return  new Scene(gridPane,600,400);
    }
}
