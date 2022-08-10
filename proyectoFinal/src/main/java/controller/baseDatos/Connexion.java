package controller.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connexion {

    private static String URL = "jdbc:mysql://localhost:3306/proyecto";
    private static String USER = "root";
    private static String PASS = "Developer2022$";

    /**
     * Constructor de la clase
     */
    public Connexion() {
    }

    /**
     * Funcion que crea la conexion a la base de datos
     * @return devuelve los settings de la conexion
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASS);

        return connection;
    }

    /**
     * Funcionq que ayuda a cerrar la declaracion de la base de datos
     * @param statement recibe declaracion de la base de datos
     * @throws SQLException
     */
    public static void closePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    /**
     * Funcion que ayuda a cerrar la conexion con la base de datos
     * @param connection Recibe unos settings para la conexion
     * @throws SQLException
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
