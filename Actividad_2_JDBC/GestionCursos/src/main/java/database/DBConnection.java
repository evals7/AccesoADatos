package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(fileInputStream);
            String user=properties.getProperty("user");
            String pass=properties.getProperty("pass");
            String url=properties.getProperty("url");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (FileNotFoundException e) {
            System.out.println("El fichero indicado no existe");
        } catch (SQLException e) {
            System.out.println("no tienes permisos de acceso al fichero");
        } catch (IOException e) {
            System.out.println("Error en la ejecución SQL");
        }
    }
}
