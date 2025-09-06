package com.example.demo5.db;

import com.example.demo5.model.Teacher;

public interface TeacherDataAccessLayer {
    public void saveTeacher(String name , String phoneNO , String email , String password);
    public Teacher teacherLogin(String email , String password);

}
