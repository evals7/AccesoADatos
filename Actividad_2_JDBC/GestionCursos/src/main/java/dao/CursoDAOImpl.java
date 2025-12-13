package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAOImpl implements InterfazDAO<Curso> {

    //CONEXIÓN
    private Connection connection;
    public CursoDAOImpl() {
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //CREATE
    @Override
    public boolean insertarDato(Curso data) {
        String query = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                SchemaDB.CURSO_TAB_NAME,
                SchemaDB.CURSO_COL_NAME, SchemaDB.CURSO_COL_IDPROF);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.getNombre());
            preparedStatement.setInt(2, data.getProfesor().getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error de inserción" + e.getMessage());
            return false;
        }
    }

    //READ
    @Override
    public ArrayList<Curso> obtenerLista() {
        ArrayList<Curso> listaCursos = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from "+SchemaDB.CURSO_TAB_NAME+" inner join "+SchemaDB.PROF_TAB_NAME+" p on id_profesor = p.id");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(SchemaDB.CURSO_TAB_NAME+"."+SchemaDB.CURSO_COL_ID);
                String nombre = resultSet.getString(SchemaDB.CURSO_TAB_NAME+"."+SchemaDB.CURSO_COL_NAME);
                Profesor profesor = new Profesor(resultSet.getInt("p."+SchemaDB.PROF_COL_ID), resultSet.getString("p."+SchemaDB.PROF_COL_NAME));
                listaCursos.add(new Curso(id, nombre, profesor));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursos;
    }

    //UPDATE
    @Override
    public void actualizarDato(Curso dataNuevo) {
        String query = String.format("UPDATE %s SET %s?, %s? WHERE %s?",
                SchemaDB.CURSO_TAB_NAME,
                SchemaDB.CURSO_COL_NAME, SchemaDB.CURSO_COL_IDPROF,
                SchemaDB.CURSO_COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dataNuevo.getNombre());
            preparedStatement.setInt(2, dataNuevo.getProfesor().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE
    @Override
    public int borrarDatos(int id) {
        return 0;
    }
}
