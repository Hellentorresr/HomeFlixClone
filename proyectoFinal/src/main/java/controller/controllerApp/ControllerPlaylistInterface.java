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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
    }

    private void cargarDatos() {
        ObservableList<Object> playlists = FXCollections.observableArrayList();
        playlists.addAll(PlaylistsVideoDAOImplement.allPlaylist);
        this.namePlaylist.setCellValueFactory(new PropertyValueFactory<>("namePlaylist"));
        this.tema.setCellValueFactory(new PropertyValueFactory<>("tema"));
        this.videos.setCellValueFactory(new PropertyValueFactory<>("videos"));
        this.creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        this.totalPlayListDurationTime.setCellValueFactory(new PropertyValueFactory<>("totalPlayListDurationTime"));
        this.PLAY.setCellValueFactory(new PropertyValueFactory<>(play));
        TableViewPlaylists.setItems(PlaylistsVideoDAOImplement.allPlaylist);
        System.out.println(TableViewPlaylists);
    }

}