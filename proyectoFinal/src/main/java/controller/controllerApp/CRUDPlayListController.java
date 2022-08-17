package controller.controllerApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CRUDPlayListController {

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnTema;

    @FXML
    private TextField inputIdEditar;

    @FXML
    private TextField inputIdEliminar;

    @FXML
    private TableView<?> tabla;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTema;



}
