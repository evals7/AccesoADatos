import database.DBConnection;
import database.SchemeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args){
        //establecemos la conexión con el patron singleton
        Connection connection = DBConnection.getConnection();  //4. llamamos al metodo getConnection desde la propia clase (sin necesidad de crear un objeto.).
                                                                //9.  Se lo damos al objeto de la clase Connection
        try {
            System.out.println(connection.getCatalog());           //10. comprobamos que la conexión ess correcta llamando al getCatalog()
        } catch (SQLException e) {
            System.out.println("error en la conexión");
        }
        //CREATE
        try {
            Statement statement = connection.createStatement();  //16. vamos a crear un bloque statement con instrucciones
            String nombre= "Nombre insertar";
            String mail = "Mail insertar";
            int telefono =123;
            int idPerfil =1;
                                                                //17. creamos la query de insercion sacando los valores de la SchemeDB donde hemos establecido las columnas como constantes
            String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%d','%d')",
                    SchemeDB.TAB_NAME,
                    SchemeDB.COL_NAME,
                    SchemeDB.COL_MAIL,
                    SchemeDB.COL_PHONE,
                    SchemeDB.COL_PROFILE,
                    nombre,mail,telefono,idPerfil
                    );

        //    statement.execute(query) ;                  //18. pasamos el string de la query a statement
        } catch (SQLException e) {
            System.out.println("Error en la ejecución de query");
        }
        //UPDATE - BORRADO
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?", //creamos la query
                SchemeDB.TAB_NAME,
                SchemeDB.COL_PROFILE,
                SchemeDB.COL_PHONE
        );
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);  //pasamos la query al prepared statement = en preparación
            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, 234);
            int numRow = preparedStatement.executeUpdate();   //ejecutamos el statement update
            if(numRow>0){
                System.out.println("Has actualizado los registros de la base de datos");
                System.out.println("En concreto, se han visto afectadas " + numRow +" filas");
            }else {
                System.out.println("Ninguna fila se ha cambiado");
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia query");
        }

    }
}
