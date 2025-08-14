package com.example.demo5.ui;

import com.example.demo5.db.SqlLiteStudentDataAccessLayerImpl;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.Student;
import com.example.demo5.service.SetStudentDegreeService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SetDegreePageTableView {
    BorderPane borderPane;
    TeacherPage teacherPage;
    Stage stage;
    Button backButton;
    int teacherId;
    Button selectButton;

    public SetDegreePageTableView(Stage stage , int teacherId){
        this.stage = stage;
        this.teacherId = teacherId;
        initControls();
        renderScene();
        applyScene();
        initActions();
    }
    public void initControls() {
        borderPane = new BorderPane();
        backButton = new Button("Back");
        selectButton = new Button("Select Student");
    }

    public void renderScene(){
        borderPane.setCenter(StudentsTableView.createTableViewForTeacher());
        selectButton.getStyleClass().add("Button");
        borderPane.setBottom(selectButton);
        VBox bottomBox = new VBox(10 , selectButton, backButton);
        bottomBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(bottomBox);

    }
    void applyScene(){
        selectButton.getStyleClass().add("button");
    }

    void initActions(){
        backButton.setOnAction(e -> {
            try {
                teacherPage = new TeacherPage(stage , teacherId);
                Scene scene =  teacherPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        selectButton.setOnAction(e->{
            Student selectedStudent = (Student) StudentsTableView.teacherTableView.getSelectionModel().getSelectedItem();
            if (selectedStudent!=null) {
                StudentEditPage studentEditPage = new StudentEditPage(stage, teacherId, selectedStudent);
                Scene scene = studentEditPage.getScene();
                scene.getStylesheets().add("Style.css");
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You should select one student");
                alert.showAndWait();
            }
        });
//        updateCgpaButton.setOnAction(e->{
//            SqlLiteStudentDataAccessLayerImpl sqlLiteStudentDataAccessLayer = new SqlLiteStudentDataAccessLayerImpl();
//            SetStudentDegreeService setStudentDegreeService = new SetStudentDegreeService(sqlLiteStudentDataAccessLayer);
//            Student selectedStudent = (Student) StudentsTableView.teacherTableView.getSelectionModel().getSelectedItem();
//            ErrorDTO errorDTO = setStudentDegreeService.prepareTOSetStudentDegree(cgpaTextField.getText(),selectedStudent );
//            if (errorDTO.getError() == null){
//                if (sqlLiteStudentDataAccessLayer.setStudentDegree(selectedStudent.getId() , Double.parseDouble(cgpaTextField.getText()))){
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Done");
//                    alert.setHeaderText("Set new CGPA Successfully");
//                    alert.showAndWait();
//                    selectedStudent.setCgpa(Double.parseDouble(cgpaTextField.getText()));
//                    StudentsTableView.teacherTableView.refresh();
//                    cgpaTextField.clear();
//                StudentEditPage studentEditPage = new StudentEditPage(stage , teacherId , selectedStudent);
//                }else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Something wrong");
//                alert.showAndWait();
//            }
//        }else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(errorDTO.getError());
//            alert.showAndWait();
//        }
//
//    });
}
Scene getScene(){
    return  new Scene(borderPane,600,400);
}
}
