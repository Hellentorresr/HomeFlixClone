/**
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.PlaylistVideos;

/**
 * Creacion de la clase AddPlaylistVideoController
 */
public class AddPlaylistVideoController {
    /**
     * Atributos de la clase AddPlaylistVideoController
     */

    @FXML
    private Button btnSend;
    @FXML
    private TextField txtNameList;
    @FXML
    private TextField txtTemaList;

    private PlaylistVideos playlistVideos;
    /**
     * metodo addRegistration
     */

    /**
     * Constructor de la clase AddPlayListVideoController
     */
    public AddPlaylistVideoController() {
        this.playlistVideos = new PlaylistVideos();
    }

    @FXML
    void addRegistration() {

    }
}
