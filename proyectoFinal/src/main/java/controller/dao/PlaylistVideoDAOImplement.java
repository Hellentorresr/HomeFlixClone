package controller.dao;

import controller.baseDatos.Connexion;
import model.PlaylistVideos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaylistVideoDAOImplement implements DAOPlayListVideos {
    @Override
    public PlaylistVideos get(int indiceConteo) throws SQLException {
        PlaylistVideos playlistVideos = null;
        Connection daoConnection = Connexion.getConnection();
        //aqui se especifican el nombre de cada columna de la tablavideo de mi base de datos
        String sql = "SELECT id, namePlaylist, totalPlayListDurationTime, tema, creationDate, videosList FROM playlisttable WHERE id = ?";
        PreparedStatement ps = daoConnection.prepareStatement(sql);
        ps.setInt(1, indiceConteo);//primer parametro
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {//verificamos si tengo un record
            //aqui obteniendo el contenido de las columnas de la base de datos del video encontrado
            int oid = rs.getInt("id");// guardando en oid el índice o conteo del video si lo encontró
            String nombreVideo = rs.getString("namePlaylist");
            float duration = rs.getFloat("totalPlayListDurationTime");
            String tema = rs.getString("tema");

            String fechaString = rs.getString("creationDate");
            DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate creationDate = LocalDate.parse(fechaString, JEFormatter);
            int videosList = rs.getInt("videosList");
            ArrayList<Integer> videos = new ArrayList<>();
            videos.add(videosList);

            playlistVideos = new PlaylistVideos(nombreVideo, duration, tema, creationDate, oid, videos);
        }
        return playlistVideos;

    }

    @Override
    public List<PlaylistVideos> getALL() throws SQLException {
        return null;
    }

    @Override
    public int save(PlaylistVideos playlistVideos) throws SQLException {
        return 0;
    }

    @Override
    public void insert(PlaylistVideos playlistVideos) throws SQLException {

    }

    @Override
    public void insert(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate) throws SQLException {
        PlaylistVideos playlistVideos1 = new PlaylistVideos(namePlaylist, totalPlayListDurationTime, tema, creationDate);
        Connection connection = Connexion.getConnection();
        String sql = "INSERT INTO playlisttable (namePlaylist, totalPlayListDurationTime, tema, creationDate) VAlUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, playlistVideos1.getNamePlaylist());
        ps.setFloat(2, playlistVideos1.getTotalPlayListDurationTime());
        ps.setString(3, playlistVideos1.getTema());
        ps.setString(4, String.valueOf(playlistVideos1.getCreationDate()));
        ps.executeUpdate();
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
    }

    @Override
    public int update(PlaylistVideos playlistVideos) throws SQLException {
        return 0;
    }

    @Override
    public int delete(PlaylistVideos playlistVideos) throws SQLException {
        Connection connection = Connexion.getConnection();

        String sql = "DELETE FROM playlisttable WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, playlistVideos.getCode());
        int result = ps.executeUpdate();

        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;
    }
}
