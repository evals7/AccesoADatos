import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MainLector {
    static void main(String[] args) {
        File file = new File("src/main/java/estudiantes.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList nodosEstudiante = document.getElementsByTagName("estudiante");
            System.out.println(nodosEstudiante.getLength());

            for(int i=0; i<nodosEstudiante.getLength(); i++){
                Element nodo = (Element) nodosEstudiante.item(i);  //casteamos i a nodo
                String id = nodo.getAttribute("id");                                            //LEER ATRIBUTOS
                System.out.println("ID: "+ id);
                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent();           //LEER ELEMENTOS
                System.out.println("Nombre: " + nombre);
                String edad = nodo.getElementsByTagName("edad").item(0).getTextContent();
                System.out.println("Edad: " + edad);

                NodeList nodosAsignatura = nodo.getElementsByTagName("asignatura");
                for(int f=0; f<nodosAsignatura.getLength();f++){
                    Element nodo2 = (Element) nodosAsignatura.item(f);
                    String nombreAS = nodo2.getElementsByTagName("nombre").item(0).getTextContent().trim();
                    String notaAS = nodo2.getElementsByTagName("nota").item(0).getTextContent();
                    System.out.println("nombreAS = " + nombreAS);
                    System.out.println("notaAS = " + notaAS);
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error de parseo");
        } catch (IOException e) {
            System.out.println("No hay permisos de lectura");
        } catch (SAXException e) {
            System.out.println("Error de SAX");
        }


    }

}
