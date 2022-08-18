/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package controller.dao;

import controller.baseDatos.Connexion;
import model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Creacion de la clase VideoDAOImplement
 */
public class VideoDAOImplement implements DAOVideo {
    /**
     * Atributos de la clase  VideoDAOImplement
     */
    private ArrayList<Video> videos;
    private boolean bandera;

    /**
     * Metodo constructor de la clase VideoDAOImplement
     */
    public VideoDAOImplement() {
        bandera = true;
        videos = new ArrayList<>();
    }

    /**
     * Metodo para obtener information del los nombres y códigos de los videos existentes
     *
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static String devolverInfo() throws SQLException {
        DAOVideo videoDAO = new VideoDAOImplement();
        StringBuilder reporte = new StringBuilder();
        for (Video video : videoDAO.getALL()) {
            reporte.append("Código: ").append(video.getVideoId()).append(", Nombre: ").append(video.getNombreVideo()).append("\n");
        }
        return reporte.toString();
    }

    /**
     * Metodo para obtener un video de la base de datos
     *
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @Override
    public Video get(int idVideo) throws SQLException {
        Video video = null;
        Connection daoConnection = Connexion.getConnection();
        //aqui se especifican el nombre de cada columna de la tablavideo de mi base de datos
        String sql = "SELECT id, nombre, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, duration FROM videotabla WHERE id = ?";
        PreparedStatement ps = daoConnection.prepareStatement(sql);
        ps.setInt(1, idVideo);//primer parametro
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {//verificamos si tengo un record
            //aqui obteniendo el contenido de las columnas de la base de datos del video encontrado
            int oid = rs.getInt("id");// guardando en oid el índice o conteo del video si lo encontró
            String nombreVideo = rs.getString("nombre");
            String categoria = rs.getString("categoria");

            String fechaString = rs.getString("fechaRegistro");
            DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaRegistro = LocalDate.parse(fechaString, JEFormatter);

            String descripcion = rs.getString("descripcion");
            boolean calificacion = Boolean.parseBoolean(rs.getString("calificacion"));
            String imagenPortada = rs.getString("imagenPortada").trim();
            String videoPath = rs.getString("videoPath").trim();
            float timer = rs.getFloat("duration");
            // video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid);
            video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid, timer);
        }
        return video;
    }

    /**
     * Metodo para listar todos los video que esten registrados en la base de datos
     *
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @Override
    public ArrayList<Video> getALL() throws SQLException {
        Connection connection = Connexion.getConnection();
        String sql = "SELECT * from videotabla";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            Video video;
            int oid = rs.getInt("id");// guardando en oid el índice o conteo del video si lo encontró
            String nombreVideo = rs.getString("nombre");
            String categoria = rs.getString("categoria");

            String fechaString = rs.getString("fechaRegistro");
            DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaRegistro = LocalDate.parse(fechaString, JEFormatter);

            String descripcion = rs.getString("descripcion");
            boolean calificacion = Boolean.parseBoolean(rs.getString("calificacion"));
            String imagenPortada = rs.getString("imagenPortada").trim();
            String videoPath = rs.getString("videoPath").trim();
            float time = rs.getFloat("duration");
            // video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid);
            video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid, time);
            videos.add(video);
        }
        //cerrando la connexion
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);

        return videos;
    }

    @Override
    public int save(Video video) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Video video) throws SQLException {

    }

    /**
     * Metodo para insertar un Video a la base de datos
     *
     * @param nombreVideo   parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param categoryVideo parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param description   parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param cover         parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param videoPath     parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param fecha         parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @param time          parametro del constructor de la clase Video, se utiliza para ser agregado a la base de datos
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @Override
    public int insert(String nombreVideo, String categoryVideo, String description, String cover, String videoPath, LocalDate fecha, float time) throws SQLException {
        Video video = new Video(nombreVideo, categoryVideo, fecha, description, cover, videoPath, time);

        Connection connection = Connexion.getConnection();
        String sql = "INSERT INTO videotabla (nombre, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, duration) VAlUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, video.getNombreVideo());
        ps.setString(2, video.getCategoryVideo());
        ps.setString(3, String.valueOf(video.getFecha()));
        ps.setString(4, video.getDescription());
        ps.setString(5, String.valueOf(video.isCalifica()));
        ps.setString(6, video.getCover());
        ps.setString(7, video.getVideoPath());
        ps.setFloat(8, video.getTotalDuration());
        int result = ps.executeUpdate();

        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;//retorna 1 si se agrego un nuevo record video
    }


    /**
     * Metodo que hacer actualizacion a un video por medio de su Id
     *
     * @param video Recibe un video como parametro
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    @Override
    public int update(Video video) throws SQLException {
        Connection connection = Connexion.getConnection();
        String sql = "UPDATE videotabla set nombre = ?, categoria = ?, fechaRegistro = ?, descripcion = ?, calificacion = ?, imagenPortada = ?, videoPath = ?, duration = ? where id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, video.getNombreVideo());
        ps.setString(2, video.getCategoryVideo());
        ps.setString(3, String.valueOf(video.getFecha()));
        ps.setString(4, video.getDescription());
        ps.setString(5, String.valueOf(video.isCalifica()));
        ps.setString(6, video.getCover());
        ps.setString(7, video.getVideoPath());
        ps.setFloat(8, video.getTotalDuration());
        ps.setInt(9, video.getVideoId());

        int result = ps.executeUpdate();
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;
    }

    /**
     * Metodo para borrar un video de la base de datos por medio de su Id
     *
     * @param video Recibe por parametro un video de la clase Video
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */

    @Override
    public int delete(Video video) throws SQLException {
        Connection connection = Connexion.getConnection();
        String sql = "DELETE FROM videotabla where id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        //Borrar
        ps.setInt(1, video.getVideoId());
        int result = ps.executeUpdate();
        //cerrando la connexion
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;
    }
}
