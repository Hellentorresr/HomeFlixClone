package controller;

import controller.dao.UsuarioDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Usuario;
import view.Main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SignUpController {
    UsuarioDAOImplement UDI;
    Usuario usuario;
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
    public Label labelRegister;
    private String img;

    public SignUpController(){
        usuario = new Usuario("","","","",0,"");
        UDI = new UsuarioDAOImplement();
        labelRegister = new Label();
    }

    public void registering() throws SQLException, ClassNotFoundException {
        usuario.setNombre(txtNombreSignUp.getText());
        String fName = usuario.getNombre();

        usuario.setApellido1(txtApellidoSignUp.getText());
        String lName1 = usuario.getApellido1();

        usuario.setUserName(txtNombreUsuarioSignUp.getText());
        String nickName = usuario.getUserName();

        usuario.setUserPassword(tf_password.getText());
        String password = usuario.getUserPassword();

        usuario.setUserId(Integer.parseInt(txtIdSignUp.getText()));
        int id = usuario.getUserId();

        if(fName.isEmpty() || lName1.isEmpty() || nickName.isEmpty() || password.isEmpty()){
            labelRegister.setText("Rellene los campos vacios");
        } else if (!confirmPassword()) {
            labelRegister.setText("Las contraseñas son diferentes");
        }else{
            labelRegister.setText("Usuario Registrado!");
            UDI.insertarUsuario(fName,lName1,nickName,password,id,img);
        }

    }

    public Boolean confirmPassword(){
        if(tf_password.getText().equals(tf_passwordConfirm.getText())){
            return true;
        }else{
            return false;
        }
    }

    public String handleBtnOpenFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = file.toString();
            imageView.setImage(new Image(file.toURI().toString()));
            img = image;
            return img;
        } else {
            System.out.println("Archivo no encontrado");
            return null;
        }
    }

    public void regresarPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PrincipalYSignIn.fxml")));
        Stage window = (Stage)regresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
