package view;

import controller.dao.DAOPlayListVideos;
import controller.dao.DAOVideo;
import controller.dao.PlaylistVideoDAOImplement;
import controller.dao.VideoDAOImplement;
import controller.server.ServerJava;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PlaylistVideos;
import model.Video;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InicioApp extends Application {

    /**
     * Function que ejecuta el código
     *
     * @param args cadena de caracteres
     */
    public static void main(String[] args) throws SQLException {
        launch();
        ServerJava serverJava = new ServerJava();
        //  serverJava.serverConnectorJava();

       /* DAOPlayListVideos v = new PlaylistVideoDAOImplement();
        PlaylistVideos f = v.get(2);
        v.delete(f);
        System.out.println(f);*/

        DAOPlayListVideos v = new PlaylistVideoDAOImplement();
        PlaylistVideos f;
        f = v.get(1);

        DAOVideo V = new VideoDAOImplement();
        Video video = V.get(11);
        f.agregarVideo(video);
        v.update(f);
        System.out.println(f);
    }

    /**
     * Function que permite dirigirse a la pantalla principal de signIn
     *
     * @param stage Recibe una plantilla para colocar la escena
     * @throws IOException Se genera una excepción si es escena no carga
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioApp.class.getResource("PrincipalYSignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HomeFlix!");
        stage.setScene(scene);
        stage.show();
    }
}