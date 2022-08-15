package controller.dao;

import model.PlaylistVideos;

import java.sql.SQLException;
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
    public int update(PlaylistVideos playlistVideos) throws SQLException {
        return 0;
    }

    @Override
    public int delete(PlaylistVideos playlistVideos) throws SQLException {
        return 0;
    }
}
