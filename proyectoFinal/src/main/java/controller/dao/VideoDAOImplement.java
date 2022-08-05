package controller.dao;

import baseDatos.BaseDeDatos;
import model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VideoDAOImplement implements VideoDAO {

    public static String devolverInfo() throws SQLException {
        VideoDAO videoDAO = new VideoDAOImplement();
        StringBuilder reporte = new StringBuilder();
        for (Video video : videoDAO.getALL()) {
            reporte.append("Código: ").append(video.getVideoId()).append(", Nombre: ").append(video.getNombreVideo()).append("\n");
        }
        return reporte.toString();
    }

    /**
     * Metodo para obtener un video de la base de datos
     */
    @Override
    public Video get(int idVideo) throws SQLException {
        Video video = null;
        Connection daoConnection = BaseDeDatos.getConnection();
        //aqui se especifican el nombre de cada columna de la tablavideo de mi base de datos
        String sql = "SELECT id, nombre, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath FROM videotabla WHERE id = ?";
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
            video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid);
        }
        return video;
    }

    @Override
    public ArrayList<Video> getALL() throws SQLException {
        Connection connection = BaseDeDatos.getConnection();

        ArrayList<Video> videos = new ArrayList<>();

        String sql = "SELECT * from videotabla";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Video video = new Video();
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
            video = new Video(nombreVideo, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath, oid);
            videos.add(video);
        }
        //cerrando la connexion
        BaseDeDatos.closePreparedStatement(ps);
        BaseDeDatos.closeConnection(connection);

        return videos;
    }

    @Override
    public int save(Video video) throws SQLException {
        return 0;
    }


    @Override
    public int insert(Video video) throws SQLException {
        return 0;
    }

    @Override
    public int insert(String nombreVideo, String categoryVideo, LocalDate fecha, String description, boolean califica, String cover, String videoPath) throws SQLException {
        Video video = new Video(nombreVideo, categoryVideo, fecha, description, califica, cover, videoPath);
        Connection connection = BaseDeDatos.getConnection();
        String sql = "INSERT INTO videotabla (nombre, categoria, fechaRegistro, descripcion, calificacion, imagenPortada, videoPath) VAlUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, video.getNombreVideo());
        ps.setString(2, video.getCategoryVideo());
        ps.setString(3, String.valueOf(video.getFecha()));
        ps.setString(4, video.getDescription());
        ps.setString(5, String.valueOf(video.isCalifica()));
        ps.setString(6, video.getCover());
        ps.setString(7, video.getVideoPath());
        int result = ps.executeUpdate();

        BaseDeDatos.closePreparedStatement(ps);
        BaseDeDatos.closeConnection(connection);
        return result;//retorna 1 si se agrego un nuevo record video
    }

    @Override
    public int update(Video video) throws SQLException {
        Connection connection = BaseDeDatos.getConnection();
        String sql = "UPDATE videotabla set nombre = ?, categoria = ?, fechaRegistro = ?, descripcion = ?, calificacion = ?, imagenPortada = ?, videoPath = ? where id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, video.getNombreVideo());
        ps.setString(2, video.getCategoryVideo());
        ps.setString(3, String.valueOf(video.getFecha()));
        ps.setString(4, video.getDescription());
        ps.setString(5, String.valueOf(video.isCalifica()));
        ps.setString(6, video.getCover());
        ps.setString(7, video.getVideoPath());
        ps.setInt(8, video.getVideoId());

        int result = ps.executeUpdate();
        BaseDeDatos.closePreparedStatement(ps);
        BaseDeDatos.closeConnection(connection);

        return result;
    }

    @Override
    public int delete(Video video) throws SQLException {
        Connection connection = BaseDeDatos.getConnection();
        String sql = "DELETE FROM videotabla where id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        //Borrar
        ps.setInt(1, video.getVideoId());
        int result = ps.executeUpdate();
        //cerrando la connexion
        BaseDeDatos.closePreparedStatement(ps);
        BaseDeDatos.closeConnection(connection);
        return result;
    }
}
