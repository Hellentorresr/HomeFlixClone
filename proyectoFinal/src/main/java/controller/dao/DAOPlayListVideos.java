package controller.dao;

import model.PlaylistVideos;

import java.sql.SQLException;
import java.time.LocalDate;

public interface DAOPlayListVideos extends DAO<PlaylistVideos> {
    void insert(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate) throws SQLException;

    void insert(int id, String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int idVideo) throws SQLException;
}
