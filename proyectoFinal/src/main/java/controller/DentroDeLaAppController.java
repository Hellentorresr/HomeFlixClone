package controller;

import controller.dao.UsuarioDAOImplement;
import controller.dao.VideoDAO;
import controller.dao.VideoDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import view.Main;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DentroDeLaAppController implements Initializable {
    VideoDAO videoDAO;
    //listas
    public static List<Video> video = new ArrayList<>();
    public static String test;
    public static Button button2 = new Button();
    //
    public static ArrayList<Video> videosBaseDatos = new ArrayList<>();

    private static String imagen;
    //para la foto y nombre del usuario que ingreso al sistema;
    @FXML
    public ImageView fotoPerfil;
    @FXML
    public Button btnCerrar;
    @FXML
    public HBox recentlyPlayedContainer;
    @FXML
    public Button agregarVideo;
    public Button eliminarEditarVideo;
    public Label nombreDeUsuario;
    //Para la búsqueda de un video
    public TextField buscarPlaceholder;
    public Button btnBuscar;
    public ImageView portadaEncontrada;
    public Label videoEncontrado;
    //
    UsuarioDAOImplement UDI;
    ArrayList<Video> favoritas;
    @FXML
    private HBox favoritasContainer;

    public DentroDeLaAppController() {
        videoDAO = new VideoDAOImplement();
        UDI = new UsuarioDAOImplement();
        videoEncontrado = new Label();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
    }

    public void getData(){
        try {
            videosBaseDatos = new ArrayList<>(videoDAO.getALL());
            nombreDeUsuario.setText(UDI.get(UDI.getUserId()).getUserName());
            String img = UDI.get(UDI.getUserId()).getImg();
            fotoPerfil.setImage(new Image("file:///" + UDI.get(UDI.getUserId()).getImg()));
            System.out.println("probando img" + img);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        video = new ArrayList<>(videosBaseDatos);

        for (int i = 0; i < video.size(); i++) {
            ImageView img = new ImageView();
            img.setFitWidth(200);
            img.setFitHeight(200);
            img.setImage(new Image("file:///" + video.get(i).getCover()));
            VBox vBox = new VBox(img);

            Label nombre = new Label();
            nombre.setText(video.get(i).getNombreVideo());
            vBox.getChildren().add(nombre);
            nombre.setFont(Font.font(16));
            nombre.setTextFill(Paint.valueOf("#fff"));


            button2 = new Button(video.get(i).getVideoPath());
            button2.setTextFill(Paint.valueOf("Red"));
            button2.setCursor(Cursor.cursor("hand"));

            int finalI = i;
            button2.setOnAction(event -> {
                test = video.get(finalI).getVideoPath();
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



    public void eliminarEditarVideo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("EditarEliminarVideo.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //se agrega esta funcion para ser usada como tes
    @FXML
    public Video buscarVideo() {
        String busqueda = this.buscarPlaceholder.getText();
        Video video = new Video();
        recentlyPlayedContainer.getChildren().clear();
        if (busqueda.isEmpty()) {
            mostrarMensajeNegativo("Favor ingrese el nombre del video que desea encontrar");
            getData();
        } else{

            try {
                ArrayList<Video> videos = new ArrayList<>(videoDAO.getALL());
                for (int i = 0; i<videos.size();i++) {
                    if (videos.get(i).getNombreVideo().equals(busqueda) || videos.get(i).getCategoryVideo().equals(busqueda)) {
                        video = videos.get(i);
                        videos = new ArrayList<>();
                        videos.add(video);
                        System.out.println("video encontrado " + video);

                        ImageView img = new ImageView();
                        img.setFitWidth(200);
                        img.setFitHeight(200);
                        img.setImage(new Image("file:///" + video.getCover()));
                        VBox vBox = new VBox(img);

                        Label nombre = new Label();
                        nombre.setText(video.getNombreVideo());
                        vBox.getChildren().add(nombre);
                        nombre.setFont(Font.font(16));
                        nombre.setTextFill(Paint.valueOf("#fff"));


                        button2 = new Button(video.getVideoPath());
                        button2.setTextFill(Paint.valueOf("Red"));
                        button2.setCursor(Cursor.cursor("hand"));


                        button2.setOnAction(event -> {
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

                        //mostrarBusqueda();
                        break;
                    }else{
                        mostrarMensajeNegativo("El video no fue encontrado");
                        getData();
                        break;
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return video;
    }

    public void mostrarBusqueda() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("videoEncontrado.fxml")));
        Stage window = (Stage) btnBuscar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void mostrarMensaje(String busqueda) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Completado");
        alert.setContentText(busqueda);
        alert.showAndWait();
    }

    private void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
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
}