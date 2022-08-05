package controller.dao;

import model.Video;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Interfaz que implementa la interfaz DAO generica
 */
public interface VideoDAO extends DAO<Video> {

    int insert(String nombreVideo, String categoryVideo, LocalDate fecha, String description, boolean califica, String cover, String videoPath) throws SQLException;
}