import dao.LibroDAOImp;
import dao.PrestamoDAOImp;
import database.DBConnection;
import model.Libro;
import model.Prestamo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    static void main(String[] args) throws ParseException {
        Connection connection = DBConnection.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            connection.getCatalog();
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }

        Libro libro1 = new Libro("123", "El Quijote de La Mancha", "Miguel de Cervantes", "Alfaguara", 2003, "Novela", 25.50, 2);
        Libro libro2 = new Libro("134", "Rayuela", "Julio Cortazar", "Planeta", 1990, "Novela", 30, 5);
        Libro libro3 = new Libro("145", "Un Mundo Feliz", "Aldoux Huxley", "Espasa", 2005, "Novela", 22.99, 7);


        Prestamo prestamo1 = new Prestamo("Javier Gómez", new Date(sdf.parse("2025-11-10").getTime()), "Activo", 1);
        Prestamo prestamo2 = new Prestamo("María Jimenez", new Date(sdf.parse("2025-11-7").getTime()), "Activo", 2);
        Prestamo prestamo3 = new Prestamo("Paco Clavel", new Date(sdf.parse("2025-11-5").getTime()), "Activo", 3);


        //INSERCION DE DATOS EN LA BBDD

        LibroDAOImp libroDAO = new LibroDAOImp();
        //libroDAO.insertarDato(libro1);
        //libroDAO.insertarDato(libro2);
        //libroDAO.insertarDato(libro3);

        PrestamoDAOImp prestamoDAOImp = new PrestamoDAOImp();
        //prestamoDAOImp.insertarDato(prestamo1);
        //prestamoDAOImp.insertarDato(prestamo2);
        //prestamoDAOImp.insertarDato(prestamo3);
        //prestamoDAOImp.borrarDatos(1);
        //prestamoDAOImp.borrarDatos(2);
        //prestamoDAOImp.borrarDatos(3);

        ArrayList<Libro> listaLibros = libroDAO.obtenerLista();
        System.out.println(listaLibros);


        OperacionesXML operacionesXML = new OperacionesXML();
        operacionesXML.escrituraXML();
    }
}
