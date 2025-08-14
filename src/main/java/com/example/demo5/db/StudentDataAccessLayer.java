package com.example.demo5.db;


public interface StudentDataAccessLayer {
    public void saveStudent(String name , String email , String password ,int level);
    public Boolean searchStudent(int id);
    public Boolean setStudentDegree(int id , double degree);
}
