import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class mainEscritor {

    public static void main(String[] args){
        Listado listado = new Listado();                                                    //11. Instanciamos un objeto de Listado y añadimos nuevos objetos de Usuario
        listado.getListado().add(new Usuario("123A", "Eva1", "Lopez5"));
        listado.getListado().add(new Usuario("223A", "Eva2", "Lopez5"));
        listado.getListado().add(new Usuario("323A", "Eva3", "Lopez5"));
        listado.getListado().add(new Usuario("423A", "Eva4", "Lopez5"));
        listado.getListado().add(new Usuario("523A", "Eva5", "Lopez5"));

                                                                                //12. Elemento que nos permite hacer la escritura, lo sacamos de la librería JAXB
        try {
            JAXBContext context = JAXBContext.newInstance(Listado.class);        //13. le pasamos la clase en la que nos vamos a basar. Listado
            Marshaller marshaller = context.createMarshaller();                  //15. Ahora creamos el mapeador, que le damos la definición de la clase y va a sacar todas las anotaciones que hemos puesto
            marshaller.marshal(listado, new File("usuarios.xml"));     //16. ya podemos mapear el objeto listado en un archivo que vamos a crear
        } catch (JAXBException e) {                                             //14. Capturamos la excepción JAXBException, por si alguna de las anotaciones estuviera mal
            throw new RuntimeException(e);
        }


    }
}
