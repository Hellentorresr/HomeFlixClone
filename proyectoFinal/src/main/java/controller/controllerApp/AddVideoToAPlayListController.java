package controller.controllerApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.PlaylistVideos;

import java.io.IOException;

/**
 * Clase AddVideoToAPlayListController
 */
public class AddVideoToAPlayListController {
    UtilitiesImplements utilitiesImplements;
    /**
     * atributos de la clase
     * AddVideoToAPlayListController
     */
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<PlaylistVideos, Integer> idLista;
    @FXML
    private TableColumn<PlaylistVideos, String> nombreLista;
    @FXML
    private TextField inputTextF;

    public AddVideoToAPlayListController() {
        this.utilitiesImplements = new UtilitiesImplements();
    }

    /**
     * Metodo para el registro del video
     */
    public void enviar() {
    }

    /**
     * Metodo para el regreso
     */
    public void regresar() throws IOException {
        utilitiesImplements.pathInterfazGrafica("Home.fxml",btnRegresar);
    }
}
