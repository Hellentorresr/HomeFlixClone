/**
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import controller.dao.DAOPlayListVideos;
import controller.dao.PlaylistVideoDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.PlaylistVideos;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static controller.controllerApp.HomeController.video;

/**
 * Creacion de la clase AddPlaylistVideoController
 */
public class AddPlaylistVideoController {
    @FXML
    private TextField inputTextF;
    public Button btnRegresar;
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
    private UtilitiesImplements utilitiesImplements;
    private DAOPlayListVideos daoPlayListVideos;


    /**
     * Constructor de la clase AddPlayListVideoController
     */
    public AddPlaylistVideoController() {
        this.playlistVideos = new PlaylistVideos();
        utilitiesImplements = new UtilitiesImplements();
        daoPlayListVideos = new PlaylistVideoDAOImplement();

    }

    /**
     * metodo addRegistration
     */
    @FXML
    void addRegistration() throws SQLException, IOException {
        String name = this.txtNameList.getText();
        String tema = this.txtTemaList.getText();

        if (name.isEmpty() || tema.isEmpty()) {
            utilitiesImplements.mostrarMensajeNegativo("Favor llenar los campos en blanco");
        } else {
            float duration =0;
            LocalDate dateNow = LocalDate.now();
            //agregar input id
            daoPlayListVideos.insert(name,duration,tema,dateNow);
            utilitiesImplements.mostrarMensajePositivo("Playlist Creada correctamente");
            regresar();
        }
    }


    public void regresar() throws IOException {
        utilitiesImplements.pathInterfazGrafica("Home.fxml",btnRegresar);
    }
}
