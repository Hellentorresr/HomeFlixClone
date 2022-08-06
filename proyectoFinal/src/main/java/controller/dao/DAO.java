package controller.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz DAO GENERIC
 */
public interface DAO<T> {

    T get(int indiceConteo) throws SQLException;

    List<T> getALL() throws SQLException;

    int save(T t) throws SQLException;

    void insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;
}

