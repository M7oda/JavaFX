package com.example.demo5.model;

public class LoginResponse {
    private  Object object;
    private ErrorDTO error;
    public LoginResponse(Object object , ErrorDTO error){
        this.object = object;
        this.error = error;
    }

    public Object getObject() {
        return object;
    }

    public ErrorDTO getErrorDTO() {
        return error;
    }
}
