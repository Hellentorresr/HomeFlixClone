package controller.controllerApp;

import controller.server.Servidor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Clase que llama al servidor
 */
public class EmpezarHostController {

    public Button btnEmpezar;
    public Label txtMostrar;

    /**
     * Metodo que llama al servidor
     */
    public void llamarServidor() {
        Servidor s = new Servidor();
        s.sever();
        txtMostrar.setText("casa");
    }
}
