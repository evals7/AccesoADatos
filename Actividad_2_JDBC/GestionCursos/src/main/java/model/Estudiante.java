package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    private int id;
    private String nombre;
    private List<Curso> listaCursos;

    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Estudiante(String nombre) {
        this.nombre = nombre;
    }
}
