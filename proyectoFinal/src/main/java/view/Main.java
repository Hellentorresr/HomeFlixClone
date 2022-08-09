package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /**
     * Funcion que permite dirigirse a la pantalla principal de signIn
     * @param stage Recibe una plantilla para colocar la escena
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PrincipalYSignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HomeFlix!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funcion que ejecuta el codigo
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}