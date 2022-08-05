package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {

    private static String URL = "jdbc:mysql://localhost:3306/proyecto";
    private static String USER = "root";
    private static String PASS = "Developer2022$";

    public BaseDeDatos() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASS);

        return connection;
    }


    public static void closePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
