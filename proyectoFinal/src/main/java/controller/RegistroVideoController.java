package controller;

import controller.dao.VideoDAO;
import controller.dao.VideoDAOImplement;
import javafx.event.ActionEvent;
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

public class RegistroVideoController {
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

    @FXML
    void handleBtnOpenFile(ActionEvent event) {
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

    @FXML
    void handleBtnOpenLinkVideo(ActionEvent event) {
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

    @FXML
    public void btnRegistrarVideo() throws IOException, SQLException {
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
            v = new Video(nombre, cate, desc, image, videoPath);
            VideoDAOImplement videoDAOImplement = new VideoDAOImplement();
            if (videoDAOImplement.getALL().contains(v)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No se pudo hacer el registrar el video, verificar si el video ya existe!");
                alert.showAndWait();
            } else {
                VideoDAO videoDAO = new VideoDAOImplement();
                videoDAO.insert(nombre, cate, fecha, desc, false, image, videoPath);
                irPrincipal();
            }
        }
    }

    public void irPrincipal() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) irHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

