package com.example.demo5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    IntegerProperty id;
    StringProperty name;
    StringProperty email;
    StringProperty password;

    public Admin(int id , String name , String email , String password){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public int getId(){
        return id.get();
    }
    public String getEmail(){
        return email.get();
    }

    public String getPassword(){
        return password.get();
    }
    public String getName(){
        return name.get();
    }
}
