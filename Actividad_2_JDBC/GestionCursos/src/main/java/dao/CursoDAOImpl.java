package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.*;
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
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getNombre());
            preparedStatement.setInt(2, data.getProfesor().getId());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                data.setId(rs.getInt(1));
            }
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
            preparedStatement = connection.prepareStatement("select * from cursos inner join profesores p on id_profesor = p.id");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int idProfesor = resultSet.getInt("p.id");
                String nombreProfesor =  resultSet.getString("p.nombre");
                Profesor profesor = new Profesor(idProfesor, nombreProfesor); //profesores de la clase Profesor
                //El Listado de estudiantes no es relevante para obtener el listado de cursos, por eso no lo añado.
                // Para saber los estudiantes de cada curso, usar el metodo obtenerListaEstudiantes()
                listaCursos.add(new Curso(id, nombre, profesor));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursos;
    }

    public ArrayList<Curso> obtenerListaCursos(Profesor profesor) {
        ArrayList<Curso> listaCursosProfesor = new ArrayList<>();
        try {
            String query = "SELECT * FROM cursos WHERE id_profesor=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, profesor.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                listaCursosProfesor.add(new Curso(id, name, profesor));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursosProfesor;
    }

    //UPDATE
    @Override
    public void actualizarDato(Curso dataNuevo) {
        String query = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?",
                SchemaDB.CURSO_TAB_NAME,
                SchemaDB.CURSO_COL_NAME, SchemaDB.CURSO_COL_IDPROF,
                SchemaDB.CURSO_COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dataNuevo.getNombre());
            preparedStatement.setInt(2, dataNuevo.getProfesor().getId());
            preparedStatement.setInt(3, dataNuevo.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE
    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemaDB.CURSO_TAB_NAME, SchemaDB.CURSO_COL_ID);
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
