package com.example.demo5.ui;

import com.example.demo5.db.SqlLiteStudentDataAccessLayerImpl;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.Student;
import com.example.demo5.service.SetStudentDegreeService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class StudentEditPage {
    Label nameLabel;
    TextField nameTextField;
    Label cgpaLabel;
    TextField cgpaTextField;
    Label levelLabel;
    TextField levelTextField;
    Button backButton;
    Button saveChangesButton;
    Stage stage;
    int teacherId;
    Student student;
    GridPane gridPane;

    public StudentEditPage(Stage stage , int teacherId , Student student){
        this.stage = stage;
        this.teacherId = teacherId;
        this.student = student;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }
    public void initControls(){
        nameLabel = new Label("Name");
        nameTextField = new TextField();
        nameTextField.setText(student.getName());
        cgpaLabel = new Label("CGPA");
        cgpaTextField = new TextField();
        cgpaTextField.setText(String.valueOf(student.getCgpa()));
        levelLabel = new Label("Level");
        levelTextField = new TextField();
        levelTextField.setText(String.valueOf(student.getLevel()));
        backButton = new Button("Back");
        saveChangesButton = new Button("Save Changes");
        gridPane = new GridPane();
    }
    public void renderScene (){
        gridPane.add(nameLabel , 0 , 0);
        gridPane.add(nameTextField , 1 , 0);
        gridPane.add(cgpaLabel , 0 , 1);
        gridPane.add(cgpaTextField , 1 ,1);
        gridPane.add(levelLabel , 0 , 2);
        gridPane.add(levelTextField , 1 , 2);
        gridPane.add(backButton , 0 , 3);
        gridPane.add(saveChangesButton , 1 , 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }
    public void applyScene(){
        nameLabel.getStyleClass().add("Label");
        levelLabel.getStyleClass().add("Label");
        cgpaLabel.getStyleClass().add("Label");
        levelTextField.getStyleClass().add("TextField");
        cgpaTextField.getStyleClass().add("TextField");
        nameTextField.getStyleClass().add("TextField");
        backButton.getStyleClass().add("Button");
        saveChangesButton.getStyleClass().add("Button");
    }

    public void initActions(){
        backButton.setOnAction( e ->{
            SetDegreePageTableView setDegreePageTableView = new SetDegreePageTableView(stage , teacherId);
            Scene scene = setDegreePageTableView.getScene();
            stage.setScene(scene);
            stage.show();
        });
        saveChangesButton.setOnAction(e->{
            SqlLiteStudentDataAccessLayerImpl sqlLiteStudentDataAccessLayer = new SqlLiteStudentDataAccessLayerImpl();
            SetStudentDegreeService setStudentDegreeService = new SetStudentDegreeService(sqlLiteStudentDataAccessLayer);
            ErrorDTO errorDTO = setStudentDegreeService.prepareTOSetStudentDegree(nameTextField.getText(),cgpaTextField.getText(),levelTextField.getText());
            if (errorDTO.getError()==null){
                if (sqlLiteStudentDataAccessLayer.editStudent(student.getId() , nameTextField.getText(),Double.parseDouble(cgpaTextField.getText()),Integer.parseInt(levelTextField.getText()))){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Done");
                    alert.setHeaderText("Changes have been saved");
                    alert.showAndWait();
                    SetDegreePageTableView setDegreePageTableView = new SetDegreePageTableView(stage , teacherId);
                    Scene scene = setDegreePageTableView.getScene();
                    scene.getStylesheets().add("Style.css");
                    stage.setScene(scene);
                    stage.show();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(errorDTO.getError());
                alert.showAndWait();
            }
        });
    }

    public Scene getScene(){
        return new Scene(gridPane,600,400);
    }
}
