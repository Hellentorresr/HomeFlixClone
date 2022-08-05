package controller;

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
import view.Main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditarEliminarVideoController {
    /**
     * Atributos clase EditarEliminarVideoController
     */
    public Button btnRegresar;
    @FXML
    private Button btnConver;
    @FXML
    private Button btnEliminarVideo;
    @FXML
    private Button btnEvitar;
    @FXML
    private Button btnMostrarReporte;
    @FXML
    private Button btnPathVideo;
    @FXML
    private ImageView imageViewCover;
    @FXML
    private TextArea textAreaReporte;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtCodigoParaEditar;
    @FXML
    private TextField txtCodigoParaEliminar;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNombreVideo;
    private String image = "";

    /**
     *Metodo para mostrar information en el contenedor textArea
     */
    @FXML
    void btnMostrarReporteEvent(ActionEvent event) throws SQLException {
        textAreaReporte.setText(VideoDAOImplement.devolverInfo());
    }

    /**
     * Metodo para mostrar mensaje
     */
    private void mostrarMensaje() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Completado");
        alert.setContentText("Cambios realizados correctamente!");
        alert.showAndWait();
    }

    /**
     * Metodo para adjuntar imagen
     */
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


    /**
     *Metodo para regresar a la página principal
     */
    public void regresarAPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
