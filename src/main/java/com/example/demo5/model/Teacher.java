package com.example.demo5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {
    IntegerProperty id;
    StringProperty name;
    IntegerProperty phoneNO;
    StringProperty email;
    StringProperty password;
    public Teacher(int id , String name , int phoneNO , String email , String password){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phoneNO = new SimpleIntegerProperty(phoneNO);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }


    public int getId(){
        return id.get();
    }

    public int getPhoneNO(){
        return phoneNO.get();
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

}
