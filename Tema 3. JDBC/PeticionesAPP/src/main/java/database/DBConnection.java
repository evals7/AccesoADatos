package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    //aquí tenemos la conexión que podemos replicar (static)
    private static Connection connection;               //1. inicializamos un objeto de Connection en privado

    public static Connection getConnection(){           //2. metodo para llamar a la conexión de forma pública (desde cualquier sitio)
                                                        //3. en teoría necesitaríamos un objeto para llamar al metodo, pero tenemos la opción de llamar al metodo desde la propia clase
    if(connection==null){           //5. si la conexión es nula, inicializala llamando al metodo creado abajo
        createConnection();         //8. llamamos al metodo creado. Es privado, pertenece a la clase y solo se puede llamar desde ella
    }
        return connection;
    }

    private static void createConnection(){ //6. para este metodo necesitamos los parámetros user, pssword y url,
        /*                                      11. En principio inicializaríamos los parámetros aquí, pero para proteger la conexión hemos creado un archivo database.properties (archivo de lectura) donde metemos estas variables
        String user= "root";
        String pass= "root";
        String url = "jdbc:mysql://localhost:41063/peticiones";
        */
        Properties properties = new Properties();                //12. Instanciamos un objeto para dejar los datos
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/database.properties"); //13. leeremos el archivo database.properties y capturamos la excepción
            properties.load(fileInputStream);   //14. cargamos el archivo en properties
            String user= "root";                //15. tomamoslas propiedades declaradas desde el archivo properties
            String pass= "root";
            String url = "jdbc:mysql://localhost:41063/peticiones";
            connection = DriverManager.getConnection(url, user, pass);  //7. le damos a connection el la conexión y capturamos la excepción
        } catch (FileNotFoundException e) {
            System.out.println("El fichero indicado no existe");
        } catch (IOException e) {
            System.out.println("no tienes permisos de acceso al fichero");
        } catch (SQLException e) {
            System.out.println("Error en la ejecución SQL");
        }


    }

}
