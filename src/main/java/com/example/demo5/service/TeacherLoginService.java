package com.example.demo5.service;

import com.example.demo5.db.TeacherDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.LoginStudentResponse;
import com.example.demo5.model.LoginTeacherResponse;
import com.example.demo5.model.Teacher;

public class TeacherLoginService {
    public TeacherDataAccessLayer teacherDataAccessLayer;

    public TeacherLoginService(TeacherDataAccessLayer teacherDataAccessLayer){
        this.teacherDataAccessLayer = teacherDataAccessLayer;
    }

    public LoginTeacherResponse prepareTeacherLogin(String email , String password){
        if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                int id = teacherDataAccessLayer.teacherLogin(email , hashPassword);
                if (id != -1) {
                    return new LoginTeacherResponse(teacherDataAccessLayer.searchTeacher(id) , null);
                }else {
                    return new LoginTeacherResponse(null , new ErrorDTO("Teacher not found"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (email.isEmpty()) {
            return new LoginTeacherResponse(null,new ErrorDTO("The email is empty"));
        } else if (password.isEmpty()) {
            return new LoginTeacherResponse(null, new ErrorDTO("The password is empty"));
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return new LoginTeacherResponse(null,new ErrorDTO("Invalid email"));
        }
        return new LoginTeacherResponse(null,null);
    }
}
