package controller;

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
     *Metodo para regresar a la página principal
     */
    public void regresarAPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
