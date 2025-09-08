package com.example.demo5.model;

public class AdminDTO {
    private int id;
    private String name;
    private String email;
    public AdminDTO(int id , String name,String email){
        this.id = id;
        this.name=name;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
