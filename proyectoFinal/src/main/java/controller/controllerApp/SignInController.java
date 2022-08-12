package controller.controllerApp;

import controller.dao.UsuarioDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.InicioApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class SignInController {
    UsuarioDAOImplement UDI;

    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_userPassword;
    @FXML
    private Label loginMessage;
    @FXML
    private Button button_signUp;
    @FXML
    private Button button_login;
    public static String userName;
    public static String userPassword;

    /**
     * Funcion que crea el constructor de la clase
     */
    public SignInController(){
        UDI = new UsuarioDAOImplement();
    }

    /**
     * Function que valida la entrada de datos en los campos de username y password
     * @throws SQLException dara un exception si no se conecta correctamente a la base de datos
     * @throws IOException dara un exception si no se conecta correctamente a la base de datos
     */
    public void login() throws SQLException, IOException {
        if (tf_userName.getText() == "" || tf_userPassword.getText() == "")
            loginMessage.setText("Datos incorrectos");
        else {
            validateLogin();
        }
    }

    /**
     * Function que válida si el username y el password hacen match en la base de datos
     * @throws SQLException dara un exception si no se conecta correctamente a la base de datos
     * @throws IOException dara un exception si no se conecta correctamente a la base de datos
     */
    public void validateLogin() throws SQLException, IOException {
        userName = tf_userName.getText();
        userPassword = tf_userPassword.getText();
        Boolean verificandoUsuario = UDI.verificarUsuario(userName,userPassword);
        if(verificandoUsuario == true){
            loginMessage.setText("Ingresado con exito");
            ingresarApp();
        }else{
            loginMessage.setText("Datos incorrectos");
        }

    }

    /**
     * Para enviar al usuario a la página principal de videos
     * @throws IOException dara un exception si no se conecta correctamente a la base de datos
     */
    public void ingresarApp() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) button_login.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Función que envia a la pantalla de signUp
     * @throws IOException Dara un error si no encuentra la interfaz
     */

    public void IrACrearCuenta() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource("SignUpForm.fxml")));
        Stage window = (Stage) button_signUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}