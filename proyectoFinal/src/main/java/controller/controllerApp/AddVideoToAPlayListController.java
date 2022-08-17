package controller.controllerApp;


import controller.dao.PlaylistVideoDAOImplement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PlaylistVideos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Clase AddVideoToAPlayListController
 */
public class AddVideoToAPlayListController implements Initializable {
    UtilitiesImplements utilitiesImplements;
    PlaylistVideoDAOImplement playlistVideoDAOImplement;
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
    private TableView<PlaylistVideos> tabla;

    private ObservableList<PlaylistVideos> playlistVideosOb;
    @FXML
    private TextField inputTextF;

    public AddVideoToAPlayListController() {
        this.utilitiesImplements = new UtilitiesImplements();
        playlistVideosOb = FXCollections.observableArrayList();
        playlistVideoDAOImplement = new PlaylistVideoDAOImplement();
    }

    /**
     * Metodo para el registro del video
     */
    public void enviar() {
    }

    /**
     * Metodo para el regreso
     */
    @FXML
    public void regresar() throws IOException {
        utilitiesImplements.pathInterfazGrafica("Home.fxml", btnRegresar);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarDatos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos() throws SQLException {
        playlistVideosOb.addAll(playlistVideoDAOImplement.getALL());
        this.idLista.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nombreLista.setCellValueFactory(new PropertyValueFactory<>("namePlaylist"));
        tabla.setItems(playlistVideosOb);
    }
}
