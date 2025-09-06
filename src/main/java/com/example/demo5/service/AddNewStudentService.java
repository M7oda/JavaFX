package com.example.demo5.service;

import com.example.demo5.db.StudentDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.ui.AddNewStudentPage;
import com.example.demo5.ui.AdminPage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AddNewStudentService {

    StudentDataAccessLayer studentDataAccessLayer;

    public AddNewStudentService(StudentDataAccessLayer studentDataAccessLayer ) {
        this.studentDataAccessLayer = studentDataAccessLayer;
    }

    public ErrorDTO prepareToCreateStudent(String name , String email , String password){
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$") && name.matches("[a-zA-Z\\s]+")) {
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                studentDataAccessLayer.saveStudent(name, email, hashPassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }else {
            if (name.isEmpty()){
                return new ErrorDTO("The name is empty");
            }else if (email.isEmpty()){
                return new ErrorDTO("The email is empty");
            }else if (password.isEmpty()){
                return new ErrorDTO("The password is empty");
            }else if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                return new ErrorDTO("Invalid email");
            } else if (!name.matches("[a-zA-Z\\s]+")) {
                return new ErrorDTO("Name must be only letters");
            }
        }
        return new ErrorDTO(null);
    }
}
