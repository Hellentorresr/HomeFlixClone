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

/**
 * Clase CRUDPlayListController
 */
public class CRUDPlayListController implements Initializable {
    /**
     * Atributos de la clase CRUDPlayListController
     */
    UtilitiesImplements utilitiesImplements;
    DAOPlayListVideos daoPlayListVideos;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<PlaylistVideos, String> columnDate;
    @FXML
    private TableColumn<PlaylistVideos, Integer> columnId;
    @FXML
    private TableColumn<PlaylistVideos, String> columnNombre;
    @FXML
    private TableColumn<PlaylistVideos, String> columnTema;
    @FXML
    private TextField inputIdEditar;
    @FXML
    private TextField inputIdEliminar;
    @FXML
    private TableView<PlaylistVideos> tabla;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTema;
    private ObservableList<PlaylistVideos> playlistVideosOb;

    public CRUDPlayListController() {
        this.playlistVideosOb = new UtilitiesImplements();
        playlistVideosOb = FXCollections.observableArrayList();
        daoPlayListVideos = new PlaylistVideoDAOImplement();
    }

    /**
     * Metodo editarLista
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    @FXML
    void editarLista() throws SQLException {
        String nombre = this.txtNombre.getText();
        String tema = this.txtTema.getText();
        if (this.inputIdEditar.getText().isEmpty()) {
            utilitiesImplements.mostrarMensajeNegativo("Favor ingresar código");
        } else {
            int id = Integer.parseInt(this.inputIdEditar.getText());
            if (utilitiesImplements.verificarPlay(id, nombre, tema)) {
                utilitiesImplements.mostrarMensajePositivo("Actualizado correctamente");
            } else {
                utilitiesImplements.mostrarMensajeNegativo("Lista con código : " + id + " No existe");
            }
        }
    }

    /**
     * metodo eliminarLista
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    @FXML
    void eliminarLista() throws SQLException {
        if (this.inputIdEliminar.getText().isEmpty()) {
            utilitiesImplements.mostrarMensajeNegativo("Favor ingresar código");
        } else {
            int id = Integer.parseInt(this.inputIdEliminar.getText());
            if (utilitiesImplements.verificarPlayEliminar(id)) {
                utilitiesImplements.mostrarMensajePositivo("Video eliminado correctamente");
            } else {
                utilitiesImplements.mostrarMensajeNegativo("Lista con código : " + id + " No existe");
            }
        }
    }
}
