/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package controller.dao;

import model.Video;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Interfaz que implementa la interfaz DAO generica
 */
public interface VideoDAO extends DAO<Video> {

    int insert(String nombreVideo, String categoryVideo, String description, String cover, String videoPath, LocalDate fecha) throws SQLException;
}