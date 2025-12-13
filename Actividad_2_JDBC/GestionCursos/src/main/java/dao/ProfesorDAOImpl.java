package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesorDAOImpl implements InterfazDAO<Profesor> {

    //CONEXIÓN
    private Connection connection;
    public ProfesorDAOImpl() {
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insertarDato(Profesor data) {
        String query = String.format("INSERT INTO %s (%s) VALUES (?)",
                SchemaDB.PROF_TAB_NAME,
                SchemaDB.PROF_COL_NAME);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.getNombre());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error de inserción" + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Profesor> obtenerLista() {
        return null;
    }

    @Override
    public void actualizarDato(Profesor dataNuevo) {

    }

    @Override
    public int borrarDatos(int id) {
        return 0;
    }
}
