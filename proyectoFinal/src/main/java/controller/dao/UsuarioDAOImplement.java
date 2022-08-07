package controller.dao;

import baseDatos.BaseDeDatos;
import controller.PrincipalYSignIn;
import model.Usuario;
import model.Video;

import java.sql.*;
import java.util.List;

public class UsuarioDAOImplement implements DAOUsuario{

    @Override
    public Usuario get(int userId) throws SQLException {
        Usuario usuario = null;
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();
        String getUsuario = "select nombre, apellido,userName, userPassword, id, imagen from usuarios where id = " + "'" + userId + "'";
        PreparedStatement preparedStatement = connectDB.prepareStatement(getUsuario);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String userName = resultSet.getString("userName");
            String userPassword = resultSet.getString("userPassword");
            int id = resultSet.getInt("id");
            String imagen = resultSet.getString("imagen");

            usuario = new Usuario(nombre,apellido,userName,userPassword,id,imagen);
        }
        return usuario;

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


    public int getUserId() throws SQLException, ClassNotFoundException {
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();
        String id = "select id from usuarios where userName = " + "'" + PrincipalYSignIn.userName + "'" + " and userPassword = " + "'" + PrincipalYSignIn.userPassword + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(id);
        queryResult.next();
        String user = queryResult.getString(1);
        return Integer.parseInt(user);
    }

    public Boolean verificarId(int userId) throws SQLException {
        boolean error = false;
        BaseDeDatos connectNow = new BaseDeDatos();
        Connection connectDB = connectNow.getConnection();
        String verifyId = "select count(1) from usuarios where id = " + "'" + userId + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyId);
            queryResult.next();
            if (queryResult.getInt(1) == 1) {
                    error = true;
            } else {
                    error = false;
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            e.getCause();
        }
        return error;
    }

    public Boolean verificarUsuario(String userName, String password) throws SQLException {
        boolean error = true;
        BaseDeDatos connectNow = new BaseDeDatos();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "select count(1) from usuarios where userName = " + "'" + userName + "'" + " and userPassword = " + "'" + password + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {

                } else {
                    error = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            e.getCause();
        }
        return error;
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
