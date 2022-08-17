package controller.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase connexion que hace la connection a la base de datos
 */
public class Connexion {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto";
    private static final String USER = "root";
    private static final String PASS = "Developer2022$";

    /**
     * Constructor de la clase
     */
    public Connexion() {
    }

    /**
     * Function que crea la conexion a la base de datos
     * @return devuelve los settings de la conexion
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(URL, USER, PASS);

        return connection;
    }

    /**
     * Function que ayuda a cerrar la declaration de la base de datos
     * @param statement recibe declaration de la base de datos
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static void closePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    /**
     * Function que ayuda a cerrar la conexion con la base de datos
     * @param connection Recibe unos settings para la conexion
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
