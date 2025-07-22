module com.example.demo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;
    requires java.desktop;


    opens com.example.demo5 to javafx.fxml;
    exports com.example.demo5;
}