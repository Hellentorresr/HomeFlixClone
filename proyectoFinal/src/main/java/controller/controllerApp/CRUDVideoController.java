/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */

package controller.controllerApp;

import controller.dao.DAOVideo;
import controller.dao.VideoDAOImplement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Video;
import view.Main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Clase EditarEliminarVideoController
 */
public class CRUDVideoController {
    /**
     * Atributos clase EditarEliminarVideoController
     */
    public Button btnRegresar;
    @FXML
    private Button btnConver;
    @FXML
    private Button btnEliminarVideo;
    @FXML
    private Button btnEvitar;
    @FXML
    private Button btnMostrarReporte;
    @FXML
    private Button btnPathVideo;
    @FXML
    private ImageView imageViewCover;
    @FXML
    private TextArea textAreaReporte;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtCodigoParaEditar;
    @FXML
    private TextField txtCodigoParaEliminar;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNombreVideo;
    private String image;

    /**
     * Metodo contractor de la clase EditarEliminarVideoController
     */
    public CRUDVideoController() {
        image = "";
    }

    /**
     * Metodo para editar un video
     */
    @FXML
    void btnEditarEvent() throws SQLException {
        String nombre = this.txtNombreVideo.getText();
        String cate = this.txtCategoria.getText();
        String desc = this.txtDescription.getText();

        if (this.txtCodigoParaEditar.getText().isEmpty()) {
            mostrarMensaje("Favor ingresar un código para hacer los cambio");
        } else if (!this.txtCodigoParaEditar.getText().isEmpty()) {
            //primero lo obtengo
            DAOVideo videoDAO = new VideoDAOImplement();
            int codi = Integer.parseInt(this.txtCodigoParaEditar.getText());
            Video video = videoDAO.get(codi);

            if (videoDAO.getALL().contains(video)) {

                if (!nombre.isEmpty()) {
                    video.setNombreVideo(nombre);
                    mostrarMensaje("Nombre del video cambiado correctamente");
                }
                if (!cate.isEmpty()) {
                    video.setCategoryVideo(cate);
                    mostrarMensaje("Categoría del video cambiado correctamente");
                }
                if (!desc.isEmpty()) {
                    video.setDescription(desc);
                    mostrarMensaje("Descripción del video cambiado correctamente");
                }
                if (!image.isEmpty()) {
                    video.setCover(image);
                    mostrarMensaje("Portada del video correctamente");
                }

                videoDAO.update(video);
                mostrarMensaje("Video editado correctamente");

            } else {
                mostrarMensajeNegativo("código ingresado no existe");
            }
        }
    }


    /**
     * Metodo para mostrar information en el contenedor textArea
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @FXML
    void btnMostrarReporteEvent() throws SQLException {
        textAreaReporte.setText(VideoDAOImplement.devolverInfo());
        textAreaReporte.setFont(Font.font(18));
    }


    /**
     * Metodo para mostrar mensaje
     * @param string recibe un String
     */
    private void mostrarMensaje(String string) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Completado");
        alert.setContentText(string);
        alert.showAndWait();
    }

    /**
     * Metodo para mostrar mensaje
     * @param mensaje recibe un String
     */

    private void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo para adjuntar imagen
     */
    @FXML
    void handleBtnOpenFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = file.toString();
            imageViewCover.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("Archivo no encontrado");
        }
    }


    /**
     * Metodo para regresar a la página principal
     */
    public void regresarAPrincipal() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     *Metodo para eliminar un video registrado
     */
    public void eliminarVideoMetodo() throws SQLException {
        if (this.txtCodigoParaEliminar.getText().isEmpty()) {
            mostrarMensajeNegativo("Favor ingrese un codigo para hacer la eliminacion");
        } else if (!this.txtCodigoParaEliminar.getText().isEmpty()) {
            //primero lo obtengo
            DAOVideo videoDAO = new VideoDAOImplement();
            Video video;
            int codi = Integer.parseInt(this.txtCodigoParaEliminar.getText());
            video = videoDAO.get(codi);
            if (videoDAO.getALL().contains(video)) {
                videoDAO.delete(video);
                mostrarMensaje("Video borrado correctamente");

            } else {
                mostrarMensajeNegativo("Código no encontrado");
            }
        }
    }
}
