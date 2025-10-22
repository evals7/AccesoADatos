package model;

import lombok.Data;
//CLASE PARA METER LOS USUARIOS EN UN LISTADO
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name="usuarios")        //8. Anotación para que la clase Listado sea nombrada "Usuarios
@XmlAccessorType(XmlAccessType.FIELD)   //9. Además, la clase Listado representará un campo, formará parte del XML
public class Listado {                  //6. Creamos una clase que tenga de atributos un listado de Usuario. Getter y Setter con Lombok, no necesitamos el constructor típioc porque, inicialmente, la lista está vacía
    @XmlElement(name= "usuario")        //7. anotación XML para que, cuando creemos esta lista, cada item sea nombrado "usuario" en el xml
    private List<Usuario> listado;

    public Listado() {                  //6.1 Creamos un constructor que de primeras esté vacío, pero inicialice la lista
        this.listado = new ArrayList<>();
    }
}

                                        //10. Ya tenemos la clase Usuario y Usuarios(Listado) configuradas con anotaciones, ahora vamos al escritor en main
