package controller;

import baseDatos.BaseDeDatos;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class PrincipalYSignIn {
    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_userPassword;
    @FXML
    private Label loginMessage;
    @FXML
    private Button button_signUp;

    public void login() throws SQLException{
        if (tf_userName.getText() == "" || tf_userPassword.getText() == "")
            loginMessage.setText("Datos incorrectos");
        else {
            validateLogin();
        }
    }

    public void validateLogin() throws SQLException {
        BaseDeDatos connectNow = new BaseDeDatos();
        Connection connectDB = connectNow.getConnection();
        String userName = tf_userName.getText();
        String userPass = tf_userPassword.getText();
        String verifyLogin = "select count(1) from usuarios where userName = " + "'" + userName + "'" + " and userPassword = " + "'" + userPass + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessage.setText("Ingresado con exito");
                } else {
                    loginMessage.setText("Datos incorrectos");
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            e.getCause();
        }
    }

    public void IrACrearCuenta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("SignUpForm.fxml")));
        Stage window = (Stage) button_signUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}