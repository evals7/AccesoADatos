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

    public static Connection getConnection() {

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


            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("pass"));

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de acceso");
        } catch (SQLException e) {
            System.out.println("Error en la ejecución SQL");
        }


    }
}
