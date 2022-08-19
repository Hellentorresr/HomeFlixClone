/**
 * @controller.controllerApp paquete que controla la interfaz grafica
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 17/08/2022
 * @por Hellen torres
 */
package controller.controllerApp;

import controller.dao.DAOPlayListVideos;
import controller.dao.DAOVideo;
import controller.dao.PlaylistVideoDAOImplement;
import controller.dao.VideoDAOImplement;
import javafx.fxml.FXMLLoader;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.PlaylistVideos;
import model.Video;
import view.InicioApp;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;



/**
 * Clase UtilitiesImplements, para implementar los metodos abstractos de la clase UtilitiesAbstract
 */
public class UtilitiesImplements extends UtilitiesAbstract {
    //
    public static ArrayList<Video> videosBaseDatos = new ArrayList<>();
    public static ArrayList<PlaylistVideos> playlistVideos = new ArrayList<>();
    public static Video video = new Video();

    private DAOVideo videoDAO;
    private DAOPlayListVideos daoPlayListVideos;


    public UtilitiesImplements() {
        this.videoDAO = new VideoDAOImplement();
        this.daoPlayListVideos = new PlaylistVideoDAOImplement();
    }

    @Override
    public int validarNumero(int numero) {
        return 0;
    }

    @Override
    public String pathInterfazGrafica(String path) throws IOException {
        return null;
    }

    /**
     * Metodo pathInterfazGrafica
     *
     * @param path   recibe un String por parametro que será la el path de la interfaz fxml
     * @param button recibe un Button por parametro que será la el boton que ejecutara este metodo
     * @throws IOException generará un error si no encuentra el path de la interfaz
     */
    @Override
    public String pathInterfazGrafica(String path, Button button) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(InicioApp.class.getResource(path)));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
        return path;
    }

    public void addVideoDuration() throws SQLException {
        float totalT = ReproductorVideoController.time;
        if (video.getTotalDuration() == 0) {
            video.setTotalDuration(totalT);
            videoDAO.update(video);
        }
    }

    /**
     * Metodos para notificar
     */
    public void mostrarMensajePositivo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Exitoso");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarMensajeNegativo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public ArrayList<Video> recentAdd() throws SQLException {
        ArrayList<Video> todayVideos = new ArrayList<>();
        ArrayList<Video> v = new ArrayList<>(videoDAO.getALL());
        Video video1;
        LocalDate today = LocalDate.now();

        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getFecha().equals(today)) {
                video1 = v.get(i);
                todayVideos.add(video1);
            }
        }
        return todayVideos;
    }

    /**
     * Metodo para obtener todas las listas de reproduccion registradas
     *
     * @return retorna un arraylist con listas de reproduccion registradas
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    public ArrayList<PlaylistVideos> allPlaylist() throws SQLException {
        ArrayList<PlaylistVideos> playlistVideos = new ArrayList<>();
        ArrayList<PlaylistVideos> play = new ArrayList<>(daoPlayListVideos.getALL());
        PlaylistVideos play1;
        for (int i = 0; i < play.size(); i++) {
            play1 = play.get(i);
            playlistVideos.add(play1);
        }
        return playlistVideos;
    }

    /**
     * Metodo para verificar si una lista de reproduccion existe
     *
     * @param id recibe por parametro un entero para hacer la busqueda
     * @return retorna true si encuentra una lista con ese, id o false si no existe
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    public boolean verificarSiExistePlayList(int id, Video v) throws SQLException {
        PlaylistVideos f;
        f = daoPlayListVideos.get(id);
        if (allPlaylist().contains(f)) {
            f.agregarVideo(v);
            System.out.println(f);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo registrarPlayList
     * @param idList recibe por parametro un int id
     * @param name recibe por parametro un string nombre
     * @param tema recibe por parametro un string tema
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    public void registrarPlaylist(int idList, String name, String tema) throws SQLException {
      float  duration = 0;
        int idVideo = 0;
        LocalDate dateNow = LocalDate.now();

        PlaylistVideos nuevaLista;
        nuevaLista = daoPlayListVideos.get(idList);
        if (allPlaylist().contains(nuevaLista)) {
            mostrarMensajeNegativo("Id de lista ya existe, favor intentar de nuevo!");
        } else {
            daoPlayListVideos.insert(idList, name, duration, tema, dateNow, idVideo);
            mostrarMensajePositivo("Lista de creada correctamente");
        }
    }


    /**
     * metodo verificarPlay
     *
     * @param id     recibe un, id por parametro
     * @param nombre recibe un nombre por parametro
     * @param tema   recibe un tema por parametro
     * @return retorna un boolean
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    public boolean verificarPlay(int id, String nombre, String tema) throws SQLException {
        PlaylistVideos f;
        f = daoPlayListVideos.get(id);
        if (allPlaylist().contains(f)) {
            if (!nombre.isEmpty()) {
                f.setNamePlaylist(nombre);
                mostrarMensajePositivo("Nombre de la lista actualizado");
            }
            if (!tema.isEmpty()) {
                f.setTema(tema);
                mostrarMensajePositivo("Tema de la lista actualizado");
            }
            daoPlayListVideos.update(f);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo verificarPlayEliminar
     *
     * @param id recibe un, id por parametro
     * @return retorna true si encuentra la lista con este, id, o false si no la encuentra
     * @throws SQLException genera una exception si no hay communication con la bae de datos
     */
    public boolean verificarPlayEliminar(int id) throws SQLException {
        PlaylistVideos f;
        f = daoPlayListVideos.get(id);
        if (allPlaylist().contains(f)) {
            daoPlayListVideos.delete(f);
            return true;
        } else {
            return false;
        }
    }

    //para la playlist
    public void cargarDatosDeLasListas(ArrayList<PlaylistVideos> playlistVideos , VBox vBox) {
        for (int i = 0; i < playlistVideos.size(); i++) {
            //para lo que va afuera del hbox
            Label titulo = new Label();
            titulo.setText("Playlist");
            titulo.setFont(Font.font(18));

            HBox hBox = new HBox(i);
            ScrollPane scrollPane = new ScrollPane(hBox);
            scrollPane.prefWidth(1059);
            scrollPane.prefHeight(229);

            scrollPane.setContent(hBox);
            scrollPane.setBackground(Background.fill(Paint.valueOf("transparent")));

            Button button = new Button(playlistVideos.get(i).getNamePlaylist());
            button.setTextFill(Paint.valueOf("white"));
            button.setCursor(Cursor.cursor("hand"));
            button.setUnderline(true);
            button.setBackground(Background.fill(Paint.valueOf("transparent")));
            button.setFont(Font.font(14));
            button.setText("Reproducir lista");

            button.setOnAction(event -> {
                try {
                    pathInterfazGrafica("AddPlayList.fxml", button);//cambiar a la correcta interfaz
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });


            Label nombre = new Label();
            nombre.setText(playlistVideos.get(i).getNamePlaylist());
            hBox.getChildren().add(nombre);
            nombre.setFont(Font.font(16));
            nombre.setTextFill(Paint.valueOf("#fff"));

            hBox.getChildren().add(button);

            vBox.getChildren().add(scrollPane);
            HomeController homeController = new HomeController();
           homeController.cargarDatos(playlistVideos.get(i).getVideos(), hBox);
        }
    }
}

