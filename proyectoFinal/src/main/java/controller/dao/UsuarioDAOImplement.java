package controller.dao;

import baseDatos.BaseDeDatos;
import controller.PrincipalYSignIn;
import model.Usuario;
import model.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImplement implements DAOUsuario {
    boolean nuevoUsurio;
    ArrayList<Usuario> usuarios;
    Usuario usuario;

    /**
     * Constructor de la clase
     */
    public UsuarioDAOImplement() {
        nuevoUsurio = true;
        usuarios = new ArrayList<>();
        usuario = new Usuario();
    }

    /**
     * Funcion que obtiene un usuario por medio de un id
     * @param userId id del usuario
     * @return devuelve un usuario
     * @throws SQLException
     */
    @Override
    public Usuario get(int userId) throws SQLException {
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();

        if(nuevoUsurio){
            String getUsuario = "select nombre, apellido,userName, userPassword, id, imagen from usuarios where id = " + "'" + userId + "'";
            PreparedStatement preparedStatement = connectDB.prepareStatement(getUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String userName = resultSet.getString("userName");
                String userPassword = resultSet.getString("userPassword");
                int id = resultSet.getInt("id");
                String imagen = resultSet.getString("imagen");

                usuario = new Usuario(nombre, apellido, userName, userPassword, id, imagen);
            }
            BaseDeDatos.closePreparedStatement(preparedStatement);
            BaseDeDatos.closeConnection(connectDB);
        }
        return usuario;

    }

    /**
     * Funcion que obtiene todos los usuarios registrados
     * @return Devuelve null
     * @throws SQLException
     */
    @Override
    public List<Usuario> getALL() throws SQLException {
        return null;
    }

    /**
     *Funcion que guarda la informacion de un usuario
     * @param usuario recibe un usuario
     * @return devuelve cero
     * @throws SQLException
     */
    @Override
    public int save(Usuario usuario) throws SQLException {
        return 0;
    }

    /**
     * Funcion que permite insertar un usuario en la base de datos y en una lista de usuarios
     * @param usuario recibe un usuario
     * @throws SQLException
     */
    @Override
    public void insert(Usuario usuario) throws SQLException {
        nuevoUsurio = true;
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();

        String insertInfo = "insert into usuarios(nombre, apellido,userName, userPassword, id, imagen) values ('" + usuario.getNombre() + "','" + usuario.getApellido1() + "','" + usuario.getUserName() + "','" + usuario.getUserPassword() + "','" + usuario.getUserId() + "','" + usuario.getImg() + "')";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertInfo);
        } catch (Exception e) {
            e.printStackTrace();

        }

        BaseDeDatos.closeConnection(connectDB);
        usuarios.add(new Usuario(usuario.getNombre(),usuario.getApellido1(),usuario.getUserName(),usuario.getUserPassword(),usuario.getUserId(),usuario.getImg()));

    }

    /**
     *Funcion que obtiene el id de un usuario en especifico
     * @return devuelve el id del usuario
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public int getUserId() throws SQLException, ClassNotFoundException {
        BaseDeDatos connection = new BaseDeDatos();
        Connection connectDB = connection.getConnection();
        String id = "select id from usuarios where userName = " + "'" + PrincipalYSignIn.userName + "'" + " and userPassword = " + "'" + PrincipalYSignIn.userPassword + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(id);
        queryResult.next();
        String user = queryResult.getString(1);
        BaseDeDatos.closeConnection(connectDB);
        return Integer.parseInt(user);
    }

    /**
     * Funcion que verifica si el id del usuario se encuentra en la base de datos
     * @param userId recibe el id de un usuario
     * @return  Devuelve true o false
     * @throws SQLException
     */
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
        BaseDeDatos.closeConnection(connectDB);
        return error;
    }

    /**
     * Funcion que verifica si el usuario existe por medio de el nombre de usuario y contraseña
     * @param userName recibe un nombre de usuario
     * @param password recibe una contraseña
     * @return devuelve true o false
     * @throws SQLException
     */
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
        BaseDeDatos.closeConnection(connectDB);
        return error;
    }

    /**
     * Funcion que actualiza la informacion de un usuario
     * @param usuario Recibe un usuario
     * @return Devuelve cero
     * @throws SQLException
     */
    @Override
    public int update(Usuario usuario) throws SQLException {
        return 0;
    }

    /**
     * Funion que elimina un usuario
     * @param usuario recibe un usuario
     * @return Devuelve 0
     * @throws SQLException
     */
    @Override
    public int delete(Usuario usuario) throws SQLException {
        return 0;
    }
}
