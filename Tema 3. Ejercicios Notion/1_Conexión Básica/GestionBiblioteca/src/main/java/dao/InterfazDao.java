package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfazDao<T> {

    boolean insertarDato(T data);   //CREATE

    ArrayList<T> obtenerLista();        //READ

    void actualizarDato(T dataNuevo);   //UPDATE

    int borrarDatos(int id);            //DELETE
}
