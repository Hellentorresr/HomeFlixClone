module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;


    opens view to javafx.fxml;
    exports view;
    exports controller;
    opens controller to javafx.fxml;
}