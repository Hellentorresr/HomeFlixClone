package controller;

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
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class DentroDeLaAppController implements Initializable {
    @FXML
    public Button btnCerrar;
    @FXML
    public HBox recentlyPlayedContainer;
    List<Video> recientesPlayed;
    @FXML
    private HBox favoritasContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Video> recientesPlayed;
        recientesPlayed = new ArrayList<>(Administracion.videos);

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

            Button button1 = new Button(recientesPlayed.get(i).getNombreVideo());

            button1.setTextFill(Paint.valueOf("Red"));
            button1.setCursor(Cursor.cursor("hand"));

            button1.setOnAction(event -> {
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("ReproductorVideo.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage window = (Stage) button1.getScene().getWindow();
                window.setScene(new Scene(root));
            });
            button1.setText("Reproducir");

            vBox.getChildren().add(button1);

            recentlyPlayedContainer.getChildren().add(vBox);
        }

    }


    public void handleBtnIngresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PrincipalYSignIn.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }



    //--Methods---
    public void handleBtnIngresar() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PrincipalYSignInForm.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}