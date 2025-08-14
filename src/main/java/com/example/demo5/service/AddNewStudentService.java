package com.example.demo5.service;

import com.example.demo5.db.StudentDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.ui.AddNewStudentPage;
import com.example.demo5.ui.AdminPage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewStudentService {

    StudentDataAccessLayer studentDataAccessLayer;

    public AddNewStudentService(StudentDataAccessLayer studentDataAccessLayer ) {
        this.studentDataAccessLayer = studentDataAccessLayer;
    }

    public ErrorDTO prepareToCreateStudent(String name , String email , String password , String level){
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$") && !level.isEmpty() ) {

        }else {
            if (name.isEmpty()){
                return new ErrorDTO("The name is empty");
            }else if (email.isEmpty()){
                return new ErrorDTO("The email is empty");
            }else if (password.isEmpty()){
                return new ErrorDTO("The password is empty");
            } else if (level.isEmpty()) {
                return new ErrorDTO("The level is Empty");
            } else if (Integer.parseInt(level) <= 0 || Integer.parseInt(level) >= 5 ) {
                return new ErrorDTO("Level out of range");
            } else if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                return new ErrorDTO("Invalid email");
            }
        }
        return new ErrorDTO(null);
    }
}
