package tl;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class SignUpController {
    /**
     * Atributos
     */
    public ImageView imageView;
    private String image;
    public Button btnSignUp;
    public Button btnCleanSignUp;
    public Button btnOpenFile;
    public Button regresar;

    //registro
    public TextField txtNombreSignUp;
    public TextField txtApellidoSignUp;
    public TextField txtIdSignUp;
    public TextField txtNombreUsuarioSignUp;
    public PasswordField tf_password;
    public PasswordField tf_passwordConfirm;


    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = file.toString();
            imageView.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("Archivo no encontrado");
        }
    }
}
