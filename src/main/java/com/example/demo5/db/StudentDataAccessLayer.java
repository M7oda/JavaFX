package com.example.demo5.db;


import com.example.demo5.model.Student;
import com.example.demo5.model.StudentDTO;

public interface StudentDataAccessLayer {
    public void saveStudent(String name , String email , String password);
    public StudentDTO searchStudent(int id);
    public Boolean setStudentDegree(int id , double degree);
    public int studentLogin(String email , String password);
}
