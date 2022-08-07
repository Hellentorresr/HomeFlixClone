package controller;

import controller.dao.VideoDAO;
import controller.dao.VideoDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Video;
import view.Main;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DentroDeLaAppController implements Initializable {
    //listas
    public static List<Video> recientesPlayed = new ArrayList<>();
    public static String test;
    public static Button button2 = new Button();
    //
    private static ArrayList<Video> videosBaseDatos = new ArrayList<>();
    @FXML
    public Button btnCerrar;
    @FXML
    public HBox recentlyPlayedContainer;
    @FXML
    public Button agregarVideo;
    public Button eliminarEditarVideo;
    //para la foto y nombre del usuario que ingreso al sistema;
    public ImageView fotoPerfil;
    public Label nombreDeUsuario;
    ArrayList<Video> favoritas;
    @FXML
    private HBox favoritasContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VideoDAO videoDAO = new VideoDAOImplement();
        try {
            videosBaseDatos = new ArrayList<>(videoDAO.getALL());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        recientesPlayed = new ArrayList<>(videosBaseDatos);
        int i;
        for (i = 0; i < recientesPlayed.size(); i++) {
            ImageView img = new ImageView();
            img.setFitWidth(200);
            img.setFitHeight(200);
            img.setImage(new Image("file:///" + recientesPlayed.get(i).getCover()));
            VBox vBox = new VBox(img);

            Label nombre = new Label();
            nombre.setText(recientesPlayed.get(i).getNombreVideo());
            vBox.getChildren().add(nombre);
            nombre.setFont(Font.font(16));
            nombre.setTextFill(Paint.valueOf("#fff"));


            button2 = new Button(recientesPlayed.get(i).getVideoPath());
            button2.setTextFill(Paint.valueOf("Red"));
            button2.setCursor(Cursor.cursor("hand"));

            int finalI = i;
            button2.setOnAction(event -> {
                test = recientesPlayed.get(finalI).getVideoPath();
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("ReproductorVideo.fxml")));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage window = (Stage) button2.getScene().getWindow();
                window.setScene(new Scene(root));
            });
            button2.setText("Reproducir");
            vBox.getChildren().add(button2);
            recentlyPlayedContainer.getChildren().add(vBox);
        }
    }

    //--Methods---
    public void handleBtnIngresar() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PrincipalYSignIn.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void agregarVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("RegistroVideo.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public String toString() {
        return "DentroDeLaApp{" +
                ", button2=" + button2 +
                ", btnCerrar=" + btnCerrar +
                ", recentlyPlayedContainer=" + recentlyPlayedContainer +
                ", agregarVideo=" + agregarVideo +
                ", favoritas=" + favoritas +
                ", favoritasContainer=" + favoritasContainer +
                '}';
    }

    public void eliminarEditarVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("EditarEliminarVideo.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * funcion para mostrar la info del usuario que ingreso
     */
    public void infoUsuario() {

    }
}