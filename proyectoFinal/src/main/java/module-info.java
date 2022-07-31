module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens view to javafx.fxml;
    exports view;
    exports tl;
    opens tl to javafx.fxml;
}