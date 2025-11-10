package dao;

import database.DBConnection;
import database.SchemeDB;
import model.Libro;
import model.Prestamo;

import java.sql.*;
import java.util.ArrayList;

public class PrestamoDAOImp implements InterfazDao<Prestamo> {

    private Connection connection;
    public PrestamoDAOImp(){
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insertarDato(Prestamo data) {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) values (?,?,?,?)",
                SchemeDB.TABPREST_NAME,
                SchemeDB.COLPR_NAME, SchemeDB.COLPR_FECHA, SchemeDB.COLPR_STATUS, SchemeDB.COLPR_IDLIBRO);

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, data.getNombre());
            preparedStatement.setDate(2, data.getFechaPrestamo());
            preparedStatement.setString(3, data.getEstado());
            preparedStatement.setInt(4, data.getId_libro());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error en la query"); e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Prestamo> obtenerLista() {
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemeDB.TABPREST_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombre = resultSet.getNString(SchemeDB.COLPR_NAME);
                Date fecha = resultSet.getDate(SchemeDB.COLPR_FECHA);
                String estado = resultSet.getNString(SchemeDB.COLPR_STATUS);
                int idLibro = resultSet.getInt(SchemeDB.COLPR_IDLIBRO);
                listaPrestamo.add(new Prestamo(nombre, fecha, estado, idLibro));
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

        return listaPrestamo;
    }

    @Override
    public void actualizarDato(Prestamo dataNuevo) {
        String query = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=? WHERE %s=?",
                SchemeDB.TABPREST_NAME,
                SchemeDB.COLPR_NAME, SchemeDB.COLPR_FECHA, SchemeDB.COLPR_STATUS, SchemeDB.COLPR_IDLIBRO,
                SchemeDB.COLPR_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dataNuevo.getNombre());
            preparedStatement.setDate(2, dataNuevo.getFechaPrestamo());
            preparedStatement.setString(3, dataNuevo.getEstado());
            preparedStatement.setInt(4, dataNuevo.getId_libro());
            preparedStatement.setInt(5, dataNuevo.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error de sintaxis en la query");
        }

    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemeDB.TABPREST_NAME, SchemeDB.COLPR_ID);
        try {
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de sintaxis en la query");
        }
        return -1; //el 0 es del execute update y el 1 es error
    }
}
