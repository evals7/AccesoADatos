package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface InterfazDAO<T> {   //DAO: definimos todos los métodos que el usuario peude hacer contra la bbdd. Los métodos son abstractos
                                //puede haber métodos default void generales
                                //el T actua de genérico para que nos sirva para diferentes clases, no solo usuarios

    boolean insertarDato(T data) throws SQLException;

    ArrayList<T> obtenerLista();

    ArrayList<String> obtenerCorreos();

    void actualizarDato(T dataNuevo);

}
