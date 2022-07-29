module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens view to javafx.fxml;
    exports view;
    exports tl;
    opens tl to javafx.fxml;
}