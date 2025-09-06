package com.example.demo5.model;

public class ErrorDTO {

    String error;
    public ErrorDTO(String error){
        this.error = error;
    }
    public String getErrorMessage(){
        return error;
    }
}
