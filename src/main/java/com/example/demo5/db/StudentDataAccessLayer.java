package com.example.demo5.db;


import com.example.demo5.model.Student;

public interface StudentDataAccessLayer {
    public void saveStudent(String name , String email , String password);
    public Boolean searchStudent(int id);
    public Boolean setStudentDegree(int id , double degree);
    public Student studentLogin(String email , String password);
}
