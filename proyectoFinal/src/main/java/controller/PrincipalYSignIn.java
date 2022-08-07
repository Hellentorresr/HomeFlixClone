package controller;

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
import view.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class PrincipalYSignIn {
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
    public PrincipalYSignIn(){
        UDI = new UsuarioDAOImplement();
    }

    public void login() throws SQLException, IOException {
        if (tf_userName.getText() == "" || tf_userPassword.getText() == "")
            loginMessage.setText("Datos incorrectos");
        else {
            validateLogin();
        }
    }

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
     */
    public void ingresarApp() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("DentroDeLaApp.fxml")));
        Stage window = (Stage) button_login.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void IrACrearCuenta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("SignUpForm.fxml")));
        Stage window = (Stage) button_signUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}