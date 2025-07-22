package com.example.demo5;

import javafx.beans.property.*;

public class Students {
    IntegerProperty id;
    StringProperty name;
    StringProperty email;
    StringProperty password;
    DoubleProperty cgpa;
    IntegerProperty level;

    Students(int id, String name, String email, String password, double cgpa ,  int level) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.cgpa = new SimpleDoubleProperty(cgpa);
        this.level = new SimpleIntegerProperty(level);
    }
    public String getName(){
        return name.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getPassword(){
        return password.get();
    }
    public double getCgpa(){
        return cgpa.get();
    }
    public int getLevel(){
        return level.get();
    }
    public int getId() {
        return id.get();
    }
}
