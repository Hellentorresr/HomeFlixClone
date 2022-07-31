package tl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class RegistroVideoController {
    /**
     * Atributos de la clase RegistroVideoController
     */
    public Button irHome;
    public Button irVideo;
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

    public void btnRegistrarVideo() {
        String nombre = this.txtNombreVideo.getText();
        String cate = this.txtCategoria.getText();
        String desc = this.txtDescription.getText();
        String videoPath = String.valueOf(this.textAreaVideoPath.getText());
        LocalDate fecha = LocalDate.now();
        Administracion.setVideos(nombre,cate,fecha,desc,1,image,videoPath);
        System.out.println(Administracion.videos);
    }

    public void irPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage)irHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void irVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("ReproductorVideo.fxml")));
        Stage window = (Stage)irVideo.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

