package controller;

import com.sun.javafx.charts.Legend;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controller.DentroDeLaAppController.videosBaseDatos;


public class ControllerVideoEncontrado implements Initializable {
    public static List<Video> recientesPlayed = new ArrayList<>();

    public static TextField buscarPlaceholder;
    public static Label videoEncontrado;

    public static Button btnReproducir = new Button();

    public static ImageView portadaEncontrada;

    public static void mostrarVideo() {
        String busqueda = buscarPlaceholder.getText();
        if (busqueda.equals("")) {
            mostrarMensajeNegativo("Favor ingrese el nombre del video que desea encontrar");
        } else {
            for (int i = 0; i <= videosBaseDatos.size()-1; i++) {
                if (videosBaseDatos.get(i).getNombreVideo().equals(busqueda)){
                    Video video = videosBaseDatos.get(i);
                    portadaEncontrada = new ImageView();
                    portadaEncontrada.setFitWidth(200);
                    portadaEncontrada.setFitHeight(200);
                    portadaEncontrada.setImage(new Image("file:///" + video.getCover()));
                    videoEncontrado = new Label();
                    videoEncontrado.setText(video.getNombreVideo());
                    //falta que el boton de reproducir btnreproducir abra ventana de reproductor
                }
            }
        }

    }

    private void mostrarMensaje(String busqueda) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Completado");
        alert.setContentText(busqueda);
        alert.showAndWait();
    }

    private static void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
