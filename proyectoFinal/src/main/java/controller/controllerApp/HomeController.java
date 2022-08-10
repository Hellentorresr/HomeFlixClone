/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package controller.controllerApp;

import controller.dao.UsuarioDAOImplement;
import controller.dao.DAOVideo;
import controller.dao.VideoDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Video;
import view.InicioApp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Creacion de la clase DentroDeLaAppController
 */
public class HomeController implements Initializable {
    /**
     * Atributos de la clase DentroDeLaAppController
     */
    public static ArrayList<Video> videosBaseDatos = new ArrayList<>();
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
    public Label videoEncontrado;
    DAOVideo videoDAO;
    UsuarioDAOImplement UDI;
    ArrayList<Video> favoritas;
    private Button btnBuscar;
    private Button button;
    @FXML
    private HBox favoritasContainer;


    /**
     * Metodo constructor
     */
    public HomeController() {
        videoDAO = new VideoDAOImplement();
        UDI = new UsuarioDAOImplement();
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

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < videosBaseDatos.size(); i++) {
            ImageView img = new ImageView();
            img.setFitWidth(200);
            img.setFitHeight(200);
            img.setImage(new Image("file:///" + videosBaseDatos.get(i).getCover()));
            VBox vBox = new VBox(img);

            Label nombre = new Label();
            nombre.setText(videosBaseDatos.get(i).getNombreVideo());
            vBox.getChildren().add(nombre);
            nombre.setFont(Font.font(16));
            nombre.setTextFill(Paint.valueOf("#fff"));

            button = new Button(videosBaseDatos.get(i).getVideoPath());
            button.setTextFill(Paint.valueOf("Red"));
            button.setCursor(Cursor.cursor("hand"));

            int finalI = i;
            button.setOnAction(event -> {
                video = videosBaseDatos.get(finalI);
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("ReproductorVideo.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage window = (Stage) button.getScene().getWindow();
                window.setScene(new Scene(root));
            });
            button.setText("Reproducir");
            vBox.getChildren().add(button);
            recentlyPlayedContainer.getChildren().add(vBox);
        }
    }

    /**
     * Metodo para regresar a la pagina principalYSignIn por medio del boton cerrar
     */
    public void handleBtnIngresar() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("PrincipalYSignIn.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Metodo que por medio del boton btnCerrar mueve al usuario a la interfaz RegistroVideo.fxml
     */
    public void agregarVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("RegistroVideo.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    /**
     * Metodo que por medio del boton btnCerrar mueve al usuario a la interfaz EditarEliminarVideo.fxml
     */
    public void eliminarEditarVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("EditarEliminarVideo.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
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
            mostrarMensajeNegativo("Favor ingrese el nombre del video que desea encontrar");
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
                            Parent root;
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("ReproductorVideo.fxml")));

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Stage window = (Stage) button.getScene().getWindow();
                            window.setScene(new Scene(root));
                        });
                        button.setText("Reproducir");
                        vBox.getChildren().add(button);
                        recentlyPlayedContainer.getChildren().add(vBox);

                        //mostrarBusqueda();
                        break;
                    } else {
                        mostrarMensajeNegativo("El video no fue encontrado");
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
     * Agregar comentario: ?
     */
    public void mostrarBusqueda() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("videoEncontrado.fxml")));
        Stage window = (Stage) btnBuscar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * metodo que muestra un mensaje al usuario
     */

    private void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
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
}