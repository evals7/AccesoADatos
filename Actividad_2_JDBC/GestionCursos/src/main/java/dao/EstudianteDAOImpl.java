package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAOImpl implements InterfazDAO<Estudiante> {

    //CONEXIÓN
    private Connection connection;
    public EstudianteDAOImpl() {
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean insertarDato(Estudiante data) {
        String query = String.format("INSERT INTO %s (%s) VALUES (?)",
                SchemaDB.ESTUDIANTE_TAB_NAME,
                SchemaDB.ESTUDIANTE_COL_NAME);

        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getNombre());
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

    public boolean insertarCursoEstudiante(int idEstudiante, int idCurso) {
        String query = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDB.CURSOEST_TAB_NAME,
                SchemaDB.CURSOEST_COL_ID_ESTUDIANTE,
                SchemaDB.CURSOEST_COL_ID_CURSO);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEstudiante);
            preparedStatement.setInt(2, idCurso);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error de inserción" + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Estudiante> obtenerLista() {
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idEstudiante = resultSet.getInt("id");
                String nameEstudiante = resultSet.getString("nombre");
                listaEstudiantes.add(new Estudiante(idEstudiante, nameEstudiante));
            }
        } catch (SQLException e) {
            System.out.println("Error de sintaxis");
        }
        return listaEstudiantes;
    }

    public ArrayList<Estudiante> obtenerListaEstudiantes(int idCurso) {
        ArrayList<Estudiante> listaEstudiantesCurso = new ArrayList<>();
        try {
            String query = "SELECT * FROM estudiantes INNER JOIN cursos_estudiantes ON cursos_estudiantes.id_estudiantes=estudiantes.id WHERE cursos_estudiantes.id_curso="+idCurso;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                listaEstudiantesCurso.add(new Estudiante(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEstudiantesCurso;
    }

    @Override
    public void actualizarDato(Estudiante dataNuevo) {
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.ESTUDIANTE_TAB_NAME,
                SchemaDB.ESTUDIANTE_COL_NAME,
                SchemaDB.ESTUDIANTE_COL_ID);

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
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemaDB.ESTUDIANTE_TAB_NAME, SchemaDB.ESTUDIANTE_COL_ID);
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
