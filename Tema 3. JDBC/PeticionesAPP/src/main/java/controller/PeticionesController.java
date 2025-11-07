package controller;

import dao.UsuarioDAOImp;
import database.DBConnection;
import model.Usuario;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class PeticionesController {   // capa de lógica del negocio, pero no contra la bbdd. Ejemplo, si un usuario se registra —>como actuar ante un error producido por elementos externos

    //queremos gestionar aquí métodos del DAO, pero con la gestión de excepciones y posibles errores que pudieran ocurrir (el usuario ya existe, el correo electrónico es null, etc.

    private UsuarioDAOImp usuarioDAOImp;                        //1. debemos declarar el usuarioDAO para obtener sus datos
    public PeticionesController() {                             //2. lo pedimos en el constructor de la clase para poder llamarlo más adelante. Además, este usuario llamará a la conexión (declarada en la implementación DAO)
        usuarioDAOImp = new UsuarioDAOImp();
    }
    public void insertarUsuario(Usuario usuario){                //3. Creamos el metodo

        boolean fallo = false;
        do{                                                          //3.6 introducimos la acción en un bucle doWhile para que se repita mientras haya un fallo (false instanciado)
            try {
                usuarioDAOImp.insertarDato(usuario);                 //3.1 capturamos la excepción que el metodoDAO throws (y, el interfaz, que es el primitivo) y gestionamos la duplicidad del mail
                fallo = false;                                       //3.8 tenemos que instanciar fallo a false de nuevo, puesto que en el bucle se cambia
                System.out.println("Usuario agregado correctamente");
            } catch (SQLException e) {
                Scanner scanner = new Scanner(System.in);                    //3.3 Para ello necesitamos instanciar un objeto de Scanner y leer el nuevo correo
                System.out.println("Error: El correo ya está en la BD");
                System.out.println(e.getMessage());
                System.out.println("Introduce el nuevo correo electrónico"); //3.2 queremos dar la opción de que, en caso de que haya error, se pueda pedir el mail otra vez
                String correo = scanner.next();                     //3.4 leemos el nuevo correo
                usuario.setMail(correo);                            //3.5 Insertamos el correo
                fallo = true;                                       //3.7 cuando sea true, salimos del bucle y fallo se cambiará a true

            }
        } while (fallo);




    }

    public void borrarUsuario(int id){
         int rows = usuarioDAOImp.borrarDatos(id);

        if (rows>1){
             System.out.println("Usuarios borrados correctamente");
         }else if (rows==0){
             System.out.println("No se ha encontrado usuario con ese ID");
         }else if (rows==1){
            System.out.println("Usuario borrado correctamente");
         } else{
            System.out.println("Fallo en el proceso");
         }
    }

    public void listarUsuarios(){
        for(Usuario item : usuarioDAOImp.obtenerLista()){
            item.mostrarDatos();
        }
    }

}
