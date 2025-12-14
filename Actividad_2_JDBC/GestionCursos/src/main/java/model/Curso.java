package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    private int id;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> listaEstudiantes;

    public Curso(int id, String nombre, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Curso(String nombre) {
        this.nombre = nombre;
    }

}
