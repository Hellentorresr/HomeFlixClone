/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static controller.controllerApp.HomeController.video;

/**
 * Creacion de la clase ReproductorVideoController
 */

public class ReproductorVideoController implements Initializable {
    public Button regresar;
    public Button noLike;
    public Button like;
    /**
     * Atributos de la clase ReproductorVideoController
     */
    UtilitiesImplements utilitiesImplements;
    RegisterVideoController rvc;
    @FXML
    private VBox vboxParent;
    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;

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
    public static float time;

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

    public ReproductorVideoController() {
        rvc = new RegisterVideoController();
        utilitiesImplements = new UtilitiesImplements();
    }

    /**
     * Metodo initialize que carga la interfaz con todos sus componentes
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//paquete de recursos=resource bundle
        final int IV_SIZE = 25;

        Media mediaVideo = new Media(new File(video.getVideoPath()).toURI().toString());
        rvc.actualizarPreferencia(video, like, noLike);
        if (video.isCalifica()) {
            like.setTextFill(Paint.valueOf("Green"));
            Image imagePlay = new Image(new File("src/media/meGusta.png").toURI().toString());
            ImageView m = new ImageView(imagePlay);
            m.setFitHeight(IV_SIZE);
            m.setFitWidth(IV_SIZE);
            like.setGraphic(m);
            like.setCursor(Cursor.cursor("hand"));
        }


        //with this object I can call methods like play,stop, pause
        //the mediaPlayer wraps the media object
        mpVideo = new MediaPlayer(mediaVideo);
        //this displays the media that we want to display
        mvVideo.setMediaPlayer(mpVideo);

        Image imagePlay = new Image(new File("src/media/play.png").toURI().toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitHeight(IV_SIZE);
        ivPlay.setFitWidth(IV_SIZE);

        Image imageStop = new Image(new File("src/media/stop.png").toURI().toString());
        ivPause = new ImageView(imageStop);
        ivPause.setFitHeight(IV_SIZE);
        ivPause.setFitWidth(IV_SIZE);

        Image imageRestart = new Image(new File("src/media/restart.png").toURI().toString());
        ivRestart = new ImageView(imageRestart);
        ivRestart.setFitHeight(IV_SIZE);
        ivRestart.setFitWidth(IV_SIZE);

        Image imageVolume = new Image(new File("src/media/volume.png").toURI().toString());
        ivVolume = new ImageView(imageVolume);
        ivVolume.setFitHeight(IV_SIZE);
        ivVolume.setFitWidth(IV_SIZE);

        Image imageFullScreen = new Image(new File("src/media/fullScreen.png").toURI().toString());
        ivFullScreen = new ImageView(imageFullScreen);
        ivFullScreen.setFitHeight(IV_SIZE);
        ivFullScreen.setFitWidth(IV_SIZE);

        Image imageMute = new Image(new File("src/media/mute.png").toURI().toString());
        ivMute = new ImageView(imageMute);
        ivMute.setFitHeight(IV_SIZE);
        ivMute.setFitWidth(IV_SIZE);

        Image imageExit = new Image(new File("src/media/exit.png").toURI().toString());
        ivExit = new ImageView(imageExit);
        ivExit.setFitHeight(IV_SIZE);
        ivExit.setFitWidth(IV_SIZE);

        //
        buttonPPR.setGraphic(ivPlay);//here I fixed
        labelVolume.setGraphic(ivMute);
        labelSpeed.setText("1x");
        labelFullScreen.setGraphic(ivFullScreen);

        //Start using the button or adding functionality
        buttonPPR.setOnAction(actionEvent -> {//funciona para cualquier nodo o componente
            bindCurrentTimeLabel();
            Button buttonPlay = (Button) actionEvent.getSource();//interface ActionListener
            if (atEndOfVideo) {
                sliderTime.setValue(0);
                atEndOfVideo = false;
                isPlaying = false;
            }
            if (isPlaying) {
                buttonPlay.setGraphic(ivPlay);
                mpVideo.pause();
                isPlaying = false;
            } else {
                buttonPlay.setGraphic(ivPause);
                mpVideo.play();
                isPlaying = true;
            }
        });


        //appear disappear button
        hboxVolume.getChildren().remove(sliderVolume);
        mpVideo.volumeProperty().bindBidirectional(sliderVolume.valueProperty());

        bindCurrentTimeLabel();

        sliderVolume.valueProperty().addListener(observable -> {
            mpVideo.setVolume(sliderVolume.getValue());
            if (mpVideo.getVolume() != 0.0) {
                labelVolume.setGraphic(ivVolume);
                isMuted = false;
            } else {
                labelVolume.setGraphic(ivMute);
                isMuted = true;
            }
        });

        labelSpeed.setOnMouseClicked(mouseEvent -> {
            if (labelSpeed.getText().equals("1x")) {
                labelSpeed.setText("2x");
                mpVideo.setRate(2.0);
            } else {
                labelSpeed.setText("1x");
                mpVideo.setRate(1.0);
            }
        });

        labelVolume.setOnMouseClicked(mouseEvent -> {
            if (isMuted) {
                labelVolume.setGraphic(ivVolume);
                sliderVolume.setValue(0.2);
                isMuted = false;
            } else {
                labelVolume.setGraphic(ivMute);
                sliderVolume.setValue(0);
                isMuted = true;
            }
        });

        labelVolume.setOnMouseEntered(mouseEvent -> {
            if (hboxVolume.lookup("#sliderVolume") == null) {
                hboxVolume.getChildren().add(sliderVolume);
                sliderVolume.setValue(mpVideo.getVolume());
            }
        });

        hboxVolume.setOnMouseExited(mouseEvent -> hboxVolume.getChildren().remove(sliderVolume));

        vboxParent.sceneProperty().addListener((observableValue, oldScene, newScene) -> mvVideo.fitHeightProperty().bind(newScene.heightProperty().subtract(hboxControls.heightProperty().add(20))));

        labelFullScreen.setOnMouseClicked(mouseEvent -> {
            Label label = (Label) mouseEvent.getSource();
            Stage stage = (Stage) label.getScene().getWindow();
            if (stage.isFullScreen()) {
                stage.setFullScreen(false);
                labelFullScreen.setGraphic(ivFullScreen);
            } else {
                stage.setFullScreen(true);
                labelFullScreen.setGraphic(ivExit);
            }

            stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    labelFullScreen.setGraphic(ivFullScreen);
                }
            });
        });

        mpVideo.totalDurationProperty().addListener((observableValue, oldDuration, newDuration) -> {
            bindCurrentTimeLabel();
            sliderTime.setMax(newDuration.toSeconds());
            labelTotalTime.setText(getTime(newDuration));
            double total = newDuration.toMinutes();
            total = Double.parseDouble(new DecimalFormat("##.##").format(total));
            time = (float) total;
            try {
                utilitiesImplements.addVideoDuration();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //Time slider I want to add a listener to the value changing property
        sliderTime.valueChangingProperty().addListener((observableValue, wasChanging, isChanging) -> {
            //what I want to do is once the slider has stopped changing meaning the user has let go of the slider ball,
            //so I have to set the video to that time
            bindCurrentTimeLabel();
            if (!isChanging) {//if this is no longer true
                mpVideo.seek(Duration.seconds(sliderTime.getValue()));
            }
        });

        sliderTime.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            bindCurrentTimeLabel();
            double currentTime = mpVideo.getCurrentTime().toSeconds();
            if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {// half second
                mpVideo.seek(Duration.seconds(newValue.doubleValue()));
            }
            labelMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
        });

        mpVideo.currentTimeProperty().addListener((observableValue, oldTime, newTime) -> {
            bindCurrentTimeLabel();
            if (!sliderTime.isValueChanging()) {
                sliderTime.setValue(newTime.toSeconds());
            }
            labelMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
        });

        mpVideo.setOnEndOfMedia(() -> {
            buttonPPR.setGraphic(ivRestart);
            atEndOfVideo = true;
            if (!labelCurrentTime.textProperty().equals(labelTotalTime.textProperty())) {
                labelCurrentTime.setText(getTime(mpVideo.getTotalDuration()) + " / ");
            }
        });
    }

    public void bindCurrentTimeLabel() {
        labelCurrentTime.textProperty().bind(Bindings.createStringBinding(() -> getTime(mpVideo.getCurrentTime()) + " / ", mpVideo.currentTimeProperty()));
    }

    public String getTime(Duration time) {
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;

        if (hours > 0) return String.format("%d:%02d:%02d",
                hours,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);
    }

    public void labelMatchEndVideo(String labelTime, String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {
            if (labelTime.charAt(i) != labelTotalTime.charAt(i)) {
                atEndOfVideo = false;
                if (isPlaying) buttonPPR.setGraphic(ivPause);
                else buttonPPR.setGraphic(ivPlay);
                break;
            } else {
                atEndOfVideo = true;
                buttonPPR.setGraphic(ivRestart);
            }
        }
    }

    /**
     * Metodo regresarHome para regresar a la interfaz Home.fxml
     * @throws IOException dara un error si no encuentra la interfaz
     */
    public void regresarHome() throws IOException {
        utilitiesImplements.pathInterfazGrafica("Home.fxml", regresar);
    }
}
