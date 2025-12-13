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
    public ArrayList<Estudiante> obtenerLista() {
        return null;
    }

    @Override
    public void actualizarDato(Estudiante dataNuevo) {

    }

    @Override
    public int borrarDatos(int id) {
        return 0;
    }

    public ArrayList<Estudiante> obtenerListaCurso(int idCurso) {
        ArrayList<Estudiante> listaEstudiantesCurso = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s INNER JOIN cursos_estudiantes ON cursos_estudiantes.id_estudiantes=estudiantes.id WHERE cursos_estudiantes.id_curso="+idCurso,
                    SchemaDB.ESTUDIANTE_TAB_NAME,
                    SchemaDB.ESTUDIANTE_COL_NAME);

            preparedStatement = connection.prepareStatement("SELECT * FROM %s "+SchemaDB.ESTUDIANTE_TAB_NAME+" WHERE ");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("cursos.id");
                String nombre = resultSet.getString(SchemaDB.CURSO_TAB_NAME+"."+SchemaDB.CURSO_COL_NAME);
                listaEstudiantesCurso.add(new Estudiante(id, nombre));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEstudiantesCurso;
    }
}
