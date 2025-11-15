package controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dao.UsuarioDAOImp;
import model.Usuario;
import model.UsuarioJSON;
import model.UsuarioResponse;
import model.UsuarioXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    //metodo para importar JSON
    public void importarJSON(){
        ObjectMapper mapper = new ObjectMapper();                                   //2. Necesitamos instanciar un objeto de ObjectMapper para coger la respuesta y leer lo que tiene
        try {
            URL url = new URL("https://dummyjson.com/users");                   //1. Necesitamos pasarle una URL. Capturamos la excepción
            UsuarioResponse response = mapper.readValue(url, UsuarioResponse.class);  //3. al mapper le pasamos la url y traduce en la clase UsuarioResponse, se lo damos al objeto de UsuarioResponse
            for(UsuarioJSON item: response.getUsers()){                                 //4. creamos un foreach para recorrer los usuarios que obtenga response
                /*int telefono = Integer.parseInt(item.getPhone()                         //el teléfono habría que parsearloa int, pero da un problema futuro y es que tendrán más de 8 dígitos, por lo que nos quitamos de golpe y lo declararemos string
                        .replaceAll(" ", "")
                        .replaceAll("\\+","")
                        .replaceAll("-", ""));
                */
                Usuario usuario = new Usuario(item.getFirstName(), item.getEmail(), item.getAge(), 2);       //5. nos cogemos los datos de response y los pasamos a un nuevo usuario. el idPerfil metemos un 2 para que los usuarios tengan el 2
                usuarioDAOImp.insertarDato(usuario);                                    //6. Una vez que tenemos el usuario, usamos el insertarDato para añadirlo a Usuario

            }
        } catch (MalformedURLException e) {
            System.out.println("La URL no existe o es errónea");
        } catch (StreamReadException | DatabindException | SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Metodo para exportar a un XML
    public void exportacionXML(){
        JAXBContext context = null;                     //1. Declaramos el contexto
        Marshaller marshaller;                     //3. Declaramos el marshaller
        List<Usuario> lista = usuarioDAOImp.obtenerLista();   //6 Para pasar la lista, tenemos que crear una lista y pasarle los usuarios de la bbdd con el metodo de obtener usuarios
        UsuarioXML usuarioXML = new UsuarioXML();  //7. creamos un objeto de tipo UsuarioXML
        usuarioXML.setLista(lista);                 //8. AL objeto usuarioXML le pasamos la lista creada

        try {
            context = JAXBContext.newInstance(UsuarioXML.class);                   //2. instanciamos el contexto y caputramos la excepcion
            marshaller = context.createMarshaller();                                        //4. Instanciamos el marshaller y creamos uno nuevo
            marshaller.marshal(usuarioXML, new File("src/main/java/controller/usuarios.xml")); //5. pasamos  la lista de usuarios y laruta

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //Metodo para importar un XML a la bbdd
    public void importacionXML(){
        JAXBContext context;
        Unmarshaller unmarshaller;
        try {
            context = JAXBContext.newInstance(UsuarioXML.class);
            unmarshaller = context.createUnmarshaller();
            UsuarioXML usuarioXML = (UsuarioXML) unmarshaller.unmarshal(new File("src/main/java/controller/usuarios.xml")); //hay que castear el objeto de tipo object a un UsuarioXML que contiene una lista
            for(Usuario item :usuarioXML.getLista()){
                usuarioDAOImp.insertarDato(item);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para exportar a un JSON desde la bbdd
    public void exportarJSON(){
        ObjectMapper mapper = new ObjectMapper(); //1.. instanciamos el mapper dle objectMapper
        ObjectWriter response = mapper.writerFor(UsuarioResponse.class); //2. declaramos un objeto response de objectWriter
        ArrayList<Usuario> lista = usuarioDAOImp.obtenerLista();  //3.1 si queremos sacar un solo usuario, tenemos que obtener toda la lista y coger solo el primer objeto

        /*
        Usuario usuario = lista.getFirst();         //3.2 creamos un objeto de usuario

        File file = new File("src/main/java/controller/usuarios.json");  //3.3 creamos un nuevo file y le pasamos la ruta
        PrintWriter pw = null;                      //3.4 lanzamos el printWriter y le pasamos el file. Capturamos la excepción


        try {
            pw = new PrintWriter(new FileWriter(file)); //3.4
            pw.println(usuario);                        //3.5 pedimos imprimir el usuario 1º de la lista
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pw.close();                                 //3.6 acordarse de cerrar el proceso
        }
        */

        try {
            response.createGenerator(new File("src/main/java/controller/usuarios.json"), JsonEncoding.UTF8); //2. aplicamos createGererator a response y le pasamos el nuevo archivo donde vamos a exportar

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
