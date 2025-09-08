package com.example.demo5.service;

import com.example.demo5.db.StudentDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.LoginStudentResponse;
public class StudentLoginService {
    StudentDataAccessLayer studentDataAccessLayer;


    public StudentLoginService(StudentDataAccessLayer studentDataAccessLayer){
        this.studentDataAccessLayer = studentDataAccessLayer;
    }

    public LoginStudentResponse prepareStudentLogin(String email , String password){

        if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                int id = studentDataAccessLayer.studentLogin(email , hashPassword);
                if (id != -1) {
                    return new LoginStudentResponse(studentDataAccessLayer.searchStudent(id),null);
                }else {
                    return new LoginStudentResponse(null,new ErrorDTO("Student not found"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (email.isEmpty()) {
            return new LoginStudentResponse(null ,new ErrorDTO("The email is empty"));
        } else if (password.isEmpty()) {
            return new LoginStudentResponse(null, new ErrorDTO("The password is empty"));
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return new LoginStudentResponse(null ,new ErrorDTO("Invalid email"));
        }
        return new LoginStudentResponse(null,null);
    }

}
