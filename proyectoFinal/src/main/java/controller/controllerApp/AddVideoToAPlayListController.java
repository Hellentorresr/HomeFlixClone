package controller.controllerApp;

import controller.dao.DAOPlayListVideos;
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
import static controller.controllerApp.HomeController.video;

/**
 * Clase AddVideoToAPlayListController
 */
public class AddVideoToAPlayListController implements Initializable {
    UtilitiesImplements utilitiesImplements;
    DAOPlayListVideos daoPlayListVideos;
    /**
     * atributos de la clase
     * AddVideoToAPlayListController
     */
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnIrRegreso;
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
        daoPlayListVideos = new PlaylistVideoDAOImplement();
    }

    /**
     * Metodo para el registro del video
     */
    public void enviar() throws SQLException {
        if (this.inputTextF.getText().isEmpty()) {
            utilitiesImplements.mostrarMensajeNegativo("Favor ingresar un id");
        } else {
            int id = Integer.parseInt(this.inputTextF.getText());
            if (utilitiesImplements.verificarSiExistePlayList(id,video)) {
                utilitiesImplements.mostrarMensajePositivo("encontrado");
                System.out.println(video);
            } else {
             utilitiesImplements.mostrarMensajeNegativo("NO ENCONTRADO");
            }
        }
    }

    /**
     * Metodo para el regreso
     */
    @FXML
    public void regresarHome() throws IOException {
        utilitiesImplements.pathInterfazGrafica("Home.fxml", btnIrRegreso);
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
        playlistVideosOb.addAll(daoPlayListVideos.getALL());
        this.idLista.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nombreLista.setCellValueFactory(new PropertyValueFactory<>("namePlaylist"));
        tabla.setItems(playlistVideosOb);
    }
}
