package com.example.demo5.model;

public class TeacherDTO {
    private int id;
    private String name;
    private String email;
    private int phoneNO;

    public TeacherDTO(int id , String name , String email , int phoneNO){
        this.id = id;
        this.name = name;
        this.email=email;
        this.phoneNO = phoneNO;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNO() {
        return phoneNO;
    }
}
