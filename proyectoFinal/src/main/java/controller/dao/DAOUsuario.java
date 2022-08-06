package controller.dao;

import model.Usuario;

import java.sql.SQLException;

public interface DAOUsuario extends DAO<Usuario> {
    void insertarUsuario(String nombre, String apellido1,  String userName, String userPassword, int userId, String img) throws SQLException, ClassNotFoundException;

}
