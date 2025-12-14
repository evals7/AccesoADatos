import dao.CursoDAOImpl;
import dao.EstudianteDAOImpl;
import dao.ProfesorDAOImpl;
import database.DBConnection;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {

        //PRUEBA CONEXIÓN
        Connection connection = DBConnection.getConnection();

        try {
            System.out.println(connection.getCatalog());

        } catch (SQLException e) {
            System.out.println("error en la conexión");
        }


        //1. insertar un profesor con dos cursos
        Profesor profesor = new Profesor("Juan");
        List<Curso> listaCursos = new ArrayList<>();
        Curso curso1= new Curso("Matematicas");
        Curso curso2= new Curso("Informatica");
        listaCursos.add(curso1);
        listaCursos.add(curso2);

        insertarProfesorCursos(profesor, listaCursos);

        //	2. Inserta tres estudiantes y asígnalos a cursos.
        Estudiante estudiante1 = new Estudiante("Maria Gomez");
        Estudiante estudiante2 = new Estudiante("Celia Ramirez");
        Estudiante estudiante3 = new Estudiante("Carmen Gomez");
        EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
        estudianteDAO.insertarDato(estudiante1);
        estudianteDAO.insertarDato(estudiante2);
        estudianteDAO.insertarDato(estudiante3);
        estudianteDAO.insertarCursoEstudiante(estudiante1.getId(), curso1.getId());
        estudianteDAO.insertarCursoEstudiante(estudiante2.getId(), curso1.getId());
        estudianteDAO.insertarCursoEstudiante(estudiante3.getId(), curso2.getId());

        //3. Muestra los cursos de un profesor y los estudiantes inscritos en un curso.
        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        System.out.println("LISTADO DE CURSOS DE UN PROFESOR");
        System.out.println(cursoDAO.obtenerListaCursos(profesor));
        System.out.println("LISTADO DE ESTUDIANTES DE UN CURSO");
        System.out.println(estudianteDAO.obtenerListaEstudiantes(curso1.getId()));

    }

    public static void insertarProfesorCursos(Profesor profesor, List<Curso> listaCursos) {
        ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();
        // Insertar profesor
        profesorDAO.insertarDato(profesor);

        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        // Insertar cursos asignados al profesor
        for (Curso curso : listaCursos) {
            curso.setProfesor(profesor);
            cursoDAO.insertarDato(curso);
        }
    }


}
