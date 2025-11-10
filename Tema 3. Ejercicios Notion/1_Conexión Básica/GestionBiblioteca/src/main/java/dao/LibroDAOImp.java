package dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.DBConnection;
import database.SchemeDB;
import model.Libro;

import java.sql.*;
import java.util.ArrayList;

public class LibroDAOImp implements InterfazDao<Libro> {

    private Connection connection;
    public LibroDAOImp(){
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insertarDato(Libro data) {
    String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) values (?,?,?,?,?,?,?,?)",
        SchemeDB.TABLIBROS_NAME,
        SchemeDB.COLLI_ISBN, SchemeDB.COLLI_NAME, SchemeDB.COLLI_AUTOR, SchemeDB.COLLI_EDIT, SchemeDB.COLLI_YEAR, SchemeDB.COLLI_CATH, SchemeDB.COLLI_PRICE, SchemeDB.COLLI_STOCK);

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, data.getIsbn());
            preparedStatement.setString(2, data.getTitulo());
            preparedStatement.setString(3, data.getAutor());
            preparedStatement.setString(4, data.getEditorial());
            preparedStatement.setInt(5, data.getAnoPublicacion());
            preparedStatement.setString(6, data.getCategoria());
            preparedStatement.setDouble(7, data.getPrecio());
            preparedStatement.setInt(8, data.getStock());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
        return false;
    }

    @Override
    public ArrayList<Libro> obtenerLista() {
        ArrayList<Libro> listaLibros = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemeDB.TABLIBROS_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String isbn = resultSet.getNString(SchemeDB.COLLI_ISBN);
                String titulo = resultSet.getNString(SchemeDB.COLLI_NAME);
                String autor = resultSet.getNString(SchemeDB.COLLI_AUTOR);
                String editorial = resultSet.getNString(SchemeDB.COLLI_EDIT);
                int ano = resultSet.getInt(SchemeDB.COLLI_YEAR);
                String categoria = resultSet.getNString(SchemeDB.COLLI_CATH);
                double precio = resultSet.getDouble(SchemeDB.COLLI_PRICE);
                int stock = resultSet.getInt(SchemeDB.COLLI_STOCK);
                listaLibros.add(new Libro(isbn, titulo, autor, editorial, ano, categoria, precio, stock));
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
        return listaLibros;
    }

    @Override
    public void actualizarDato(Libro dataNuevo) {
        String query = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?",
                SchemeDB.TABLIBROS_NAME,
                SchemeDB.COLLI_ISBN, SchemeDB.COLLI_NAME, SchemeDB.COLLI_AUTOR, SchemeDB.COLLI_EDIT, SchemeDB.COLLI_YEAR, SchemeDB.COLLI_CATH, SchemeDB.COLLI_PRICE, SchemeDB.COLLI_STOCK,
                SchemeDB.COLLI_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dataNuevo.getIsbn());
            preparedStatement.setString(2, dataNuevo.getTitulo());
            preparedStatement.setString(3, dataNuevo.getAutor());
            preparedStatement.setString(4, dataNuevo.getEditorial());
            preparedStatement.setInt(5, dataNuevo.getAnoPublicacion());
            preparedStatement.setString(6, dataNuevo.getCategoria());
            preparedStatement.setDouble(7, dataNuevo.getPrecio());
            preparedStatement.setInt(8, dataNuevo.getStock());
            preparedStatement.setInt(9, dataNuevo.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error de sintaxis en la query");
        }
    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemeDB.TABLIBROS_NAME, SchemeDB.COLLI_ID);
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
