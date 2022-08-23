package controller.controllerApp;

import controller.server.Server;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.InicioApp;

import java.io.IOException;
import java.util.Objects;

import static controller.controllerApp.HomeController.videosBaseDatos;

/**
 * Clase que llama al servidor
 */
public class EmpezarHostController {
    HomeController homeController;
    public Button btnEmpezar;
    public Label txtMostrar;

    Boolean signal = false;

    public EmpezarHostController() {
        homeController = new HomeController();
    }

    /**
     * Metodo que llama al servidor
     */
    public void llamarServidor() {
        Server s = new Server();
        s.serverConnectorJava();
    }
}
