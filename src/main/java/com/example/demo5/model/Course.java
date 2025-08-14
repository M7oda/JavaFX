package com.example.demo5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {

    IntegerProperty id;
    StringProperty courseName;
    public Course(int id , String courseName){
        this.id = new SimpleIntegerProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
    }

    public int getId(){
        return id.get();
    }

    public String getCourseName(){
        return courseName.get();
    }

}
