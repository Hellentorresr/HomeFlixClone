package controller.controllerApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPlaylistInterface implements Initializable {

    @FXML
    private Button play;

    @FXML
    public TableColumn PLAY;
    @FXML
    public TableColumn totalPlayListDurationTime;
    @FXML
    public TableColumn creationDate;
    @FXML
    public TableColumn videos;
    @FXML
    public TableColumn tema;
    @FXML
    public TableColumn namePlaylist;

    @FXML
    public TableView TableViewPlaylists ;


    private void cargarDatos() {
      /*  ObservableList<Object> playlists = FXCollections.observableArrayList();
        playlists.addAll(PlaylistsVideoDAOImplement.allPlaylist);
        this.namePlaylist.setCellValueFactory(new PropertyValueFactory<>("namePlaylist"));
        this.tema.setCellValueFactory(new PropertyValueFactory<>("tema"));
        this.videos.setCellValueFactory(new PropertyValueFactory<>("videos"));
        this.creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        this.totalPlayListDurationTime.setCellValueFactory(new PropertyValueFactory<>("totalPlayListDurationTime"));
        this.PLAY.setCellValueFactory(new PropertyValueFactory<>(play));
        TableViewPlaylists.setItems(PlaylistsVideoDAOImplement.allPlaylist);
        System.out.println(TableViewPlaylists);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
    }
}