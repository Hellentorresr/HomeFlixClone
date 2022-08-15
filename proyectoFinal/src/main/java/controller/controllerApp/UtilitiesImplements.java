/**
 * @controller.controllerApp paquete que controla la interfaz grafica
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12/08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import controller.dao.DAOVideo;
import controller.dao.VideoDAOImplement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.InicioApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static controller.controllerApp.HomeController.video;

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

    public void addVideoDuration() throws SQLException {
        DAOVideo videoDAO = new VideoDAOImplement();
        float totalT = ReproductorVideoController.time;
        if(video.getTotalDuration()==0){
            video.setTotalDuration(totalT);
            videoDAO.update(video);
        }
    }

    /**
     * Metodos para notificar
     */
    public void mostrarMensajePositivo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Exitoso");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
