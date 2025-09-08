package com.example.demo5.model;

public class LoginTeacherResponse {
    private TeacherDTO teacherDTO;
    private ErrorDTO errorDTO;
    public LoginTeacherResponse(TeacherDTO teacherDTO , ErrorDTO errorDTO){
        this.errorDTO = errorDTO;
        this.teacherDTO = teacherDTO;
    }

    public TeacherDTO getTeacherDTO() {
        return teacherDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }
}
