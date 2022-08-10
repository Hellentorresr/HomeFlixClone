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
public interface DAOVideo extends DAO<Video> {

    /**
     *
     * @param nombreVideo Parametro de la clase Video, se utiliza para su registro
     * @param categoryVideo Parametro de la clase Video, se utiliza para su registro
     * @param description Parametro de la clase Video, se utiliza para su registro
     * @param cover Parametro de la clase Video, se utiliza para su registro
     * @param videoPath Parametro de la clase Video, se utiliza para su registro
     * @param fecha Parametro de la clase Video, se utiliza para su registro
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo insert para insertar un video a la base de datos
     */
    int insert(String nombreVideo, String categoryVideo, String description, String cover, String videoPath, LocalDate fecha) throws SQLException;
}