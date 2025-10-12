import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OperacionesXML {

    //METODO PARA LEER DATOS DE UN XML
    public void lecturaXML(){
        File file = new File("usuarios.xml");                                          //1.Instanciamos un objeto de la clase File y le pasamos el archivo que queremos leer (usuarios.xml)
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();   //2. Necesitamos la clase DocumentBuilderFactory que crea objetos DocumentBuilder (cambia de XML a document para que Java lo entienda
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();      //3. Ahora podemos crear el DocumentBuilder a traves deL builderFactory, tendremos que capturar la excepción
            Document document = documentBuilder.parse(file);                                    //4. Ahora que ya tenemos el documentBuilder, vamos a intentar parsear el documento file
            //document.getElementsByTagName() -->                                               //5. obtenemos NodeList (lista/array de nodos que contienen esa etiqueta, por tanto tenemos que instanciar un array antes
            NodeList nodosUsuario = document.getElementsByTagName("usuario");                   //5.1 nodeList con todos los elementos que contienen usuario
            System.out.println(nodosUsuario.getLength());                                       //6. TEST: syso para obtener la longitud de usuarios desde una app que se llame xml
            for (int i = 0; i < nodosUsuario.getLength(); i++) {                                //7. Recorremos el array con el for hasta el getLength()
                Element nodo = (Element) nodosUsuario.item(i);                                  //8. casteamos el i a "nodo"
                String nacionalidad = nodo.getAttribute("nacionalidad");                  //9. nodo nos permite sacar datos como atributos, nombres, hijos, etc.
                System.out.println("La nacionalidad es: "+ nacionalidad);
                int edad = Integer.parseInt(nodo.getAttribute("edad"));                   //10. atributo edad. que hay que parsearlo de string a int
                System.out.println("La edad es: " + edad);
                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent(); //11. para sacar atributos que están en niveles más bajos dentro del nodo, como nombre, tenemos que recurrir a los niveles que ya hemos descifrado y dar las posiciones y atributos o contenido si tuviera
                String apellido = nodo.getElementsByTagName("apellido").item(0).getTextContent();
                System.out.println("El nombre es: "+nombre);
                System.out.println("El apellido es"+apellido);

                Usuario usuario = new Usuario(nombre, apellido, nacionalidad, edad);
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");                                           //3.1 los errores pueden ser porque el xml está mal configurado (más de un nodo root, nodos sin cerrar, etc.)
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");                                //4.1 IO Exception permisos de lectura
        } catch (SAXException e) {
            System.out.println("Error de SAX");                                                 //4.2 Error en el parseo real
        }

    }

}
