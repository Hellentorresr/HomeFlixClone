package view;

import controller.server.ServerJava;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioApp extends Application {

    /**
     * Function que ejecuta el código
     *
     * @param args cadena de caracteres
     */
    public static void main(String[] args) {
        launch();
        ServerJava serverJava = new ServerJava();
        //  serverJava.serverConnectorJava();
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