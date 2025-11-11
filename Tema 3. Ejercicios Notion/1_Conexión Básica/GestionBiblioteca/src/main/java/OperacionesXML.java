import dao.LibroDAOImp;
import dao.PrestamoDAOImp;
import database.DBConnection;
import model.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperacionesXML {

    public void escrituraXML(){

        LibroDAOImp libroDAOImp = new LibroDAOImp();
        ArrayList<Libro> listaLibros = libroDAOImp.obtenerLista();


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element nodoRoot = document.createElement("biblioteca");
            document.appendChild(nodoRoot);

            for(Libro item: listaLibros){
                Element nodoLibro = document.createElement("libro");
                nodoLibro.setAttribute("id", String.valueOf(item.getId()));
                nodoLibro.setAttribute("isbn", String.valueOf(item.getIsbn()));
                nodoRoot.appendChild(nodoLibro);

                Element nodoTitulo = document.createElement("titulo");
                nodoTitulo.setTextContent(item.getTitulo());
                nodoLibro.appendChild(nodoTitulo);

                Element nodoAutor = document.createElement("autor");
                nodoAutor.setTextContent(item.getAutor());
                nodoLibro.appendChild(nodoAutor);

                Element nodoEditorial = document.createElement("editorial");
                nodoEditorial.setTextContent(item.getEditorial());
                nodoLibro.appendChild(nodoEditorial);

                Element nodoAno = document.createElement("anoPublicacion");
                nodoAno.setTextContent(String.valueOf(item.getAnoPublicacion()));
                nodoLibro.appendChild(nodoAno);

                Element nodoCategoria = document.createElement("categoría");
                nodoCategoria.setTextContent(item.getCategoria());
                nodoLibro.appendChild(nodoCategoria);

                Element nodoPrecio = document.createElement("precio");
                nodoPrecio.setTextContent(String.valueOf(item.getPrecio()));
                nodoLibro.appendChild(nodoPrecio);

                Element nodoStock = document.createElement("stock");
                nodoStock.setTextContent(String.valueOf(item.getStock()));
                nodoLibro.appendChild(nodoStock);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("biblioteca3.xml"));
            transformer.transform(domSource, result);

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo del documento");
        } catch (TransformerException e) {
            System.out.println("Error en la configuracion del transformer");
        }


    }
}
