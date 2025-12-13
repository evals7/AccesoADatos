package dao;

import java.util.ArrayList;

public interface InterfazDAO<T> {

    //Create
    boolean insertarDato(T data);
    //Read
    ArrayList<T> obtenerLista();
    //Update
    void actualizarDato(T dataNuevo);
    //Delete
    int borrarDatos(int id);

}
