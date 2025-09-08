package com.example.demo5.model;

public class LoginStudentResponse {
    private  StudentDTO studentDTO;
    private ErrorDTO error;
    public LoginStudentResponse(StudentDTO studentDTO , ErrorDTO error){
        this.studentDTO = studentDTO;
        this.error = error;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public ErrorDTO getErrorDTO() {
        return error;
    }
}
