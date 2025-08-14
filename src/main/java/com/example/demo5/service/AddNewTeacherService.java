package com.example.demo5.service;

import com.example.demo5.db.TeacherDataAccessLayer;
import com.example.demo5.model.ErrorDTO;

public class AddNewTeacherService {
    TeacherDataAccessLayer teacherDataAccessLayer;

    public AddNewTeacherService(TeacherDataAccessLayer teacherDataAccessLayer){
        this.teacherDataAccessLayer = teacherDataAccessLayer;
    }

    public ErrorDTO prepareToCreateTeacher(String name , String phoneNO , String email , String password){
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")&&!phoneNO.isEmpty()){

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
