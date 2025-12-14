package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.*;
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
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getNombre());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                data.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error de inserción" + e.getMessage());
        }

        return false;
    }


    @Override
    public ArrayList<Profesor> obtenerLista() {
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM profesores");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idProfesor = resultSet.getInt("id");
                String nameProfesor = resultSet.getString("nombre");
                listaProfesores.add(new Profesor(idProfesor, nameProfesor));
            }
        } catch (SQLException e) {
            System.out.println("Error de sintaxis");
        }
        return listaProfesores;
    }

    @Override
    public void actualizarDato(Profesor dataNuevo) {
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.PROF_TAB_NAME,
                SchemaDB.PROF_COL_NAME,
                SchemaDB.PROF_COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dataNuevo.getNombre());
            preparedStatement.setInt(2, dataNuevo.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemaDB.PROF_TAB_NAME, SchemaDB.PROF_COL_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de sintaxis");
        }
        return -1;
    }
}
