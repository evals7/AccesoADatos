package dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.DBConnection;
import database.SchemeDB;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAOImp implements InterfazDAO<Usuario> { //1. Implementamos la interfaz y sus métodos

    private Connection connection;                          //2. Establecemos la conexión con la bbdd: declaramos una conexión y la la inicializamos con getConnection
    public UsuarioDAOImp(){
        connection = DBConnection.getConnection();
    }
    private PreparedStatement preparedStatement;            //3. Para ejecutar las queries, necesitamos uno de los dobjetos de Clases: preparedStatement o Statement (mejor el primero) y resultSet
    private ResultSet resultSet;

    @Override
    public boolean insertarDato(Usuario data) throws SQLException {         //4. Metodo Crear
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE);        //4.1. creamos la query SQL para insertar los datos (todos menos el ID)


            preparedStatement = connection.prepareStatement(query);                                     //4.2. inicializamos el preparedStatement pasandole la query, y en vez de capturar la excepción, haremos throws para gestionarla en el controller
            preparedStatement.setString(1,data.getNombre());                               //4.3. parametrizamos o seteamos los ? . el IDUSUARIO es increemntal, por eso no lo añadimos
            preparedStatement.setString(2,data.getMail());
            preparedStatement.setInt(3,data.getTelefono());
            preparedStatement.setInt(4,data.getIdPerfil());
            return preparedStatement.execute();                                                          //4.4 ejecutamos la sentencia y que devuelva si no ha habido fallo (false)
        //} catch (SQLException e) {                                                                    //4.6 esta excepción la añadiremos en el throws a este metodo, al de la interfaz y lo trataremos en el metodo del controller
        //TODO: gestionar en el controller

    }

    @Override
    public ArrayList<Usuario> obtenerLista() {                                                          //5. Metodo Read
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();                                           //5.2 Declaramos un nuevo arrayList para meter la lista
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemeDB.TAB_NAME);  //5.1 pasamos al preparedStatement la query con la tabla "genérica". No necesitamos un formateo porquqe la query es muy sencilla. Capturamos la excepción
            resultSet = preparedStatement.executeQuery();                                               //5.2 Inicializamos result set pasándole el executeQuery. Result set hay que recorrerlo en un bucle
            while (resultSet.next()){                                                                   //5.3 recorremos el resultset y cada columna se la asignamos a una variable instanciada aquí
                String nombre = resultSet.getString(SchemeDB.COL_NAME);
                String mail = resultSet.getString(SchemeDB.COL_MAIL);
                int telefono = resultSet.getInt(SchemeDB.COL_PHONE);
                int idPerfil = resultSet.getInt(SchemeDB.COL_PROFILE);
                Usuario usuario = new Usuario(nombre, mail, telefono, idPerfil);                        //5.4 creamos un usuario nuevo y le pasamos todos los atributos añadidos
                listaUsuarios.add(usuario);                                                             //5.5 añadimos el usuario a la lista
            }
            return listaUsuarios;                                                                       //5.6 al salir del bucle devolvemos la lista de usuarios

        } catch (SQLException e) {
            //error en la query
        }
        return null;                                                                                    //5.7 si llegamos aquí, es que ha habido un error
    }

    @Override
    public ArrayList<String> obtenerCorreos() {
        return null;
    }

    @Override
    public void actualizarDato(Usuario dataNuevo) {

    } //al darle un tipo al interfazDao, implementará los métodos con el mismo título


}   //la lógica contra la bbdd

