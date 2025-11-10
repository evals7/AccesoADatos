import database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Entrada {
    static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        try {
            System.out.println(connection.getCatalog());
            System.out.println("La conexión funciona");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
        }

        try {
            connection.close();
            System.out.println("La conexión está cerrada");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
        }
    }
}
