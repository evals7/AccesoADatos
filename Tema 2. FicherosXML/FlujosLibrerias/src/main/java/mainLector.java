import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class mainLector {
    public static void main(String[] args){
        try {
            JAXBContext context = JAXBContext.newInstance(Listado.class);   //1. utilizamos la clase JAXBContext para leer una Listado.class
            Unmarshaller unmarshaller = context.createUnmarshaller();       //2. creamos el desmapeado
            Listado listado = (Listado) unmarshaller.unmarshal(new File("usuarios.xml"));      //3. damos la orden de desmapear el archivo usuarios.xml. Esto nos dará un objeto que contiene una lista, por lo que lo igualaremos a Listado (ArrayList) y castearemos el objeto a (listado)
            for (Usuario item :listado.getListado()){                       //4. Recorremos el listado y mostramos datos usando el metodo mostrarDatos() que hemos hecho en la clase Usuario
                item.mostrarDatos();
            }

        } catch (JAXBException e) {
            System.out.println("Mapeado fallido");                                //1.1 Capturamos la excepción
        }
    }
}
