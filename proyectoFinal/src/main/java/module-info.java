module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;


    opens view to javafx.fxml;
    exports view;
    exports model;
    exports controller.controllerApp;
    exports controller.baseDatos;
    opens controller.controllerApp to javafx.fxml;
}