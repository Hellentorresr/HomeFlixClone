package tl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class DentroDeLaAppController implements Initializable {
    @FXML
    public Button btnCerrar;
    @FXML
    public HBox recentlyPlayedContainer;
    
    @FXML
    private HBox favoritasContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void handleBtnIngresar(ActionEvent event) {

    }



    //--Methods---
    public void handleBtnIngresar() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PrincipalYSignInForm.fxml")));
        Stage window = (Stage) btnCerrar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}