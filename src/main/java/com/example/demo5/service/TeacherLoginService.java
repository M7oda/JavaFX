package com.example.demo5.service;

import com.example.demo5.db.TeacherDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.LoginResponse;
import com.example.demo5.model.Teacher;

public class TeacherLoginService {
    public TeacherDataAccessLayer teacherDataAccessLayer;

    public TeacherLoginService(TeacherDataAccessLayer teacherDataAccessLayer){
        this.teacherDataAccessLayer = teacherDataAccessLayer;
    }

    public LoginResponse prepareTeacherLogin(String email , String password){
        if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                Teacher teacher = teacherDataAccessLayer.teacherLogin(email , hashPassword);
                if (teacher != null) {
                    return new LoginResponse(teacher , null);
                }else {
                    return new LoginResponse(null , new ErrorDTO("Teacher not found"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (email.isEmpty()) {
            return new LoginResponse(null,new ErrorDTO("The email is empty"));
        } else if (password.isEmpty()) {
            return new LoginResponse(null, new ErrorDTO("The password is empty"));
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return new LoginResponse(null,new ErrorDTO("Invalid email"));
        }
        return null;
    }
}
