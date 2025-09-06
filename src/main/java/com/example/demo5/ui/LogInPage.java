package com.example.demo5.ui;

import com.example.demo5.db.SSMSAdminDataAccessLayerImpl;
import com.example.demo5.db.SSMSStudentDataAccessLayerImpl;
import com.example.demo5.db.SSMSTeacherDataAccessLayerImpl;
import com.example.demo5.model.Admin;
import com.example.demo5.model.LoginResponse;
import com.example.demo5.model.Student;
import com.example.demo5.model.Teacher;
import com.example.demo5.service.AdminLoginService;
import com.example.demo5.service.StudentLoginService;
import com.example.demo5.service.TeacherLoginService;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInPage {
    Label emailLabel;
    Label passwordLabel;
    TextField emailTextField;
    PasswordField passwordTextField;
    Button loginButton;
    GridPane gridPane;
    Label welcomeLabel;
    AdminPage adminPage;
    Stage stage;
    int studentId;
    int teacherId;
    StudentPage studentPage;
    TeacherPage teacherPage;
    RadioButton studentRadioButton;
    RadioButton teacherRadioButton;
    RadioButton adminRadioButton;
    ToggleGroup loginToggleGroup;
    Label loginAsLabel;

    public LogInPage(Stage stage)throws IOException {
        this.stage = stage;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }

    void initControls() {
        emailLabel = new Label("Email:");
        passwordLabel = new Label("Password:");
        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        loginButton = new Button("Login");
        gridPane = new GridPane();
        welcomeLabel = new Label("Welcome to the Login Page");
        teacherRadioButton = new RadioButton("Teacher");
        adminRadioButton = new RadioButton("Admin");
        studentRadioButton = new RadioButton("Student");
        loginToggleGroup = new ToggleGroup();
        studentRadioButton.setToggleGroup(loginToggleGroup);
        teacherRadioButton.setToggleGroup(loginToggleGroup);
        adminRadioButton.setToggleGroup(loginToggleGroup);
        loginAsLabel = new Label("Login as : ");
    }

    void renderScene(){
        gridPane.add(welcomeLabel,0,0,2,1);
        gridPane.add(emailLabel,0,1);
        gridPane.add(emailTextField,1,1);
        gridPane.add(passwordLabel,0,2);
        gridPane.add(passwordTextField,1,2);
        gridPane.add(loginAsLabel,0,3);
        gridPane.add(adminRadioButton , 1,3);
        gridPane.add(teacherRadioButton , 2,3);
        gridPane.add(studentRadioButton,3,3);
        gridPane.add(loginButton,0,4,2,1);
        GridPane.setHalignment(loginButton, HPos.CENTER );
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }


    void applyScene(){
        welcomeLabel.getStyleClass().add("title");
        emailLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        loginButton.getStyleClass().add("button");
        emailTextField.getStyleClass().add("text-field");
        passwordTextField.getStyleClass().add("text-field");
        loginAsLabel.getStyleClass().add("Label");
    }

    void initActions() {

        loginButton.setOnAction(event -> {
            RadioButton selectedRadioButton = (RadioButton) loginToggleGroup.getSelectedToggle();
            if (selectedRadioButton != null) {

                if (studentRadioButton.isSelected()) {
                    SSMSStudentDataAccessLayerImpl studentDataAccessLayer = new SSMSStudentDataAccessLayerImpl();
                    StudentLoginService studentLoginService = new StudentLoginService(studentDataAccessLayer);
                    LoginResponse loginStudentResponse = studentLoginService.prepareStudentLogin(emailTextField.getText(), passwordTextField.getText());
                    if (loginStudentResponse.getErrorDTO() == null) {
                        if (loginStudentResponse.getObject() instanceof Student) {
                            Student student = (Student) loginStudentResponse.getObject();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done");
                            alert.setHeaderText("Log In Successfully");
                            alert.setContentText("You have successfully logged in : " + student.getName());
                            alert.showAndWait();
                            studentPage = new StudentPage(stage, student);
                            Scene scene = studentPage.getScene();
                            scene.getStylesheets().add("Style.css");
                            stage.setScene(scene);
                            stage.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(loginStudentResponse.getErrorDTO().getErrorMessage());
                        alert.showAndWait();
                    }
                } else if (teacherRadioButton.isSelected()) {
                    SSMSTeacherDataAccessLayerImpl ssmsTeacherDataAccessLayer = new SSMSTeacherDataAccessLayerImpl();
                    TeacherLoginService teacherLoginService = new TeacherLoginService(ssmsTeacherDataAccessLayer);
                    LoginResponse loginTeacherResponse = teacherLoginService.prepareTeacherLogin(emailTextField.getText(), passwordTextField.getText());

                    if (loginTeacherResponse.getErrorDTO() == null) {
                        if (loginTeacherResponse.getObject() instanceof Teacher) {
                            Teacher teacher = (Teacher) loginTeacherResponse.getObject();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done");
                            alert.setHeaderText("Log In Successfully");
                            alert.setContentText("You have successfully logged in : " + teacher.getName());
                            alert.showAndWait();
                            try {
                                teacherPage = new TeacherPage(stage, teacher);
                                Scene scene = teacherPage.getScene();
                                scene.getStylesheets().add("Style.css");
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(loginTeacherResponse.getErrorDTO().getErrorMessage());
                        alert.showAndWait();
                    }
                } else if (adminRadioButton.isSelected()) {
                    SSMSAdminDataAccessLayerImpl ssmsAdminDataAccessLayer = new SSMSAdminDataAccessLayerImpl();
                    AdminLoginService adminLoginService = new AdminLoginService(ssmsAdminDataAccessLayer);
                    LoginResponse loginAdminResponse = adminLoginService.prepareAdminLogin(emailTextField.getText(), passwordTextField.getText());

                    if (loginAdminResponse.getErrorDTO() == null) {
                        if (loginAdminResponse.getObject() instanceof Admin) {
                            Admin admin = (Admin) loginAdminResponse.getObject();
                            try {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Done");
                                alert.setHeaderText("Log In Successfully");
                                alert.setContentText("You have successfully logged in : " + admin.getName());
                                alert.showAndWait();
                                adminPage = new AdminPage(stage, admin);
                                Scene scene = adminPage.getScene();
                                scene.getStylesheets().add("Style.css");
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(loginAdminResponse.getErrorDTO().getErrorMessage());
                        alert.showAndWait();
                    }

                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Choice one of the radio button");
                alert.showAndWait();
            }

        });
    }

    public Scene getScene(){
        return new Scene(gridPane,600,400);
    }
}
