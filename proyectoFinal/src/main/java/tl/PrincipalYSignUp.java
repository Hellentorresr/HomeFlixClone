package tl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;
import java.util.Objects;

public class PrincipalYSignUp {
    /**
     * Atributos clase PrincipalYSignUp
     */
    @FXML
    private Button button_login;
    @FXML
    private Button button_signUp;
    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_userPassword;


    /**
     * Creacion metodo IrACreaCuenta
     */
    public void IrACrearCuenta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("SignUpForm.fxml")));
        Stage window = (Stage)button_signUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
