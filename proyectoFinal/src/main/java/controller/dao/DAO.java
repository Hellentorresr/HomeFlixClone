/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package controller.dao;

import java.sql.SQLException;

import java.util.List;

/**
 * Interfaz DAO GENERIC
 */
public interface DAO<T> {

    /**
     * @param indiceConteo retorna el id del video
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo insert para insertar un video a la base de datos
     */
    T get(int indiceConteo) throws SQLException;

    /**
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo geAll para obtener todos los videos de la base de datos
     */
    List<T> getALL() throws SQLException;

    /**
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo save
     */
    int save(T t) throws SQLException;

    /**
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo inset para agregar un video en la base de datos
     */
    void insert(T t) throws SQLException;
    /**
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo update para actualizar un video en la base de datos
     */
    int update(T t) throws SQLException;

    /**
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     * @metodo delete para borrar un video de la base de datos
     */
    int delete(T t) throws SQLException;
}

