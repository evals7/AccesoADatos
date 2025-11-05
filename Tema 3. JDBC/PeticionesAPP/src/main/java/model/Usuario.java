package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {          //MODEL: modelado de la clase que simula la entidad con la que trabajamos en BBDD
    private int id;
    private String nombre, mail;
    private int telefono;
    private int idPerfil;

    public Usuario(String nombre, String mail, int telefono, int idPerfil) { //Generamos un constructor adicional sin el ID
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }
}
