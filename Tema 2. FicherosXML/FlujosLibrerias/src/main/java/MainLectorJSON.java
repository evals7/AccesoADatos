import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import model.RespuestaProductos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainLectorJSON {

    //CLASE PARA LEER EL ARCHIVO JSON
    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();                           //1. Instanciamos un objeto de ObjectMapper

        try {
            URL url = new URL("https://dummyjson.com/products");    //2. Instanciamos la URL que queremos leer
            RespuestaProductos respuestaProductos = mapper.readValue(url, RespuestaProductos.class);                //4. Utilizamos el metodo readValue del mapper y le pasamos la url(de donde lee) y la clase RespuestaProductos(a que lo traduce. Nos devolverá respuestaProductos que es una lista de Productos que podemos recorrer
            for (Product item : respuestaProductos.getProducts()){
                System.out.println(item);
            }
        } catch (MalformedURLException e) {
            System.out.println("Error en la URL indicada");                 //3. Capturamos la excepción
        } catch (StreamReadException e) {                                   //5. capturamos varias excepciones posibles
            System.out.println("Error en la lectura. no es un Json");
        } catch (DatabindException e) {
            System.out.println("Error en el mapeado del Json");
        } catch (IOException e) {
            System.out.println("Error en la conexión de red");
        }


    }
}
