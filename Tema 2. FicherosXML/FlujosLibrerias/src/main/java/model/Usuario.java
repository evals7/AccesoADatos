package model;

//CLASE PARA ESCRIBIR USUARIOS EN UN XML
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@AllArgsConstructor                     //2. Creamos todos los constructores y getter y setter con las anotaciones de Lombok
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)  //3. Con esta anotación de JAXB, Indicamos a Java que esta clase será un campo, con todos sus atributos

public class Usuario {                  //1. Definimos la clase y sus atributos
    @XmlAttribute                       //4. con esta anotación indicamos que queremos que dni sea un atributo, no un nodo
    private String dni;
    private String nombre;
    private String apellido;


                                    //5. Queremos crear varios campos como este, por loq ue vamos a crear una clase que contenga un listado de Usuario
public void mostrarDatos(){             //MÉTDOO PARA MOSTRAR DATOS DE USUARIO EN EL LECTOR DE XML
    System.out.println("dni = " + dni);
    System.out.println("nombre = " + nombre);
    System.out.println("apellido = " + apellido);
}

}
