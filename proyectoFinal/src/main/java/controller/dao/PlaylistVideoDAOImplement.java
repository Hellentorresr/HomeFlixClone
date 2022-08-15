package controller.dao;

import controller.baseDatos.Connexion;
import model.PlaylistVideos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaylistVideoDAOImplement implements DAOPlayListVideos{
    @Override
    public PlaylistVideos get(int indiceConteo) throws SQLException {
        return null;
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
        PlaylistVideos playlistVideos1 = new PlaylistVideos(namePlaylist,totalPlayListDurationTime,tema,creationDate);
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
        return 0;
    }
}
