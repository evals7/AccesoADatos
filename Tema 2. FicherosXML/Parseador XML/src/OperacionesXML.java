import model.Actor;
import model.Pelicula;
import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                Element nodo = (Element) nodosUsuario.item(i);                                  //8. casteamos el i a "nodo", parte de Element.
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

    //METODO PARA ESCRIBIR DATOS EN UN XML
    public void escrituraXML(){
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();   //1. Creamos un ArrayList donde vamos a añadir todas las películas que queramos escribir
                                                                    //2. Vamos a añadir una lista de objetos de Película creados con ChagGPt basados en las clases creadas Usuario y Actor
        Pelicula p1 = new Pelicula(
                "Inception",
                "Christopher Nolan",
                new Actor[]{new Actor("Leonardo DiCaprio"), new Actor("Joseph Gordon-Levitt"), new Actor("Elliot Page")},
                "Ciencia ficción / Acción",
                2010,
                8.8
        );
        Pelicula p2 = new Pelicula(
                "The Dark Knight",
                "Christopher Nolan",
                new Actor[]{new Actor("Christian Bale"), new Actor("Heath Ledger"), new Actor("Gary Oldman")},
                "Acción / Crimen / Drama",
                2008,
                9.0
        );

        Pelicula p3 = new Pelicula(
                "Forrest Gump",
                "Robert Zemeckis",
                new Actor[]{new Actor("Tom Hanks"), new Actor("Robin Wright"), new Actor("Gary Sinise")},
                "Drama / Romance",
                1994,
                8.8
        );

        Pelicula p4 = new Pelicula(
                "The Shawshank Redemption",
                "Frank Darabont",
                new Actor[]{new Actor("Tim Robbins"), new Actor("Morgan Freeman"), new Actor("Bob Gunton")},
                "Drama",
                1994,
                9.3
        );

        Pelicula p5 = new Pelicula(
                "The Godfather",
                "Francis Ford Coppola",
                new Actor[]{new Actor("Marlon Brando"), new Actor("Al Pacino"), new Actor("James Caan")},
                "Crimen / Drama",
                1972,
                9.2
        );

        Pelicula p6 = new Pelicula(
                "Gladiator",
                "Ridley Scott",
                new Actor[]{new Actor("Russell Crowe"), new Actor("Joaquin Phoenix"), new Actor("Connie Nielsen")},
                "Acción / Aventura / Drama",
                2000,
                8.5
        );

        Pelicula p7 = new Pelicula(
                "Interstellar",
                "Christopher Nolan",
                new Actor[]{new Actor("Matthew McConaughey"), new Actor("Anne Hathaway"), new Actor("Jessica Chastain")},
                "Ciencia ficción / Drama",
                2014,
                8.6
        );

        Pelicula p8 = new Pelicula(
                "Pulp Fiction",
                "Quentin Tarantino",
                new Actor[]{new Actor("John Travolta"), new Actor("Samuel L. Jackson"), new Actor("Uma Thurman")},
                "Crimen / Drama",
                1994,
                8.9
        );

        Pelicula p9 = new Pelicula(
                "The Matrix",
                "Lana Wachowski, Lilly Wachowski",
                new Actor[]{new Actor("Keanu Reeves"), new Actor("Carrie-Anne Moss"), new Actor("Laurence Fishburne")},
                "Ciencia ficción / Acción",
                1999,
                8.7
        );

        Pelicula p10 = new Pelicula(
                "Parasite",
                "Bong Joon-ho",
                new Actor[]{new Actor("Song Kang-ho"), new Actor("Cho Yeo-jeong"), new Actor("Choi Woo-shik")},
                "Drama / Thriller",
                2019,
                8.6
        );

        listaPeliculas.add(p1);                                     //3. Añadimos las películas al Array listaPeliculas
        listaPeliculas.add(p2);
        listaPeliculas.add(p3);
        listaPeliculas.add(p4);
        listaPeliculas.add(p5);
        listaPeliculas.add(p6);
        listaPeliculas.add(p7);
        listaPeliculas.add(p8);
        listaPeliculas.add(p9);
        listaPeliculas.add(p10);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); //4. Tenemos que crear el Document, por tanto, necesitamos documentBuilderFactory y documentBuilder
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();      //5. Capturamos la excepción de error en el parseo
            Document document = documentBuilder.newDocument();                                  //6. Empezaremos a crear el document y sus nodos
            Element nodoRoot = document.createElement("cartelera");                     //7. creamos el nodo root. Después, lo configuramos con los atributos y lo anexamos al documento
            nodoRoot.setAttribute("id", "1");                                       //7.1. Le configuramos los atributos al nodo (en este caso id=1)
            document.appendChild(nodoRoot);                                                     //7.2 Lo anexamos al document

            for (Pelicula item: listaPeliculas){                                                //8. Empezamos a crear y añadir los nodos específicos de cada película, para ello debemos recorrer el array listaPeliculas con un foreach
                Element nodoPelicula = document.createElement("pelicula");              //8.1 Creamos nodo
                nodoPelicula.setAttribute("genero", item.getGenero());                     //8.2 configuramos atributos. en algun caso hay que castear a String
                nodoPelicula.setAttribute("anio", String.valueOf(item.getAnio()));
                nodoRoot.appendChild(nodoPelicula);                                             //8.3 añadimos el nodo al nodo contenedor

                Element nodoTitulo = document.createElement("titulo");
                nodoTitulo.setTextContent(item.getTitulo());
                nodoPelicula.appendChild(nodoTitulo);

                Element nodoDirector = document.createElement("director");
                nodoTitulo.setTextContent(item.getDirector());
                nodoPelicula.appendChild(nodoDirector);

                Element nodoActores = document.createElement("actores");            //8.2 Actores es un array, por lo que tenemos que recorrerlo con otro foreach
                for (Actor actor: item.getActores()) {                                      //array que recorre Actores (lo saca mediante item.getActores) obtiene un objeto de Actor y le llama actor
                    Element nodoActor = document.createElement("actor");
                    nodoActor.setTextContent(actor.getNombre());
                    nodoPelicula.appendChild(nodoActores);
                    nodoActores.appendChild(nodoActor);
                }
                                                                                            //9. ahora que ya tenemos la estructura de nodos creada y añadida al documento, estamos listos para escribir el fichero
            TransformerFactory transformerFactory = TransformerFactory.newInstance();       //10. utilizamos para esto el transformerFactory, en vez del documentBuilder, puesto que estamos transformando en xml
            Transformer transformer = transformerFactory.newTransformer();                  //11. creamos un objeto transformer desde TransformerFactory y capturamos la excepción aunque solo añadimos el catch al otro mensaje, puesto que es el mismo tipo de error

            DOMSource domSource = new DOMSource(document);                                  //12. ahora tenemos que ejecutar el metodo transform del objeto transformer, pero primero tenemos que crear la fuente. Un objeto de tipo DOMSource y le pasamos el documento
            StreamResult result = new StreamResult(new File("peliculas.xml"));      //13. ahora necesitamos un string Results que nos va a pedir el metodo transform, le pasamos el nuevo archivo xml que tenemos que crear
            transformer.transform(domSource, result);                                       //14. al metodo transform le pasamos el objeto de DOMSource y el objeto de StreamResult, capturamos la excepción (es la excepción padre de la anterior, así que la reemplazamos

            }

        } catch (ParserConfigurationException |TransformerException e) {
            System.out.println("Error en el parseo del documento");
        }
    }
}
