package com.example.demo5.service;

import com.example.demo5.db.TeacherDataAccessLayer;
import com.example.demo5.model.ErrorDTO;

import java.security.NoSuchAlgorithmException;

public class AddNewTeacherService {
    TeacherDataAccessLayer teacherDataAccessLayer;

    public AddNewTeacherService(TeacherDataAccessLayer teacherDataAccessLayer){
        this.teacherDataAccessLayer = teacherDataAccessLayer;
    }

    public ErrorDTO prepareToCreateTeacher(String name , String phoneNO , String email , String password){
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")&&!phoneNO.isEmpty() && phoneNO.matches("\\d+") && name.matches("[a-zA-Z\\s]+") && phoneNO.length()==11){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                teacherDataAccessLayer.saveTeacher(name, phoneNO, email, hashPassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }else if (name.isEmpty()){
            return new ErrorDTO("The name is empty");
        } else if (email.isEmpty()) {
            return new ErrorDTO("The email is empty");
        } else if (password.isEmpty()) {
            return new ErrorDTO("The password  is empty");
        }else if (phoneNO.isEmpty()){
            return new ErrorDTO("the PhoneNO is empty");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
         return new ErrorDTO("Invalid email");
        } else if (!phoneNO.matches("\\d+")) {
            return new ErrorDTO("Phone number should be only numbers");
        } else if (!name.matches("[a-zA-Z\\s]+")) {
            return new ErrorDTO("Name must be only letters");
        } else if (phoneNO.length()!= 11) {
            return new ErrorDTO("Phone number should be 11 number");
        }

        return new ErrorDTO(null);
    }
    public ErrorDTO prepareToSetDegree(String id ,String degree){
        if (!degree.isEmpty() && !id.isEmpty() && id.matches("\\d+") && degree.matches("\\d*(\\.\\d*)?") && Double.parseDouble(degree) >= 0 && Double.parseDouble(degree) <=4 ){
        }else if (id.isEmpty()){
            return new ErrorDTO("The id is empty");
        } else if (degree.isEmpty()) {
            return new ErrorDTO("The degree is empty");
        } else if (id.matches("\\d+")) {
            return new ErrorDTO("ID numbers only");
        } else if (degree.matches("\\d*(\\.\\d*)?")) {
            return new ErrorDTO("CGPA numbers only");
        } else {
            return new ErrorDTO("CGPA out of range");
        }
        return new ErrorDTO(null);
    }
}
