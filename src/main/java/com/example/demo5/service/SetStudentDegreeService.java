package com.example.demo5.service;

import com.example.demo5.db.StudentDataAccessLayer;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.Student;

public class SetStudentDegreeService {
    StudentDataAccessLayer studentDataAccessLayer ;
    public SetStudentDegreeService(StudentDataAccessLayer studentDataAccessLayer){
        this.studentDataAccessLayer = studentDataAccessLayer;
    }
//    public ErrorDTO prepareTOSetStudentDegree(String newCgpa , Student student){
//        if (newCgpa.isEmpty()){
//            return new ErrorDTO("The new CGPA is empty");
//        }else if (student == null){
//            return new ErrorDTO("you do not select any students");
//        } else if (Double.parseDouble(newCgpa) < 0.0 || Double.parseDouble(newCgpa) > 4.0) {
//            return new ErrorDTO("CGPA out of range");
//        }
//        return new ErrorDTO(null);
//    }
    public ErrorDTO prepareTOSetStudentDegree(String name , String cgpa , String level){
        if (name.isEmpty()){
            return new ErrorDTO("Name is empty");
        } else if (cgpa.isEmpty()) {
            return new ErrorDTO("CGPA is empty");
        } else if (level.isEmpty()) {
            return new ErrorDTO("Level is empty");
        } else if (!name.matches("[a-zA-Z\\s]*")) {
            return new ErrorDTO("Name only letters");
        } else if (!cgpa.matches("\\d*(\\.\\d*)?")) {
            return new ErrorDTO("CGPA only numbers");
        } else if (!level.matches("\\d+")) {
            return new ErrorDTO("Level only numbers");
        }
        return new ErrorDTO(null);
    }
}
