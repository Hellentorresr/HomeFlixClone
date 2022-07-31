package tl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReproductorVideoController {
    /**
     * Atributos de la clase ReproductorVideoController
     */
    public Button regresar;
    @FXML
    private VBox vboxParent;
    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;
    private Media mediaVideo;

    @FXML
    private HBox hboxControls;
    @FXML
    private HBox hboxVolume;
    @FXML
    private Button buttonPPR;
    @FXML
    private Label labelCurrentTime;
    @FXML
    private Label labelTotalTime;
    @FXML
    private Label labelFullScreen;
    @FXML
    private Label labelSpeed;
    @FXML
    private Label labelVolume;
    @FXML
    private Slider sliderVolume;
    @FXML
    private Slider sliderTime;

    /**
     * Declarar algunas variables booleanas para determinar si nuestro video se está reproduciendo o no
     * si es el final del video o si el video está silenciado----
     * --Cuando el video se cargue por primera vez, el final del video es falso--prívate boolean atEndOfVideo = false;
     * --el comienzo del video se reproduce como verdadero porque cuando comenzamos a ejecutarlo-- prívate boolean isPlaying = true;
     * --la variable isMuted es verdadera al principio porque será silenciada desde el principio
     */
    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = true;

    /**
     * variables for the button with images
     */
    private ImageView ivPlay;
    private ImageView ivPause;
    private ImageView ivRestart;
    private ImageView ivVolume;
    private ImageView ivFullScreen;
    private ImageView ivMute;
    private ImageView ivExit;
}
