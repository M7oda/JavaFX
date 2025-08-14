package com.example.demo5;

import com.example.demo5.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentsTableView{
    static TableView<Student> createStudentsTableView() {
        TableView<Student> studentsTableView = new TableView<>();
        TableColumn<Student,Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Student, String> nameColumn =  new TableColumn<>("Name");
        TableColumn<Student, String> emailColumn =  new TableColumn<>("Email");
        TableColumn<Student, String> passwordColumn =  new TableColumn<>("Password");
        TableColumn<Student,Double> cgpaColumn =  new TableColumn<>("CGPA");
        TableColumn<Student,Integer>levelColumn =  new TableColumn<>("Level");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        cgpaColumn.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        studentsTableView.getColumns().addAll(idColumn, nameColumn, emailColumn, passwordColumn, cgpaColumn, levelColumn);
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/School.db");
            String query = "SELECT * FROM students ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                studentList.add(new Student(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getDouble("cgpa"),resultSet.getInt("level")));
            }
            studentsTableView.setItems(studentList);

        } catch (Exception e) {
            System.out.println(e);
        }
        return studentsTableView;

    }
}
