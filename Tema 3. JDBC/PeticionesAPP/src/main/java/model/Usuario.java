package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)                                  //XML: para exportar a XMl necesitamos esta anotación para que se exporte como campo
public class Usuario {          //MODEL: modelado de la clase que simula la entidad con la que trabajamos en BBDD

    @XmlAttribute   //xml annotation
    private int id;
    private String nombre, mail;
    private int telefono;
    @XmlAttribute
    private int idPerfil;

    public Usuario(String nombre, String mail, int telefono, int idPerfil) { //Generamos un constructor adicional sin el ID
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("mail = " + mail);
        System.out.println("telefono = " + telefono);
        System.out.println("idPerfil = " + idPerfil);
    }
}
