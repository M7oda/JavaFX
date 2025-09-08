package com.example.demo5.model;

public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private double cgpa;
    private int level;

     public StudentDTO(int id , String name , String email , double cgpa , int level){
         this.id = id;
         this.name = name;
         this.email = email;
         this.cgpa = cgpa;
         this.level=level;
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

    public double getCgpa() {
        return cgpa;
    }

    public int getLevel() {
        return level;
    }
}
