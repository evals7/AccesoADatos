package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "usuarios")              //anotación para que el elemento root sea usuarios
public class UsuarioXML {               //CLASE PARA EXPORTAR TODOS LOS DATOS A UNA LISTA DE USUARIO

    @XmlElement(name = "usuario")               //anotación para que el elemento sea un usuario
    private List<Usuario> lista;

}
