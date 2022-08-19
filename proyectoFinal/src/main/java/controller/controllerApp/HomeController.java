/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import controller.dao.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.PlaylistVideos;
import model.Video;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Creacion de la clase DentroDeLaAppController
 */
public class HomeController implements Initializable {
    /**
     * Atributos de la clase DentroDeLaAppController
     */
    public static ArrayList<Video> videosBaseDatos = new ArrayList<>();
    public static ArrayList<PlaylistVideos> playlistVideos = new ArrayList<>();
    public static Video video = new Video();
    @FXML
    public ImageView fotoPerfil;
    @FXML
    public Button btnCerrar;
    @FXML
    public HBox recentlyPlayedContainer;
    @FXML
    public Button agregarVideo;
    public Button eliminarEditarVideo;
    public Label nombreDeUsuario;
    //Para la búsqueda de un video
    public TextField buscarPlaceholder;
    //boton para ir a crear una playlist
    public Button btnNewPlayList;
    /**
     * Padre de los containers
     */
    public VBox vboxContainer;//para que se le agregue hijos
    public Button btnListas;


    UtilitiesImplements utilitiesImplements;
    DAOVideo videoDAO;
    UsuarioDAOImplement UDI;
    ArrayList<Video> favoritas;
    private Button button;
    @FXML
    private HBox favoritasContainer;


    /**
     * Metodo constructor
     */
    public HomeController() {
        UDI = new UsuarioDAOImplement();
        this.utilitiesImplements = new UtilitiesImplements();
        videoDAO = new VideoDAOImplement();
    }

    /**
     * Metodo initialize que carga la interfaz con todos sus componentes
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
    }

    /**
     * metodo getData que agregar los elementos que debe mostrar en el initialize
     */

    public void getData() {
        try {
            videosBaseDatos = new ArrayList<>(videoDAO.getALL());
            nombreDeUsuario.setText(UDI.get(UDI.getUserId()).getUserName());
            playlistVideos = new ArrayList<>(utilitiesImplements.allPlaylist());
            cargarDatos(utilitiesImplements.recentAdd(), recentlyPlayedContainer);
            cargarDatos(videosBaseDatos, favoritasContainer);
            utilitiesImplements.cargarDatosDeLasListas(playlistVideos,vboxContainer);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos(ArrayList<Video> videos, HBox hbox) {
        for (int i = 0; i < videos.size(); i++) {
            ImageView img = new ImageView();
            img.setFitWidth(200);
            img.setFitHeight(200);
            img.setImage(new Image("file:///" + videos.get(i).getCover()));
            VBox vBox = new VBox(img);

            Label nombre = new Label();
            nombre.setText(videos.get(i).getNombreVideo());
            vBox.getChildren().add(nombre);
            nombre.setFont(Font.font(16));
            nombre.setTextFill(Paint.valueOf("#fff"));

            button = new Button(videos.get(i).getVideoPath());
            button.setTextFill(Paint.valueOf("Red"));
            button.setCursor(Cursor.cursor("hand"));

            int toGetIterator = i;
            button.setOnAction(event -> {
                video = videos.get(toGetIterator);
                try {
                    utilitiesImplements.pathInterfazGrafica("ReproductorVideo.fxml", button);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            button.setText("Reproducir");
            vBox.getChildren().add(button);
            hbox.getChildren().add(vBox);
        }
    }
    /**
     * Metodo para regresar a la página principalYSignIn por medio del boton cerrar
     *
     * @throws IOException genera una IOException si no encuentra la interfaz
     */
    public void handleBtnIngresar() throws IOException {
        utilitiesImplements.pathInterfazGrafica("PrincipalYSignIn.fxml", btnCerrar);
    }

    /**
     * Metodo que por medio del boton btnCerrar mueve al usuario a la interfaz RegistroVideo.fxml
     *
     * @throws IOException genera una IOException si no encuentra la interfaz
     */
    public void agregarVideo() throws IOException {
        utilitiesImplements.pathInterfazGrafica("RegistroVideo.fxml", btnCerrar);
    }


    /**
     * Metodo que por medio del boton btnCerrar mueve al usuario a la interfaz EditarEliminarVideo.fxml
     *
     * @throws IOException genera una IOException si no encuentra la interfaz
     */
    public void eliminarEditarVideo() throws IOException {
        utilitiesImplements.pathInterfazGrafica("EditarEliminarVideo.fxml", btnCerrar);
    }

    /**
     * Agregar comentario: ?
     */
    @FXML
    public Video buscarVideo() {
        String busqueda = buscarPlaceholder.getText();
        Video video = new Video();
        recentlyPlayedContainer.getChildren().clear();
        if (busqueda.isEmpty()) {
            utilitiesImplements.mostrarMensajeNegativo("Favor ingrese el nombre del video que desea encontrar");
            getData();
        } else {

            try {
                ArrayList<Video> videos = new ArrayList<>(videoDAO.getALL());
                for (int i = 0; i < videos.size(); i++) {
                    if (videos.get(i).getNombreVideo().equals(busqueda) || videos.get(i).getCategoryVideo().equals(busqueda)) {
                        video = videos.get(i);
                        videos = new ArrayList<>();
                        videos.add(video);
                        System.out.println("video encontrado " + video);

                        ImageView img = new ImageView();
                        img.setFitWidth(200);
                        img.setFitHeight(200);
                        img.setImage(new Image("file:///" + video.getCover()));
                        VBox vBox = new VBox(img);

                        Label nombre = new Label();
                        nombre.setText(video.getNombreVideo());
                        vBox.getChildren().add(nombre);
                        nombre.setFont(Font.font(16));
                        nombre.setTextFill(Paint.valueOf("#fff"));


                        button = new Button(video.getVideoPath());
                        button.setTextFill(Paint.valueOf("Red"));
                        button.setCursor(Cursor.cursor("hand"));


                        button.setOnAction(event -> {
                            try {
                                utilitiesImplements.pathInterfazGrafica("ReproductorVideo.fxml", button);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        button.setText("Reproducir");
                        vBox.getChildren().add(button);
                        recentlyPlayedContainer.getChildren().add(vBox);

                        //mostrarBusqueda();
                        break;
                    } else {
                        utilitiesImplements.mostrarMensajeNegativo("El video no fue encontrado");
                        getData();
                        break;
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return video;
    }


    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "DentroDeLaApp{" +
                ", button2=" + button +
                ", btnCerrar=" + btnCerrar +
                ", recentlyPlayedContainer=" + recentlyPlayedContainer +
                ", agregarVideo=" + agregarVideo +
                ", favoritas=" + favoritas +
                ", favoritasContainer=" + favoritasContainer +
                '}';
    }

    /**
     * Metodo goToCreateNewPlayList
     *
     * @throws IOException genera una IOException si no encuentra la interfaz
     */
    public void goToCreateNewPlayList() throws IOException {
        utilitiesImplements.pathInterfazGrafica("AddPlayList.fxml", btnNewPlayList);
    }

    /**
     * Metodo irAdministrarListas
     *
     * @throws IOException genera una IOException si no encuentra la interfaz
     */
    public void irAdministrarListas() throws IOException {
        utilitiesImplements.pathInterfazGrafica("CRUDPlayList.fxml", btnListas);
    }
}