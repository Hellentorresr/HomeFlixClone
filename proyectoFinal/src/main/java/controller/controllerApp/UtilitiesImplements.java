/**
 * @controller.controllerApp paquete que controla la interfaz grafica
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12/08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.InicioApp;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase UtilitiesImplements, para implementar los metodos abstractos de la clase UtilitiesAbstract
 */
public class UtilitiesImplements extends UtilitiesAbstract {
    @Override
    public int validarNumero(int numero) {
        return 0;
    }

    @Override
    public String pathInterfazGrafica(String path) throws IOException {
        return null;
    }

    /**
     * Metodo pathInterfazGrafica
     * @param path recibe un String por parametro que será la el path de la interfaz fxml
     * @param button recibe un Button por parametro que será la el boton que ejecutara este metodo
     * @throws IOException generará un error si no encuentra el path de la interfaz
     */
    @Override
    public String pathInterfazGrafica(String path, Button button) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource(path)));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
        return path;
    }
}
