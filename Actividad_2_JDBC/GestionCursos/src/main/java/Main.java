import dao.CursoDAOImpl;
import database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static void main(String[] args) {

        //PRUEBA CONEXIÓN
        Connection connection = DBConnection.getConnection();

        try {
            System.out.println(connection.getCatalog());

        } catch (SQLException e) {
            System.out.println("error en la conexión");
        }


        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        System.out.println(cursoDAO.obtenerLista());

    }
}
