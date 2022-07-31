package tl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
}
