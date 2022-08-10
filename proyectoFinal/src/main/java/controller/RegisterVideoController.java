/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package controller;

import controller.dao.DAOVideo;
import controller.dao.VideoDAOImplement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Video;
import view.Main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
/**
 * Creacion de la clase ReproductorVideoController
 */
public class RegisterVideoController {
    /**
     * Atributos de la clase RegistroVideoController
     */
    DAOVideo videoDAO;
    public Button irHome;
    public Button btnConver;
    @FXML
    private Button btnPathVideo;

    @FXML
    private Button btnRegistrarVideo;

    @FXML
    private ImageView imageViewCover;

    @FXML
    private TextArea textAreaVideoPath;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNombreVideo;
    private String image;
    public RegisterVideoController(){
        videoDAO = new VideoDAOImplement();
    }

    /**
     * Metodo actualizar referencias que hace el cambio del atributo de la clase Video de falso a verdadero,
     * Cuando es verdadero le agrega una imagen y el font se cambiara a verde
     * @param video recibe por parametro un Video para su actualizacion
     * @param like parametro de tipo Boton
     * @param noLike parametro de tipo Boton
     */
    public void actualizarPreferencia(Video video, Button like, Button noLike) {
        like.setOnAction(event -> {
            like.setText("Si me gusta");
            video.setCalifica(true);
            try {
                videoDAO.update(video);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        noLike.setOnAction(event -> {
            like.setText("Me gusta");
            video.setCalifica(false);
            try {
                videoDAO.update(video);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *Metodo para agregar una imagen para portada del video
     */
    @FXML
    void handleBtnOpenFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = file.toString();
            imageViewCover.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("Archivo no encontrado");
        }
    }

    /**
     *Metodo para agregar una un video por medio de su path
     */
    @FXML
    void handleBtnOpenLinkVideo() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Video");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.mkv", "*.mp4"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            textAreaVideoPath.appendText(file.getAbsolutePath());
        } else {
            System.out.println("Archivo no encontrado");
        }
    }

    /**
     *Metodo para registrar videos
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @FXML
    public void btnRegistrarVideo() throws IOException, SQLException {
        DAOVideo videoDAO = new VideoDAOImplement();
        Video v;
        String nombre = this.txtNombreVideo.getText();
        String cate = this.txtCategoria.getText();
        String desc = this.txtDescription.getText();
        String videoPath = String.valueOf(this.textAreaVideoPath.getText());
        LocalDate fecha = LocalDate.now();
        if (nombre.isEmpty() || videoPath.isEmpty() || image.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Favor llenar todos los campos!");
            alert.showAndWait();
        } else {
            v = new Video(nombre, cate, desc, image, videoPath,fecha);
            if (videoDAO.getALL().contains(v)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No se pudo hacer el registrar el video, verificar si el video ya existe!");
                alert.showAndWait();
            } else {
                videoDAO.insert(nombre, cate,  desc, image, videoPath,fecha);
                mostrarMensaje();
                irPrincipal();
            }
        }
    }

    /**
     *Metodo para hacer cambios de interfaz
     */
    public void irPrincipal() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) irHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     *Metodo para mostrar mensaje
     */
    private void mostrarMensaje() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Completado");
        alert.setContentText("Video registrado correctamente!");
        alert.showAndWait();
    }
}

