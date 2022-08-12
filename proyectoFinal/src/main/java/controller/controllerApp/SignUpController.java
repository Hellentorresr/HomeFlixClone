package controller.controllerApp;

import controller.dao.UsuarioDAOImplement;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Usuario;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {
    UtilitiesImplements utilitiesImplements;
    UsuarioDAOImplement UDI;
    Usuario usuario;
    /**
     * Atributos
     */
    public ImageView imageView;
    private String image;
    public Button btnSignUp;
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

    /**
     * Constructor de la clase
     */
    public SignUpController(){
        usuario = new Usuario("","","","",0,"");
        UDI = new UsuarioDAOImplement();
        labelRegister = new Label();
        usuario = new Usuario();
        utilitiesImplements = new UtilitiesImplements();

    }

    /**
     * Function que registra un usuario
     * @throws SQLException dara un exception si no se conecta correctamente a la base de datos
     */
    public void registering() throws SQLException {
        usuario.setNombre(txtNombreSignUp.getText());
        String fName = usuario.getNombre();

        usuario.setApellido1(txtApellidoSignUp.getText());
        String lName1 = usuario.getApellido1();

        usuario.setUserName(txtNombreUsuarioSignUp.getText());
        String nickName = usuario.getUserName();

        usuario.setUserPassword(tf_password.getText());
        String password = usuario.getUserPassword();

        if( txtIdSignUp.getText()==""){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Incompleto");
            alert.setContentText("Introduzca su identificación");
            alert.showAndWait();
        } else{
            usuario.setUserId(Integer.parseInt(txtIdSignUp.getText()));
        }
        int id = usuario.getUserId();
        boolean verificandoId = UDI.verificarId(id);

        if(fName.isEmpty() || lName1.isEmpty() || nickName.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Rellene los espacios en blanco");
            alert.showAndWait();
        } else if (!confirmPassword()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Las contraseñas son distintas");
            alert.showAndWait();
        }else if(txtIdSignUp == null || txtIdSignUp.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ingrese su identificación");
            alert.showAndWait();
        } else if (verificandoId == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La identificacion ya fue registrada");
            alert.showAndWait();
        } else{

            if(validarContrasena(password)) {

            }else{
                labelRegister.setText("Usuario Registrado!");
                usuario = new Usuario(fName,lName1,nickName,password,id,img);
                UDI.insert(usuario);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Completado");
                alert.setContentText("El usuario ha sido registrado");
                alert.showAndWait();
            }

        }

    }

    /**
     * Funcion que permite validar la contraseña a la hora de registrarla
     * @param password recibe la contraseña a validar
     * @return devuelve false o true dependiendo del resultado
     */
    public Boolean validarContrasena(String password){
        Boolean error = false;
        char especiales[] =  {'!','@','#','$','%','^','&','*','(',')','-','_','=','+','{','}',':',';','"',',','.','<','>','?','/'};
        int isUpper = 0;
        int isLower = 0;
        int isDigit = 0;
        int isSpecial = 0;
        if(password.length()>8 || password.length()<6){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Incorrecto");
            alert.setContentText("La contraseña debe contener de 6 a 8 caracteres");
            alert.showAndWait();
            error = true;
        }
        for (int i = 0; i < password.length(); i++) {
            char letra = password.charAt(i);
            if(Character.isUpperCase(letra)){
                isUpper++;
            } else if (Character.isLowerCase(letra)) {
                isLower++;
            } else if (Character.isDigit(letra)) {
                isDigit++;
            }
            for (int j = 0; j < 25; j++) {
                if(letra == especiales[j]){
                    isSpecial++;
                }
            }

        }
        if(!(isUpper>0&&isLower>0&&isDigit>0&&isSpecial>0)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Incorrecto");
            alert.setContentText("La contraseña debe contener almenos un caracter especial, un número, una letra minúscula y una mayúscula");
            alert.showAndWait();
            error = true;
        }
        return error;
    }

    /**
     * Funcion que valida si las contraseñas son validas
     * @return retorna true o false
     */

    public Boolean confirmPassword(){
        if(tf_password.getText().equals(tf_passwordConfirm.getText())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Funcion que busca una imagen y su URL
     * @return devuelve un String URL de la imagen
     */
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

    /**
     *Function que redirige a la pantalla de signIn
     * @throws IOException dara un exception si no se conecta correctamente a la base de datos
     */
    public void regresarPrincipal() throws IOException {
        utilitiesImplements.pathInterfazGrafica("PrincipalYSignIn.fxml",regresar);
    }
}
