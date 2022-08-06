package controller.dao;

import baseDatos.BaseDeDatos;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAOImplement implements DAOUsuario{

    @Override
    public Usuario get(int indiceConteo) throws SQLException {
        return null;
    }

    @Override
    public List<Usuario> getALL() throws SQLException {
        return null;
    }

    @Override
    public int save(Usuario usuario) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Usuario usuario) throws SQLException {

    }

    @Override
    public void insertarUsuario(String nombre, String apellido1, String userName, String userPassword, int userId, String img) throws SQLException, ClassNotFoundException {
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();

        String insertInfo = "insert into usuarios(nombre, apellido,userName, userPassword, id, imagen) values ('" + nombre + "','" + apellido1  + "','" + userName + "','" + userPassword + "','" + userId + "','" + img + "')";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertInfo);
        }catch (Exception e){
            e.printStackTrace();

        }
    }



    @Override
    public int update(Usuario usuario) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Usuario usuario) throws SQLException {
        return 0;
    }
}
