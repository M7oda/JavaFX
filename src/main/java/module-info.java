module com.example.demo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.demo5 to javafx.fxml;
    exports com.example.demo5;
    exports com.example.demo5.ui;
    opens com.example.demo5.ui to javafx.fxml;
    exports com.example.demo5.model;
    opens com.example.demo5.model to javafx.fxml;
}