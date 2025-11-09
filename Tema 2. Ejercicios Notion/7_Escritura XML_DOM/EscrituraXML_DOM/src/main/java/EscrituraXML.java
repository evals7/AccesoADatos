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

public class EscrituraXML {

    static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();              //documento creado
            Element nodoRaiz = document.createElement("productos");  //nodoRaiz creado
            document.appendChild(nodoRaiz);                                  //nodoRaiz indexado

            Element nodoProducto = document.createElement("producto");
            nodoProducto.setAttribute("id", "1");
            nodoRaiz.appendChild(nodoProducto);

            Element nodoNombre = document.createElement("nombre");
            nodoNombre.setTextContent("Guitarra acústica");
            nodoProducto.appendChild(nodoNombre);

            Element nodoPrecio = document.createElement("precio");
            nodoNombre.setTextContent("150€");
            nodoProducto.appendChild(nodoPrecio);

            Element nodoCantidad = document.createElement("cantidad");
            nodoNombre.setTextContent("3");
            nodoProducto.appendChild(nodoCantidad);

            Element nodoCategoria = document.createElement("categoria");
            nodoNombre.setTextContent("instrumentos");
            nodoProducto.appendChild(nodoCategoria);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("productos.xml"));
            transformer.transform(domSource, result);

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo del documento");
        } catch (TransformerException e) {
            System.out.println("Error en la configuración del transformer");
        }

    }
}
